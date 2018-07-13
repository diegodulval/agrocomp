package com.agrocomp.model.dao;

import com.agrocomp.model.base.BaseDAO;
import com.agrocomp.model.entity.Noticia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticiaDAO implements BaseDAO<Noticia> {

    @Override
    public void create(Connection conn, Noticia entity) throws Exception {
        String sql = "INSERT INTO noticia(administrador_fk,data_hora, descricao, link, titulo)VALUES (?, ?, ?, ?, ?) returning id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getAdm().getId());
        ps.setTimestamp(++i, entity.getDataHora());
        ps.setString(++i, entity.getDescricao());
        ps.setString(++i, entity.getLink());
        ps.setString(++i, entity.getTitulo());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Noticia readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM noticia where id=?";
        Noticia noticia = null;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            noticia = new Noticia();
            noticia.setId(id);
            noticia.setDataHora(rs.getTimestamp("data_hora"));
            noticia.setDescricao(rs.getString("descricao"));
            noticia.setLink(rs.getString("link"));
            noticia.setTitulo(rs.getString("titulo"));
        }
        rs.close();
        ps.close();
        return noticia;
    }

    @Override
    public List<Noticia> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Noticia> noticiaList = new ArrayList<>();
        String sql = "SELECT * FROM noticia where 1=1";
        sql += applyCriteria(criteria);
        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            Noticia noticia = new Noticia();
            noticia.setId(rs.getLong("id"));
            noticia.setDataHora(rs.getTimestamp("data_hora"));
            noticia.setDescricao(rs.getString("descricao"));
            noticia.setLink(rs.getString("link"));
            noticia.setTitulo(rs.getString("titulo"));
            noticiaList.add(noticia);
        }
        return noticiaList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*) count FROM noticia WHERE 1=1 ";
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
    public void update(Connection conn, Noticia entity) throws Exception {
        String sql = "UPDATE noticia SET  descricao=?, link=?, titulo=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getDescricao());
        ps.setString(++i, entity.getLink());
        ps.setString(++i, entity.getTitulo());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM noticia WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        String sql = " order by data_hora desc";
        return sql;
    }

}
