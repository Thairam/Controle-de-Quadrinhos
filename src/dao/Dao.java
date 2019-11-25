package dao;

import interfaces.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Thairam Michel
 * @param <T> tipo de objeto manipulado
 */
public abstract class Dao<T> {

    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    protected String table;

    /**
     * Cria uma Dao para a tabela table
     *
     * @param table
     */
    public Dao(String table) {
        this.table = table;
    }

    public String getTable() {
        return this.table;
    }

    /**
     * Adiciona na tabela table um registro para o objeto ent, que implementa a
     * interface Entity
     *
     * @param ent
     */
    public void insert(Entity ent) throws Exception {
        con = ConnectionFactory.getConnection();

        try {
            stmt = con.prepareStatement("INSERT INTO " + table + "(" + ent.getFields() + ") VALUES (" + ent.getValues() + ")", 1);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            ent.setKeyValue(keys.getInt(1));

        } catch (SQLException ex) {
            throw new Exception(ex);
//            JOptionPane.showMessageDialog(null, "Falha ao salvar!\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {

            try {
                ConnectionFactory.closeConnection(con, stmt);
            } catch (SQLException ex) {
                //
            }

        }
    }

    /**
     * Atualiza na tabela table um registro referente ao objeto ent, que
     * implementa a interface Entity
     *
     * @param ent
     */
    public void update(Entity ent) throws Exception {
        con = ConnectionFactory.getConnection();
        String[] fields = ent.getFields().split(",");
        String[] values = ent.getValues().split(",");

        String sql = "UPDATE " + table + " SET ";

        sql += fields[0] + " = " + values[0];
        for (int i = 1; i < fields.length; i++) {
            sql += ", " + fields[i] + " = " + values[i];
        }

        sql += " WHERE " + ent.getKeyField() + " = " + ent.getKeyValue();

        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception(ex);
//            JOptionPane.showMessageDialog(null, "Falha ao salvar!\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt);
            } catch (SQLException ex) {
                //
            }
        }
    }

    /**
     * Remove da tabela table a tupla referente ao objeto ent
     *
     * @param ent
     */
    public void delete(Entity ent) throws Exception {
        con = ConnectionFactory.getConnection();

        try {
            stmt = con.prepareStatement("DELETE FROM " + table + " WHERE " + ent.getKeyField() + " = " + ent.getKeyValue());
            stmt.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
//            JOptionPane.showMessageDialog(null, "Falha ao excluir!\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt);
            } catch (SQLException ex) {
                //
            }
        }
    }

    /**
     * Executa uma instrução SQL
     *
     * @param sql
     */
    public void executeSql(String sql) {
        con = ConnectionFactory.getConnection();

        try {
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao executar operação!\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt);
            } catch (SQLException ex) {
                //
            }
        }
    }

    /**
     * Realiza uma consulta na tabela e retorna um ArrayList com os elementos em
     * objetos
     *
     * @param sql
     * @return
     */
    public ArrayList<T> query(String sql) throws Exception {
        ArrayList<T> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(getObject(rs));
            }

            return lista;
        } catch (SQLException ex) {
            throw new Exception(ex);
//            JOptionPane.showMessageDialog(null, "Falha ao executar consulta!\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt, rs);
            } catch (SQLException ex) {
                //
            }
        }
    }

    /**
     * Busca um registro específico e retorna em um objeto do tipo
     *
     * @param id
     * @return
     */
    public T find(int id) throws Exception {
        ArrayList<T> lista = query("SELECT * FROM " + table + " WHERE id = " + id);

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    /**
     * Busca um registro específico e retorna em um objeto do tipo
     *
     * @param fields
     * @param values
     * @return
     */
    public T find(String[] fields, String[] values) throws Exception {
        String sql = "SELECT * FROM " + getTable() + " WHERE " + fields[0] + " = " + values[0];
        for (int i = 1; i < fields.length; i++) {
            sql += " AND " + fields[i] + " = " + values[i];
        }

        ArrayList<T> lista = query(sql);

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    /**
     * Método abstrato a ser implementado em todas as especializações de Dao,
     * que retornará o objeto específico de cada tipo de Dao
     *
     * @param resultSet
     * @return
     */
    protected abstract T getObject(ResultSet resultSet) throws SQLException;

}
