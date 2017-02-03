package com.agrocomp.model.base.service;

import com.agrocomp.model.entity.Usuario;

public interface BaseUsuarioService {

    public Usuario login(String email, String senha) throws Exception;


}
