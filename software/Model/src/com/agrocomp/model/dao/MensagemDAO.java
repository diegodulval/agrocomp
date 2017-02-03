package com.agrocomp.model.dao;

import com.agrocomp.model.base.BaseDAO;
import com.agrocomp.model.criteria.MensagemCriteria;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Discussao;
import com.agrocomp.model.entity.Mensagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MensagemDAO implements BaseDAO<Mensagem> {

    @Override
    public void create(Connection conn, Mensagem entity) throws Exception {
        String sql = "INSERT INTO mensagem( resposta, datahora, cliente_fk,discussao_fk) VALUES (?, ?, ?,?) returning id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getMensagem());
        ps.setTimestamp(++i, entity.getDataHora());
        ps.setLong(++i, entity.getCliente().getId());
        ps.setLong(++i, entity.getDiscussao().getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Mensagem readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT  resposta, datahora, cliente_fk FROM public.mensagem id= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Mensagem m = null;
        if (rs.next()) {
            m = new Mensagem();
            m.setId(id);
            m.setMensagem(rs.getString("resposta"));
            m.setDataHora(rs.getTimestamp("datahora"));
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("cliente_fk"));
            m.setCliente(cliente);
        }
        rs.close();
        ps.close();
        return m;
    }

    @Override
    public List<Mensagem> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT mensagem.id id_mensagem, resposta, datahora, cliente_fk, discussao_fk,nome,email FROM public.mensagem  left join usuario on usuario.id=mensagem.cliente_fk where 1=1";
        Statement s = conn.createStatement();
        sql += applyCriteria(criteria);

        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }
        List<Mensagem> mensagemList = new ArrayList<>();
        ResultSet rs = s.executeQuery(sql);
        Mensagem m = null;
        while (rs.next()) {
            m = new Mensagem();
            m.setId(rs.getLong("id_mensagem"));
            m.setMensagem(rs.getString("resposta"));
            m.setDataHora(rs.getTimestamp("datahora"));
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("cliente_fk"));
            cliente.setEmail(rs.getString("email"));
            cliente.setNome(rs.getString("nome"));
            m.setCliente(cliente);
            Discussao discussao = new Discussao();
            discussao.setId(rs.getLong("discussao_fk"));
            m.setDiscussao(discussao);
            mensagemList.add(m);
        }
        return mensagemList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*) count FROM mensagem WHERE 1=1 ";
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
    public void update(Connection conn, Mensagem entity) throws Exception {
        String sql = "UPDATE mensagem SET  resposta=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, entity.getMensagem());
        ps.setLong(2, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM mensagem  WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) {
        String sql = "";
        Long discussao_fk = (Long) criteria.get(MensagemCriteria.DISCUSSAO_FK);
        if (discussao_fk != null && discussao_fk > 0) {
            sql = " and discussao_fk=" + discussao_fk;
        }
        return sql;
    }

}
