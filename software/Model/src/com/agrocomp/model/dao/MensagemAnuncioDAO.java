package com.agrocomp.model.dao;

import com.agrocomp.model.base.dao.BaseMensagemAnuncioDAO;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.MensagemAnuncio;
import com.agrocomp.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MensagemAnuncioDAO implements BaseMensagemAnuncioDAO<MensagemAnuncio> {

    @Override
    public void create(Connection conn, MensagemAnuncio entity) throws Exception {
        String sql = "INSERT INTO mensagem_anuncio(usuario_fk, anuncio_fk, mensagem, data_hora) VALUES(?, ?, ?, ?) returning id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setLong(++i, entity.getAnuncio().getId());
        ps.setString(++i, entity.getMensagem());
        ps.setTimestamp(++i, entity.getDataHora());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public List<MensagemAnuncio> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * FROM mensagem_anuncio WHERE true";
        List<MensagemAnuncio> mensagemAnuncioList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;

        ps.close();
        return mensagemAnuncioList;
    }

    public List<MensagemAnuncio> readByIdAnuncio(Connection conn, Long idAnuncio) throws Exception {
        String sql = "SELECT mensagem_anuncio.id, mensagem_anuncio.anuncio_fk, mensagem_anuncio.usuario_fk, mensagem_anuncio.mensagem, mensagem_anuncio.data_hora,\n"
                + "usuario.nome \n"
                + "FROM mensagem_anuncio \n"
                + "LEFT JOIN usuario ON usuario.id = mensagem_anuncio.usuario_fk \n"
                + "WHERE anuncio_fk = ?";
        List<MensagemAnuncio> mensagemAnuncioList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, idAnuncio);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MensagemAnuncio ma = new MensagemAnuncio();
            Usuario usuario = new Cliente();
            ma.setId(rs.getLong("id"));
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("nome"));
            ma.setUsuario(usuario);
            ma.setMensagem(rs.getString("mensagem"));
            ma.setDataHora(rs.getTimestamp("data_hora"));
            mensagemAnuncioList.add(ma);
        }

        ps.close();
        return mensagemAnuncioList;
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM mensagem_anuncio WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
