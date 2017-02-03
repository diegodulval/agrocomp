
package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseReporteService;
import com.agrocomp.model.dao.ReporteDAO;
import com.agrocomp.model.entity.Reporte;
import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class ReporteService implements BaseReporteService<Reporte>{

    @Override
    public void create(Reporte entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ReporteDAO dao = new ReporteDAO();
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
    public List<Reporte> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Reporte> reporteList = null;
        try {
            ReporteDAO dao = new ReporteDAO();
            reporteList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return reporteList;
    }

    @Override
    public Map<Long, String> validate(Reporte entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ReporteDAO dao = new ReporteDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

   
    
}
