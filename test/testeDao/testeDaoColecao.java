package testeDao;

import DAO.ColecaoDao;
import DAO.Dao;
import classes.Colecao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thairam Michel
 */
public class testeDaoColecao {

    Dao<Colecao> dao;

    @Before
    public void setUp() {
        dao = new ColecaoDao<>();
    }

    public testeDaoColecao() {
    }

    @Test
    public void testFindColecao() throws SQLException {
        System.out.println("=======teste find==========");
        Colecao colecao1 = dao.find(1);
        System.out.println("Nome: " + colecao1.getNome());
        System.out.println("Quantidade: " + colecao1.getQuantidade());
        System.out.println("===========================");
    }

    @Test
    public void testQueryColecao() throws SQLException {
        System.out.println("=======teste query==========");
        ArrayList<Colecao> lista = dao.query("SELECT * FROM colecao");
        System.out.println(lista.size() + " registros.");
        for (Colecao colecao : lista) {
            System.out.println("Nome: " + colecao.getNome());
            System.out.println("Quantidade: " + colecao.getQuantidade());
            System.out.println(colecao.getValues());
            System.out.println("");
        }
    }
}
