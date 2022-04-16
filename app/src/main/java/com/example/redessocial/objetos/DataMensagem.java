package com.example.redessocial.objetos;

public class DataMensagem {

    int idMandou = 0;
    int idRecebeu = 0;
    String mensagem = "";
    String hora = "";

    public DataMensagem(int idMandou, int idRecebeu, String mensagem, String hora) {
        this.idMandou = idMandou;
        this.idRecebeu = idRecebeu;
        this.mensagem = mensagem;
        this.hora = hora;
    }

    public int getIdMandou() {
        return idMandou;
    }

    public void setIdMandou(int idMandou) {
        this.idMandou = idMandou;
    }

    public int getIdRecebeu() {
        return idRecebeu;
    }

    public void setIdRecebeu(int idRecebeu) {
        this.idRecebeu = idRecebeu;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
