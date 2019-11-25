/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thairam Michel
 */
public class EnderecoDao extends Dao {

    public EnderecoDao() {
        super("endereco");
    }

    @Override
    protected Object getObject(ResultSet resultSet) throws SQLException {
        return new Endereco(
                rs.getInt("id"),
                rs.getInt("cep"),
                rs.getString("rua"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("uf")
        );
    }
}
