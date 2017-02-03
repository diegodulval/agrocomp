package com.agrocomp.model.entity;

import com.agrocomp.model.base.BaseEntity;

//id sendo herdado da classe Mae BaseEntity.
//Categoria é uma Classe POJO que sofrerá persistencia no Banco / Entity
public class Categoria extends BaseEntity{
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
