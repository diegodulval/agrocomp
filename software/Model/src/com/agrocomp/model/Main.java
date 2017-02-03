package com.agrocomp.model;

import com.agrocomp.model.entity.Administrador;
import com.agrocomp.model.entity.Categoria;
import com.agrocomp.model.service.AdministradorService;
import com.agrocomp.model.service.CategoriaService;
import com.agrocomp.model.service.ClienteService;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Categoria> cat = new ArrayList<>();

        CategoriaService cs = new CategoriaService();
        Administrador administrador = new Administrador();
        administrador.setEmail("admin@gmail.com");
        administrador.setNome("Administrador");
        administrador.setSenhaAsPlainText("123456");

        AdministradorService s = new AdministradorService();
        s.create(administrador);
        

//        Map<Long,Object> criteria = new HashMap<>();
//        AdministradorService s = new AdministradorService();
//        List<Administrador> lista = s.readByCriteria(criteria, null, null);
        

    }

}
