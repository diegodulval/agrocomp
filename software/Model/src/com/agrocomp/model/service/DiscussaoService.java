package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseDiscussaoService;
import com.agrocomp.model.dao.DiscussaoDAO;
import com.agrocomp.model.entity.Discussao;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscussaoService implements BaseDiscussaoService {

    @Override
    public void create(Discussao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            DiscussaoDAO dao = new DiscussaoDAO();
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
    public Discussao readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Discussao discussao = null;
        try {
            DiscussaoDAO dao = new DiscussaoDAO();
            discussao = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return discussao;
    }

    @Override
    public List<Discussao> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Discussao> discussaoList = null;
        try {
            DiscussaoDAO dao = new DiscussaoDAO();
            discussaoList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return discussaoList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long count = 0L;
        try {
            DiscussaoDAO dao = new DiscussaoDAO();
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
    public void update(Discussao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            DiscussaoDAO dao = new DiscussaoDAO();
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
            DiscussaoDAO dao = new DiscussaoDAO();
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
        Map<String, String> errors = new HashMap<>();
        String titulo = (String) filds.get("titulo");
        if (titulo == null || titulo.trim().isEmpty() || titulo.length() > 200) {
            errors.put("titulo", "insira um titulo");
        }

        String pergunta = (String) filds.get("pergunta");
        if (pergunta == null || pergunta.trim().isEmpty() || pergunta.length() > 500) {
            errors.put("pergunta", "insira uma pergunta");
        }
        return errors;
    }

}
