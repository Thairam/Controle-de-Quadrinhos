/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Entidades.Entity;

/**
 *
 * @author Thairam Michel
 */
public class Endereco implements Entity {

    private int id;
    private int cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco() {
    }

    public Endereco(int id, int cep, String rua, String bairro, String cidade, String uf) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Endereco(int cep, String rua, String bairro, String cidade, String uf) {
        this(0, cep, rua, bairro, cidade, uf);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String getFields() {
        return "cep, rua, bairro, cidade, uf";
    }

    @Override
    public String getValues() {
        return cep + ", " + Utils.quotedStr(rua) + ", " + Utils.quotedStr(bairro) + ", " + Utils.quotedStr(cidade) + ", " + Utils.quotedStr(uf);
    }

    @Override
    public String getKeyField() {
        return "id";
    }

    @Override
    public int getKeyValue() {
        return this.id;
    }

    @Override
    public void setKeyValue(int value) {
        setId(value);
    }

}
