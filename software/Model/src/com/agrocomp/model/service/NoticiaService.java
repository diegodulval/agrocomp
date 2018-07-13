package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseNoticiaService;
import com.agrocomp.model.dao.NoticiaDAO;
import com.agrocomp.model.entity.Noticia;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class NoticiaService implements BaseNoticiaService {

    @Override
    public void create(Noticia entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            NoticiaDAO dao = new NoticiaDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Noticia readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Noticia noticia = null;
        try {
            NoticiaDAO dao = new NoticiaDAO();
            noticia = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return noticia;
    }

    @Override
    public List<Noticia> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Noticia> noticiaList = null;
        try {
            NoticiaDAO dao = new NoticiaDAO();
            noticiaList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return noticiaList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long count = 0L;
        try {
            NoticiaDAO dao = new NoticiaDAO();
            count = dao.countByCriteria(conn, criteria);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return count;
    }

    @Override
    public void update(Noticia entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            NoticiaDAO dao = new NoticiaDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            NoticiaDAO dao = new NoticiaDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> filds) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
