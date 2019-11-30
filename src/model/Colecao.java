package model;

import interfaces.Entity;

/**
 *
 * @author Thairam Michel
 */
public class Colecao extends Object implements Entity {

    private int id;
    private String nome;
    private int tamanhoTotal;

    public Colecao(int id, String nome, int tamTotal) {
        this.id = id;
        this.nome = nome;
        this.tamanhoTotal = tamTotal;
    }

    public Colecao(String nome, int tamTotal) {
        this(0, nome, tamTotal);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeTotalDeQuadrinhos() {
        return tamanhoTotal;
    }

    public void setQuantidadeTotalDeQuadrinhos(int quantidade) {
        this.tamanhoTotal = quantidade;
    }

    @Override
    public String getFields() {
        return "nome, tam_total";
    }

    @Override
    public String getValues() {
        return Utils.quotedStr(getNome()) + ","
                + getQuantidadeTotalDeQuadrinhos();
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
