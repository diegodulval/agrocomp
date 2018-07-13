package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseMensagemService;
import com.agrocomp.model.dao.MensagemDAO;
import com.agrocomp.model.entity.Mensagem;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MensagemService implements BaseMensagemService {

    @Override
    public void create(Mensagem entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemDAO dao = new MensagemDAO();
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
    public Mensagem readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Mensagem m = null;
        try {
            MensagemDAO dao = new MensagemDAO();
            m = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return m;
    }

    @Override
    public List<Mensagem> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Mensagem> mensagemList = null;
        try {
            MensagemDAO dao = new MensagemDAO();
            mensagemList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return mensagemList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long count = 0L;
        try {
            MensagemDAO dao = new MensagemDAO();
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
    public void update(Mensagem entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemDAO dao = new MensagemDAO();
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
            MensagemDAO dao = new MensagemDAO();
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
        String resposta = (String) filds.get("resposta");
        if (resposta == null || resposta.trim().isEmpty()) {
            errors.put("resposta", "Insira uma resposta");
        }

        return errors;
    }

}
