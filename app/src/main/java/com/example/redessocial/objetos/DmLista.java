package com.example.redessocial.objetos;

public class DmLista {

    String nomeConversando = "";
    String mensagem = "";
    int idUser = 0;
    byte[] imagem = null;

    public DmLista(String nomeConversando, String mensagem,int idUser, byte[] imagem) {
        this.nomeConversando = nomeConversando;
        this.mensagem = mensagem;
        this.idUser = idUser;
        this.imagem = imagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
