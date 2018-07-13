package com.agrocomp.model.dao;

import com.agrocomp.model.base.BaseDAO;
import com.agrocomp.model.criteria.AnuncioCriteria;
import com.agrocomp.model.entity.Anuncio;
import com.agrocomp.model.entity.Categoria;
import com.agrocomp.model.entity.Cliente;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnuncioDAO implements BaseDAO<Anuncio> {

    @Override
    public Anuncio readById(Connection conn, Long id) throws Exception {
        String sql = "select anuncio.cliente_fk,anuncio.nome anuncio, anuncio.id id_anuncio, anuncio.preco,anuncio.qtde,anuncio.descricao,anuncio.foto,categoria.id id_categoria, categoria.nome categoria from anuncio left join categoria on categoria.id= anuncio.categoria_fk   where anuncio.id=? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Anuncio a = null;
        if (rs.next()) {
            a = new Anuncio();
            a.setId(rs.getLong("id_anuncio"));
            a.setNome(rs.getString("anuncio"));
            a.setPreco(rs.getBigDecimal("preco"));
            a.setQtd(rs.getInt("qtde"));
            a.setDescricao(rs.getString("descricao"));
            a.setFoto(rs.getBytes("foto"));
            Categoria cat = new Categoria();
            cat.setId(rs.getLong("id_categoria"));
            cat.setNome(rs.getString("categoria"));
            a.setCategoria(cat);
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("cliente_fk"));
            a.setCliente(cliente);

        }
        rs.close();
        ps.close();
        return a;
    }

    @Override
    public List<Anuncio> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select anuncio.nome anuncio, anuncio.id id_anuncio, anuncio.preco,anuncio.qtde,anuncio.descricao,anuncio.foto,categoria.id id_categoria, categoria.nome categoria from anuncio left join categoria on categoria.id= anuncio.categoria_fk   where 1=1 ";
        Statement s = conn.createStatement();
        sql += applyCriteria(criteria);

        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }
        List<Anuncio> anuncioList = new ArrayList<>();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Anuncio a = new Anuncio();
            a.setId(rs.getLong("id_anuncio"));
            a.setNome(rs.getString("anuncio"));
            a.setPreco(rs.getBigDecimal("preco"));
            a.setQtd(rs.getInt("qtde"));
            a.setDescricao(rs.getString("descricao"));
            a.setFoto(rs.getBytes("foto"));
            Categoria cat = new Categoria();
            cat.setId(rs.getLong("id_categoria"));
            cat.setNome(rs.getString("categoria"));
            a.setCategoria(cat);
            anuncioList.add(a);
        }
        rs.close();
        s.close();
        return anuncioList;
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
    public String applyCriteria(Map<Long, Object> criteria) {
        String sql = "";

        String nomeEQ = (String) criteria.get(AnuncioCriteria.NOME_EQ);
        if (nomeEQ != null && !nomeEQ.isEmpty()) {
            sql += " and anuncio.nome='" + nomeEQ + "'";
        }

        Long id = (Long) criteria.get(AnuncioCriteria.CLIENTE_FK);
        if (id != null && id > 0) {
            sql = " and anuncio.cliente_fk=" + id + "";
        }

        BigDecimal maior = (BigDecimal) criteria.get(AnuncioCriteria.MAIOR);
        if (maior != null) {
            sql += " and anuncio.preco<" + maior;
        }
        BigDecimal menor = (BigDecimal) criteria.get(AnuncioCriteria.MENOR);
        if (menor != null) {
            sql += " and anuncio.preco>" + menor;
        }

        Long categoria = (Long) criteria.get(AnuncioCriteria.CATEGORIA_EQ);
        if (categoria != null && categoria > 0) {
            sql += " and categoria.id='" + categoria + "'";
        }

        String nomeIlike = (String) criteria.get(AnuncioCriteria.NOME_ILIKE);
        if (nomeIlike != null && !nomeIlike.trim().isEmpty()) {
            sql += " AND anuncio.nome ILIKE '%" + nomeIlike + "%'";
        }

        return sql;
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM anuncio WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void create(Connection conn, Anuncio entity) throws Exception {
        String sql = "INSERT INTO anuncio(cliente_fk, categoria_fk, foto, qtde, preco, nome,descricao) VALUES (?, ?, ?, ?, ?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getCliente().getId());
        ps.setLong(++i, entity.getCategoria().getId());
        ps.setBytes(++i, entity.getFoto());
        ps.setInt(++i, entity.getQtd());
        ps.setBigDecimal(++i, entity.getPreco());
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Anuncio entity) throws Exception {
        String sql = "UPDATE public.anuncio SET categoria_fk=?, nome=?,  qtde=?, preco=?, descricao=?, foto=? WHERE anuncio.id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getCategoria().getId());
        ps.setString(++i, entity.getNome());
        ps.setInt(++i, entity.getQtd());
        ps.setBigDecimal(++i, entity.getPreco());
        ps.setString(++i, entity.getDescricao());
        ps.setBytes(++i, entity.getFoto());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

}
