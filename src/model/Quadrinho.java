package model;

import interfaces.Entity;

/**
 *
 * @author Thairam Michel
 */
public class Quadrinho extends Object implements Entity {

    private int id;
    private Colecao colecao;
    private String nome;
    private double valor;
    private String editora;
    private String isbn;
    private boolean versaoFisica;
    private boolean versaoDigital;
    private String edicao;
    private String genero;
    private boolean disponibilidadeF;
    private boolean disponibilidadeD;
    private String curiosidade;
    private double nota;
    private boolean recomendavel;

    public Quadrinho() {
    }

    public Quadrinho(int id, Colecao colecao, String nome, double valor, String editora,
            String ISBN, boolean versaoFisica, boolean versaoDigital, String edicao,
            String genero, boolean disponibilidadeF, boolean disponibilidadeD, String curiosidade, double nota, boolean recomendavel
    ) {
        this.id = id;
        this.colecao = colecao;
        this.nome = nome;
        this.valor = valor;
        this.editora = editora;
        this.isbn = ISBN;
        this.versaoFisica = versaoFisica;
        this.versaoDigital = versaoDigital;
        this.edicao = edicao;
        this.genero = genero;
        this.disponibilidadeF = disponibilidadeF;
        this.disponibilidadeD = disponibilidadeD;
        this.curiosidade = curiosidade;
        this.nota = nota;
        this.recomendavel = recomendavel;
    }

    public Quadrinho(Colecao colecao, String nome, double valor, String editora,
            String ISBN, boolean versaoFisica, boolean versaoDigital, String edicao,
            String genero, boolean disponibilidadeF, boolean disponibilidadeD, String curiosidade, double nota, boolean recomendavel
    ) {
        this(0, colecao, nome, valor, editora, ISBN, versaoFisica, versaoDigital, edicao, genero,
                disponibilidadeF, disponibilidadeD, curiosidade, nota, recomendavel);
    }

    public Colecao getColecao() {
        return colecao;
    }

    public void setColecao(Colecao colecao) {
        this.colecao = colecao;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String ISBN) {
        this.isbn = ISBN;
    }

    public boolean isVersaoFisica() {
        return versaoFisica;
    }

    public void setVersaoFisica(boolean versaoFisica) {
        this.versaoFisica = versaoFisica;
    }

    public boolean isVersaoDigital() {
        return versaoDigital;
    }

    public void setVersaoDigital(boolean versaoDigital) {
        this.versaoDigital = versaoDigital;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getDisponibilidadeFisica() {
        return disponibilidadeF;
    }

    public void setDisponibilidadeFisica(boolean disponibilidadeF) {
        this.disponibilidadeF = disponibilidadeF;
    }

    public boolean getDisponibilidadeDigital() {
        return disponibilidadeD;
    }

    public void setDisponibilidadeDigital(boolean disponibilidadeD) {
        this.disponibilidadeD = disponibilidadeD;
    }

    public String getCuriosidade() {
        return curiosidade;
    }

    public void setCuriosidade(String curiosidade) {
        this.curiosidade = curiosidade;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean isRecomendavel() {
        return recomendavel;
    }

    public void setRecomendavel(boolean recomendavel) {
        this.recomendavel = recomendavel;
    }

    @Override
    public String getFields() {
        return "id_colecao, nome, valor, editora, isbn, versao_fisica, versao_digital, edicao, genero, disponibilidadeF, disponibilidadeD, curiosidade, nota, recomendavel";
    }

    @Override
    public String getValues() {
        return (colecao != null ? colecao.getId() : "null") + ", "
                + Utils.quotedStr(getNome()) + ","
                + getValor() + ","
                + Utils.quotedStr(getEditora()) + ","
                + Utils.quotedStr(getIsbn()) + ","
                + versaoFisica + ","
                + versaoDigital + ","
                + Utils.quotedStr(getEdicao()) + ","
                + Utils.quotedStr(getGenero()) + ","
                + disponibilidadeF + ","
                + disponibilidadeD + ","
                + Utils.quotedStr(getCuriosidade()) + ","
                + nota + ","
                + recomendavel;
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
