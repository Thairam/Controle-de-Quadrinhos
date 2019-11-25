package model;

import interfaces.Entity;
import java.util.Calendar;

/**
 *
 * @author Thairam Michel
 */
public class Emprestimo extends Object implements Entity {

    private int id;
    private Amigo amigo;
    private Calendar dataEmprestimo;
    private Calendar dataDevolucao;
    private String estado;

    public Emprestimo() {
    }

    public Emprestimo(int id, Amigo amigo, Calendar dataEmprestimo, Calendar dataDevolucao, String estado) {
        this.id = id;
        this.amigo = amigo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.estado = estado;
    }

    public Emprestimo(Amigo amigo, Calendar dataEmprestimo, Calendar dataDevolucao, String estado) {
        this(0, amigo, dataEmprestimo, dataDevolucao, estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }

    public Calendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Calendar getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String getFields() {
        return "id, data_emprestimo, data_devolucao, id_quadrinho, id_amigo, estado";
    }

    @Override
    public String getValues() {
        return (dataEmprestimo != null ? "'" + Utils.calendToString(dataEmprestimo, "yyyy/MM/dd") + "'" : "null") + ", "
                + (dataDevolucao != null ? "'" + Utils.calendToString(dataDevolucao, "yyyy/MM/dd") + "'" : "null") + ", "
                + (amigo != null ? amigo.getId() + ", " : "null, ")
                + estado;
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
