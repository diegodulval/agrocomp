
package com.agrocomp.model.entity;

import com.agrocomp.model.base.BaseEntity;
import java.sql.Timestamp;

import javafx.scene.text.Text;


public class Reporte extends BaseEntity{
    private String descricao;
    private Timestamp dataHora;
    private Anuncio anuncio;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
