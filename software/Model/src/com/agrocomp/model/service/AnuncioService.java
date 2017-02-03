package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import java.util.List;
import java.util.Map;
import com.agrocomp.model.dao.AnuncioDAO;
import com.agrocomp.model.entity.Anuncio;
import java.sql.Connection;
import java.util.HashMap;
import com.agrocomp.model.base.service.BaseAnuncioService;

public class AnuncioService implements BaseAnuncioService {



    @Override
    public void create(Anuncio entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AnuncioDAO dao = new AnuncioDAO();
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
    public Anuncio readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Anuncio anuncio = null;
        try {
            AnuncioDAO dao = new AnuncioDAO();
            anuncio = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return anuncio;
    }

    @Override
    public void update(Anuncio entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AnuncioDAO dao = new AnuncioDAO();
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
            AnuncioDAO dao = new AnuncioDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();

        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }

    }

    @Override
    public List<Anuncio> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Anuncio> anuncioList = null;
        try {
            AnuncioDAO dao = new AnuncioDAO();
            anuncioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();

        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
        return anuncioList;

    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long count = 0L;
        try {
            AnuncioDAO dao = new AnuncioDAO();
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
    public Map<String, String> validate(Map<String, Object> filds) {
        Map<String, String> errors = new HashMap<>();
        Anuncio a = (Anuncio) filds.get("anuncio");
        if (a.getNome() == null || a.getNome().trim().isEmpty()) {
            errors.put("nome", "Insira um nome ao anuncio");
        }
        if (a.getQtd() == null || a.getQtd() <= 0) {
            errors.put("qtd", "Insira a quantidade disponivel");
        }
        if (a.getPreco() == null || a.getPreco().equals(0.00)) {
            errors.put("preco", "Insira um preço valido");
        }
        if(a.getCategoria() == null || a.getCategoria().getId()==null || a.getCategoria().getId()<=0){
            errors.put("categoria", "Selecione uma categoria");
        }

        return errors;
    }

}
