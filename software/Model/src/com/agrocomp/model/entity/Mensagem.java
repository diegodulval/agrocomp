package com.agrocomp.model.entity;

import com.agrocomp.model.base.BaseEntity;
import java.sql.Timestamp;

public class Mensagem extends BaseEntity {

    private String mensagem;
    private Timestamp dataHora;
    private Cliente cliente;
    private Discussao discussao;

    public Discussao getDiscussao() {
        return discussao;
    }

    public void setDiscussao(Discussao discussao) {
        this.discussao = discussao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
}
