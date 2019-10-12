package DAO;

import classes.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thairam Michel
 * @param <T>
 */
public class ItemDao<T> extends Dao<T> {

    public ItemDao() {
        super("item");
    }

    @Override
    protected T getObject(ResultSet resultSet) throws SQLException {
        Emprestimo emprestimo = null;
        Quadrinho quadrinho = null;

        if (resultSet.getInt("id_emprestimo") != 0) {
            EmprestimoDao dao = new EmprestimoDao();
            emprestimo = (Emprestimo) dao.find(resultSet.getInt("id_emprestimo"));
        }

        if (resultSet.getInt("id_quadrinho") != 0) {
            QuadrinhoDao dao = new QuadrinhoDao();
            quadrinho = (Quadrinho) dao.find(resultSet.getInt("id_quadrinho"));
        }

        return ((T) new Item(resultSet.getInt("id"),
                emprestimo,
                quadrinho,
                resultSet.getString("versao")
        ));
    }
}
