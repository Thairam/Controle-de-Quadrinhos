package DAO;

import classes.Colecao;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thairam Michel
 * @param <T>
 */
public class ColecaoDao<T> extends Dao<T> {

    public ColecaoDao() {
        super("colecao");
    }

    @Override
    protected T getObject(ResultSet resultSet) throws SQLException {

        return ((T) new Colecao(resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getInt("qtd_quadrinhos")
        ));
    }

}
