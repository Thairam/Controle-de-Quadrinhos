package model;

import interfaces.Entity;

/**
 *
 * @author Thairam Michel
 */
public class Item extends Object implements Entity {

    private int id;
    private Emprestimo emprestimo;
    private Quadrinho quadrinho;
    private String versao; // 0 - Fisica e 1 - Digital

    public Item() {
    }

    public Item(int id, Emprestimo emprestimo, Quadrinho quadrinho, String versao) {
        this.id = id;
        this.emprestimo = emprestimo;
        this.quadrinho = quadrinho;
        this.versao = versao;
    }

    public Item(Emprestimo emprestimo, Quadrinho quadrinho, String versao) {
        this(0, emprestimo, quadrinho, versao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Quadrinho getQuadrinho() {
        return quadrinho;
    }

    public void setQuadrinho(Quadrinho quadrinho) {
        this.quadrinho = quadrinho;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @Override
    public String getFields() {
        return ("id_emprestimo, id_quadrinho, versao");
    }

    @Override
    public String getValues() {
        return emprestimo.getId() + ", "
                + quadrinho.getId() + ", "
                + Utils.quotedStr(this.getVersao());
    }

    @Override
    public String getKeyField() {
        return "id";
    }

    @Override
    public int getKeyValue() {
        return getId();
    }

    @Override
    public void setKeyValue(int value) {
        setId(value);
    }
}
