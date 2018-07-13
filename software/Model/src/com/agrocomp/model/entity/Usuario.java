package com.agrocomp.model.entity;

import com.agrocomp.model.base.BaseEntity;
import java.security.MessageDigest;

public abstract class Usuario extends BaseEntity {

    private static String SALT_PREFIX_SENHA = "a$4oc0mP";
    private static String SALT_SUFIX_SENHA = "53QquEla";

    private String nome;
    private String email;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSenhaAsPlainText(String senhaAsPlainText) throws Exception {
        this.senha = encodeSenha(senhaAsPlainText);
    }

    public static String encodeSenha(String senhaAsPlainText) throws Exception {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest((SALT_PREFIX_SENHA + senhaAsPlainText + SALT_SUFIX_SENHA).getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

}
