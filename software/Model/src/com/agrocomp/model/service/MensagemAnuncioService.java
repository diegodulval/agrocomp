package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseMensagemAnuncioService;
import com.agrocomp.model.dao.MensagemAnuncioDAO;
import com.agrocomp.model.entity.MensagemAnuncio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MensagemAnuncioService implements BaseMensagemAnuncioService {

    @Override
    public void create(MensagemAnuncio entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemAnuncioDAO dao = new MensagemAnuncioDAO();
            dao.create(conn, entity);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    @Override
    public MensagemAnuncio readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MensagemAnuncio> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<MensagemAnuncio> mensagemAnuncioList = new ArrayList<>();
        try {
            MensagemAnuncioDAO dao = new MensagemAnuncioDAO();
            mensagemAnuncioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        } finally {
            conn.close();
        }
        return mensagemAnuncioList;
    }

    public List<MensagemAnuncio> readByIdAnuncio(Long idAnuncio) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<MensagemAnuncio> mensagemAnuncioList = new ArrayList<>();
        try {
            MensagemAnuncioDAO dao = new MensagemAnuncioDAO();
            mensagemAnuncioList = dao.readByIdAnuncio(conn, idAnuncio);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        } finally {
            conn.close();
        }
        return mensagemAnuncioList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MensagemAnuncio entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemAnuncioDAO dao = new MensagemAnuncioDAO();
            dao.delete(conn, id);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        } finally {
            conn.close();
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> filds) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
