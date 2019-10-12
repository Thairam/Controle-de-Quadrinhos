package DAO;

import classes.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Thairam Michel
 * @param <T>
 */
public class QuadrinhoDao<T> extends Dao<T> {

    public QuadrinhoDao() {
        super("quadrinho");
    }

    @Override
    protected T getObject(ResultSet resultSet) throws SQLException {
        Colecao colecao = null;

        if (resultSet.getInt("id_colecao") != 0) {
            ColecaoDao dao = new ColecaoDao();
            colecao = (Colecao) dao.find(resultSet.getInt("id_colecao"));
        }

        return ((T) new Quadrinho(resultSet.getInt("id"),
                colecao,
                resultSet.getString("nome"),
                resultSet.getDouble("valor"),
                resultSet.getString("editora"),
                resultSet.getString("isbn"),
                resultSet.getBoolean("versao_fisica"),
                resultSet.getBoolean("versao_digital"),
                resultSet.getString("edicao"),
                resultSet.getString("genero"),
                resultSet.getBoolean("disponibilidade"),
                resultSet.getString("curiosidade"),
                resultSet.getDouble("nota"),
                resultSet.getBoolean("recomendavel")
        ));
    }
}
