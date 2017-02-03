/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agrocomp.model.dao;

import com.agrocomp.model.base.BaseDAO;
import com.agrocomp.model.criteria.CategoriaCriteria;
import com.agrocomp.model.entity.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alunos
 */
public class CategoriaDAO implements BaseDAO<Categoria>{

    @Override
    public void create(Connection conn, Categoria entity) throws Exception {
         String sql = "INSERT INTO categoria(nome) VALUES (?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, entity.getNome());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }
    

    @Override
    public Categoria readById(Connection conn, Long id) throws Exception {
        Categoria cat= null;
        String sql="SELECT nome FROM categoria where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            cat= new Categoria();
            cat.setId(id);
            cat.setNome(rs.getString("nome"));
        }
        rs.close();
        ps.close();
        return cat;
    }

    @Override
    public List<Categoria> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Categoria> categoriaList = new ArrayList<>();
        String sql="SELECT id,nome FROM categoria where 1=1 order by nome";
        if(criteria==null){
            criteria= new HashMap<>();
        }
        sql+= applyCriteria(criteria);
        Statement ps = conn.createStatement();
        ResultSet rs= ps.executeQuery(sql);
        while(rs.next()){
            Categoria cat= new Categoria();
            cat.setId(rs.getLong("id"));
            cat.setNome(rs.getString("nome"));
            categoriaList.add(cat);
        }
        rs.close();
        ps.close();
        return categoriaList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*) count FROM categoria WHERE 1=1 ";
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
    public void update(Connection conn, Categoria entity) throws Exception {
        String sql="UPDATE categoria SET nome=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, entity.getNome());
        ps.setLong(2, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
         String sql="DELETE FROM categoria  WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        String sql="";
        
        String nomeEQ = (String)criteria.get(CategoriaCriteria.NOME_EQ);
        if(nomeEQ != null && !nomeEQ.isEmpty()){
            sql=" and nome= '"+nomeEQ+"'";
        }
        return sql;
    }
    
}
