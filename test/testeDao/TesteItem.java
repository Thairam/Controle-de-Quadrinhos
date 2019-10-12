package testeDao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import DAO.*;
import classes.*;

/**
 *
 * @author Thairam Michel
 */
public class TesteItem {

    Dao<Item> dao;

    @Before
    public void setUp() {
        dao = new ItemDao<>();
    }

    @Test
    public void t1() {
        Item item = dao.find(1);
        Quadrinho q = item.getQuadrinho();
        Emprestimo e = item.getEmprestimo();
        System.out.println(q.getValues());
        System.out.println(e.getValues());
    }
}
