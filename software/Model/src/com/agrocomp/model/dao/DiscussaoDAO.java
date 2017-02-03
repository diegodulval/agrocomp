package com.agrocomp.model.dao;

import com.agrocomp.model.base.BaseDAO;
import com.agrocomp.model.criteria.DiscussaoCriteria;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Discussao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscussaoDAO implements BaseDAO<Discussao> {

    @Override
    public void create(Connection conn, Discussao entity) throws Exception {
        String sql = "INSERT INTO discussao(titulo, pergunta, datahora, cliente_fk) VALUES (?, ?, ?, ?) returning id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getTitulo());
        ps.setString(++i, entity.getPergunta());
        ps.setTimestamp(++i, entity.getDataHora());
        ps.setLong(++i, entity.getCliente().getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Discussao readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT  titulo, pergunta, datahora, cliente_fk FROM discussao where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Discussao d = null;
        if (rs.next()) {
            d = new Discussao();
            d.setId(id);
            d.setTitulo(rs.getString("titulo"));
            d.setPergunta(rs.getString("pergunta"));
            d.setDataHora(rs.getTimestamp("datahora"));
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("cliente_fk"));
            d.setCliente(cliente);
        }
        rs.close();
        ps.close();
        return d;
    }

    @Override
    public List<Discussao> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql="SELECT  id,titulo, pergunta, datahora, cliente_fk FROM discussao where 1=1 ";
        Statement s = conn.createStatement();
        sql += applyCriteria(criteria);
        
         if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }
        List<Discussao> discussaoList = new ArrayList<>();
        ResultSet rs = s.executeQuery(sql);
        Discussao d = null;
        while (rs.next()) {
            d = new Discussao();
            d.setId(rs.getLong("id"));
            d.setTitulo(rs.getString("titulo"));
            d.setPergunta(rs.getString("pergunta"));
            d.setDataHora(rs.getTimestamp("datahora"));
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("cliente_fk"));
            d.setCliente(cliente);
            discussaoList.add(d);
        }
        rs.close();
        s.close();
        return discussaoList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
         String sql = "SELECT count(*) count FROM discussao WHERE 1=1 ";
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
    public void update(Connection conn, Discussao entity) throws Exception {
        String sql="UPDATE discussao SET  titulo=?, pergunta=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, entity.getTitulo());
        ps.setString(2, entity.getPergunta());
        ps.setLong(3, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql="DELETE FROM discussao WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        String sql="";
        
        Long cliente_fk = (Long) criteria.get(DiscussaoCriteria.CLIENTE_FK);
        if(cliente_fk!= null && cliente_fk >0){
            sql=" and cliente_fk="+cliente_fk;
        }
        
         String discussaoILike=(String) criteria.get(DiscussaoCriteria.DISCUSSAO_ILIKE);
        if(discussaoILike != null && !discussaoILike.trim().isEmpty()){
             sql += " AND titulo ILIKE '%" + discussaoILike + "%'";
        }
         sql += " order by datahora desc";
        
        return sql;
    }

}
