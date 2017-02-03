package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import java.util.List;
import java.util.Map;
import com.agrocomp.model.base.service.BaseCategoriaService;
import com.agrocomp.model.criteria.CategoriaCriteria;
import com.agrocomp.model.dao.CategoriaDAO;
import com.agrocomp.model.entity.Categoria;
import java.sql.Connection;
import java.util.HashMap;

public class CategoriaService implements BaseCategoriaService {

    @Override
    public void create(Categoria entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CategoriaDAO dao = new CategoriaDAO();
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
    public List<Categoria> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Categoria> categoriaList = null;
        try {
            CategoriaDAO dao = new CategoriaDAO();
            categoriaList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return categoriaList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
       Connection conn = ConnectionManager.getInstance().getConnection();
        Long count = 0L;
        try {
            CategoriaDAO dao = new CategoriaDAO();
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
    public Categoria readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Categoria cat = null;
        try {
            CategoriaDAO dao = new CategoriaDAO();
            cat = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return cat;
    }

    @Override
    public void update(Categoria entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CategoriaDAO dao = new CategoriaDAO();
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
            CategoriaDAO dao = new CategoriaDAO();
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
        String nome = (String) filds.get("nome");
        if (nome == null || nome.trim().isEmpty()) {
            errors.put("nome", "Campo Obrigatório!");
        } else {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(CategoriaCriteria.NOME_EQ, nome);
            List<Categoria> categoriaList= this.readByCriteria(criteria, null, null);
            if (!categoriaList.isEmpty()) {
                errors.put("nome", "Este valor já se encontra em uso!");
            }
        }
        return errors;
    }

}
