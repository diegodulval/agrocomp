package com.agrocomp.model.dao;

import com.agrocomp.model.base.BaseDAO;
import com.agrocomp.model.criteria.UsuarioCriteria;
import com.agrocomp.model.entity.Administrador;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDAO implements BaseDAO<Usuario> {

    @Override
    public void create(Connection conn, Usuario entity) throws Exception {
        String sql = "INSERT INTO usuario(nome, email, senha) VALUES (?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getSenha());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();

        if (entity instanceof Administrador) {
            this.createAdministrador(conn, (Administrador) entity);
        } else if (entity instanceof Cliente) {
            this.createCliente(conn, (Cliente) entity);
        }
    }

    private void createAdministrador(Connection conn, Administrador entity) throws SQLException {
        String sql = "INSERT INTO administrador(usuario_fk) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    private void createCliente(Connection conn, Cliente entity) throws SQLException {
        String sql = "INSERT INTO cliente(usuario_fk) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public Usuario readById(Connection conn, Long id) throws Exception {
        Usuario usuario = null;
        String sql = "SELECT cliente.*,usuario.nome,usuario.email,usuario.senha,administrador.usuario_fk adm,anuncio.id id_anuncio,anuncio.nome anuncio,anuncio.foto,anuncio.qtde,anuncio.preco,anuncio.descricao, categoria.id id_categoria,categoria.nome categoria FROM usuario left join cliente on cliente.usuario_fk= usuario.id  left join administrador on administrador .usuario_fk = usuario.id left join anuncio on anuncio.cliente_fk=cliente.usuario_fk left join categoria on categoria.id= anuncio.categoria_fk where cliente.usuario_fk=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (usuario == null) {

                if (rs.getLong("usuario_fk") > 0) {
                    usuario = new Cliente();
                } else {
                    usuario = new Administrador();
                }
            }
            usuario.setId(id);
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            if (usuario instanceof Cliente) {
                ((Cliente) usuario).setCidade(rs.getString("cidade"));
                ((Cliente) usuario).setEstado(rs.getString("estado"));
                ((Cliente) usuario).setTelefone(rs.getString("telefone"));
                ((Cliente) usuario).setSobre(rs.getString("sobre"));

            }
        }
        rs.close();
        ps.close();
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "SELECT usuario.*, cliente.usuario_fk cliente, administrador.usuario_fk adm FROM usuario left join cliente on cliente.usuario_fk=usuario.id left join administrador on administrador.usuario_fk=usuario.id where 1=1";
        sql += applyCriteria(criteria);
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            Usuario usuario = null;
            if (rs.getLong("cliente") > 0) {
                usuario = new Cliente();
            } else {
                usuario = new Administrador();
            }
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuarioList.add(usuario);
        }
        rs.close();
        ps.close();
        return usuarioList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*) count FROM anuncio WHERE 1=1 ";
        sql += applyCriteria(criteria);
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Long count = 0L;
        if (rs.next()) {
            count = rs.getLong("count");
        }
        rs.close();
        s.close();
        return count;
    }

    @Override
    public void update(Connection conn, Usuario entity) throws Exception {
        String sql = "UPDATE usuario SET nome=?,senha=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getSenha());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        String sql = "";

        String email = (String) criteria.get(UsuarioCriteria.EMAIL_EQ);
        if (email != null && !email.isEmpty()) {
            sql = " and email='" + email + "'";
        }

        String senha = (String) criteria.get(UsuarioCriteria.SENHA_EQ);
        if (senha != null && !senha.isEmpty()) {
            sql += " and senha = '" + senha + "'";
        }

        String nome = (String) criteria.get(UsuarioCriteria.NOME_EQ);
        if (nome != null && !nome.trim().isEmpty()) {
            sql += " and nome='" + nome + "'";
        }
        return sql;
    }

}
