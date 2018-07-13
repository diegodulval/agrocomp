package com.agrocomp.model.base.service;

import com.agrocomp.model.base.BaseCRUDService;
import com.agrocomp.model.entity.Categoria;

//A categoria herda os metodos declarados em BaseCRUD
//Para que os métodos possam ser implementados corretamente é necessario
//Enviar a Classe Categoria como paramentro para a geração dos métodos corretamente.
public interface BaseCategoriaService extends BaseCRUDService<Categoria> {

}
