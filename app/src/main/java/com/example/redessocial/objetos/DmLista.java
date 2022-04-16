package com.example.redessocial.objetos;

public class DmLista {

    String nomeConversando = "";
    String mensagem = "";
    int idUser = 0;

    public DmLista(String nomeConversando, String mensagem,int idUser) {
        this.nomeConversando = nomeConversando;
        this.mensagem = mensagem;
        this.idUser = idUser;
    }

    public String getNomeConversando() {
        return nomeConversando;
    }

    public void setNomeConversando(String nomeConversando) {
        this.nomeConversando = nomeConversando;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
