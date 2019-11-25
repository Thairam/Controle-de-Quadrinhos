package model;

import interfaces.Entity;

/**
 *
 * @author Thairam Michel
 */
public class Colecao extends Object implements Entity {

    private int id;
    private String nome;
    private int qtd_quadrinhos;

    public Colecao(int id, String nome, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.qtd_quadrinhos = quantidade;
    }

    public Colecao(String nome, int quantidade) {
        this(0, nome, quantidade);
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

    public int getQuantidade() {
        return qtd_quadrinhos;
    }

    public void setQuantidade(int quantidade) {
        this.qtd_quadrinhos = quantidade;
    }

    @Override
    public String getFields() {
        return "nome, qtd_quadrinhos";
    }

    @Override
    public String getValues() {
        return Utils.quotedStr(getNome()) + ","
                + getQuantidade();
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
