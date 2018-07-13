package com.agrocomp.model.service;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.base.service.BaseUsuarioService;
import com.agrocomp.model.criteria.UsuarioCriteria;
import com.agrocomp.model.dao.UsuarioDAO;
import com.agrocomp.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioService implements BaseUsuarioService {

    @Override
    public Usuario login(String email, String senha) throws Exception {
        senha = Usuario.encodeSenha(senha);
        Connection conn = ConnectionManager.getInstance().getConnection();
        Usuario usuario = null;
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, email);
        criteria.put(UsuarioCriteria.SENHA_EQ, senha);
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarioList = dao.readByCriteria(conn, criteria, 0L, 0L);
        if (usuarioList.size() == 1) {
            Usuario aux = usuarioList.get(0);
            if (aux.getEmail().equals(email) && aux.getSenha().equals(senha)) {
                usuario = aux;
            }
        }
        conn.commit();
        conn.close();
        return usuario;
    }

}
