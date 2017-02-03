package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseClienteService;
import com.agrocomp.model.criteria.UsuarioCriteria;
import com.agrocomp.model.dao.ClienteDAO;
import com.agrocomp.model.dao.UsuarioDAO;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteService implements BaseClienteService {

    @Override
    public void create(Cliente entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
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
    public Cliente readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Cliente cliente = null;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            cliente = (Cliente) dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return cliente;
    }

    @Override
    public List<Cliente> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {

        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Cliente> clienteList = null;
        try {
            ClienteDAO dao = new ClienteDAO();
            clienteList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return clienteList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ClienteDAO dao = new ClienteDAO();
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
            UsuarioDAO dao = new UsuarioDAO();
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
    public void updateUser(Cliente entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
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
    public Map<String, String> validate(Map<String, Object> filds) throws Exception {
        Map<String, String> errors = new HashMap<>();
        String nome = (String) filds.get("nome");
        String email = (String) filds.get("email");
        String senha = (String) filds.get("senha");

        if (nome == null || nome.trim().isEmpty()) {
            errors.put("nome", "Insira o seu nome");
        }

        if (email == null || email.trim().isEmpty()) {

            errors.put("email", "Insira um email");

        } else {
            HashMap<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.EMAIL_EQ, email);
            List<Usuario> usuarioList = this.readByCrit(criteria);
            if (!usuarioList.isEmpty()) {
                errors.put("email", "Email em questão já está em uso");
            }

        }

        if (senha == null || senha.trim().isEmpty() || senha.length() < 6) {
            errors.put("senha", "Senha invalida");
        }

        return errors;

    }

    private List<Usuario> readByCrit(Map<Long, Object> criteria) throws Exception {

        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Usuario> usuarioList = null;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            usuarioList = dao.readByCriteria(conn, criteria, 0L, 0L);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return usuarioList;
    }

}
