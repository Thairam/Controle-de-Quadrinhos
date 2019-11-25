package dao;

import model.Item;
import model.Emprestimo;
import model.Quadrinho;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try {
                emprestimo = (Emprestimo) dao.find(resultSet.getInt("id_emprestimo"));
            } catch (Exception ex) {
            }
        }

        if (resultSet.getInt("id_quadrinho") != 0) {
            QuadrinhoDao dao = new QuadrinhoDao();
            try {
                quadrinho = (Quadrinho) dao.find(resultSet.getInt("id_quadrinho"));
            } catch (Exception ex) {
            }
        }

        return ((T) new Item(resultSet.getInt("id"),
                emprestimo,
                quadrinho,
                resultSet.getString("versao")
        ));
    }
}
