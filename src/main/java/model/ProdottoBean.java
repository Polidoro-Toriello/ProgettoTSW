package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {
    private int code, quantity;
    private String nome, descrizione, action, categoria;
    private float prezzo;

    public ProdottoBean() {

    }

    public ProdottoBean (int quantity, String nome, String descrizione, String action, String categoria, float prezzo) {
        this.quantity = quantity;
        this.nome = nome;
        this.descrizione = descrizione;
        this.action = action;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
