package com.agrocomp.model.dao;

import com.agrocomp.model.base.dao.BaseClienteDAO;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteDAO implements BaseClienteDAO<Cliente> {

    @Override
    public void update(Connection conn, Cliente entity) throws Exception {
        String sql = "UPDATE cliente SET  cidade=?, estado=?, telefone=? , sobre=? WHERE usuario_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getCidade());
        ps.setString(++i, entity.getEstado());
        ps.setString(++i, entity.getTelefone());
        ps.setString(++i, entity.getSobre());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public List<Cliente> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Cliente> clienteList = new ArrayList<>();
        String sql = "SELECT usuario.*, cliente.usuario_fk cliente, administrador.usuario_fk adm FROM usuario left join cliente on cliente.usuario_fk=usuario.id left join administrador on administrador.usuario_fk=usuario.id where 1=1";
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            Usuario usuario = null;
            if (rs.getLong("cliente") > 0) {
                usuario = new Cliente();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                clienteList.add((Cliente) usuario);
            }

        }
        rs.close();
        ps.close();
        return clienteList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
