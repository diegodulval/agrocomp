
package com.agrocomp.model.dao;

import com.agrocomp.model.base.dao.BaseReporteDAO;
import com.agrocomp.model.entity.Anuncio;
import com.agrocomp.model.entity.Reporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReporteDAO implements BaseReporteDAO<Reporte>{

    @Override
    public void create(Connection conn, Reporte entity) throws Exception {
        String sql="INSERT INTO reporte(anuncio_fk,descricao, data_hora) VALUES (?, ?, ?) returning id";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i =0;
        ps.setLong(++i, entity.getAnuncio().getId());
        ps.setString(++i, entity.getDescricao());
        ps.setTimestamp(++i, entity.getDataHora());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public List<Reporte> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql="SELECT reporte.*,anuncio.id id_anuncio,anuncio.nome FROM reporte left join anuncio on anuncio.id = reporte.anuncio_fk";
        List<Reporte> reporteList = new ArrayList<>();
         Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);
       Reporte reporte= null;
        while (rs.next()) {
            reporte= new Reporte();
            reporte.setId(rs.getLong("id"));
            reporte.setDescricao(rs.getString("descricao"));
            reporte.setDataHora(rs.getTimestamp("data_hora"));
            Anuncio anuncio= new Anuncio();
            anuncio.setId(rs.getLong("id_anuncio"));
            anuncio.setNome(rs.getString("nome"));
            reporte.setAnuncio(anuncio);
            reporteList.add(reporte);
        }
        return reporteList;
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql= "DELETE FROM reporte WHERE id=?;";
         PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }
    
}
