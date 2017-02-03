package com.agrocomp.model.entity;

import com.agrocomp.model.base.BaseEntity;
import java.sql.Timestamp;


public class Discussao extends BaseEntity{
    private String titulo;
    private String pergunta;
    private Timestamp dataHora;
    private Long numMensagem;
    private Cliente cliente;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getNumMensagem() {
        return numMensagem;
    }

    public void setNumMensagem(Long numMensagem) {
        this.numMensagem = numMensagem;
    }
    
    
}
