package com.agrocomp.model;

import com.agrocomp.model.entity.Administrador;
import com.agrocomp.model.service.AdministradorService;

public class Main {

    public static void main(String[] args) throws Exception {

        Administrador administrador = new Administrador();
        administrador.setEmail("admin@gmail.com");
        administrador.setNome("Administrador");
        administrador.setSenhaAsPlainText("123456");

        AdministradorService s = new AdministradorService();
        s.create(administrador);

    }

}
