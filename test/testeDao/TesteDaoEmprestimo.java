package testeDao;

import DAO.*;
import classes.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class TesteDaoEmprestimo {

    Dao<Emprestimo> dao;

    @Before
    public void setUp() {
        dao = new EmprestimoDao<>();
    }

    public TesteDaoEmprestimo() {
    }

    @Test
    public void testeQueryEmprestimo() {
        ArrayList<Emprestimo> lista;
        lista = dao.query("SELECT * FROM EMPRESTIMO");

        System.out.println(lista.size() + " registros.");
        for (Emprestimo e : lista) {
            System.out.println("Amigo: " + e.getAmigo().getNome());
            System.out.println("Data do emprestimo: " + Utils.calendToString(e.getDataEmprestimo()));
            System.out.println("Data do devolucao: " + Utils.calendToString(e.getDataDevolucao()));
            System.out.println("Estado: " + e.getEstado());
            System.out.println(e.getValues());
        }
    }
}
