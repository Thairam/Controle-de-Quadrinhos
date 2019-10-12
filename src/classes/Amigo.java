package classes;

import Entidades.Entity;
import java.util.Calendar;

/**
 * @author Thairam Michel
 */
public class Amigo extends Object implements Entity {

    private int id;
    private Endereco endereco;
    private String nome;
    private Calendar dataNascimento;
    private String cpf;
    private String fone;
    private String email;

    /**
     * Contrutor
     *
     * @param id
     * @param endereco
     * @param nome
     * @param dataNasc
     * @param cpf
     * @param fone
     * @param email
     */
    public Amigo(int id, Endereco endereco, String nome, Calendar dataNasc, String cpf, String fone, String email) {
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
        this.dataNascimento = dataNasc;
        this.cpf = cpf;
        this.fone = fone;
        this.email = email;
    }

    public Amigo(Endereco endereco, String nome, Calendar dataNasc, String cpf, String fone, String email) {
        this(0, endereco, nome, dataNasc, cpf, fone, email);
    }

    public Amigo() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFields() {
        return "id_endereco, nome, data_nascimento, cpf, fone, email";
    }

    @Override
    public String getValues() {
        return (endereco != null ? endereco.getId() : "null") + ", "
                + Utils.quotedStr(getNome()) + ", "
                + "'" + Utils.calendToString(dataNascimento, "yyyy/MM/dd") + "', "
                + Utils.quotedStr(getCpf()) + ", "
                + Utils.quotedStr(getFone()) + ", "
                + Utils.quotedStr(getEmail());
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
