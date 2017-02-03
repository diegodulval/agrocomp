//Aqui definimos o modelo base para o servicos que serão implementados no sistema
package com.agrocomp.model.base.service;

import com.agrocomp.model.base.BaseCRUDService;
import com.agrocomp.model.entity.Categoria;

//A categoria herda os metodos declarados em BaseCRUD
//Para que os métodos possam ser implementados corretamente e necessario
//Enviar a Classe Categoria como paramentro para a geração dos métodos corretamente.
public interface BaseCategoriaService extends BaseCRUDService<Categoria>{
   
    
}
