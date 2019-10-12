package testeDao;

import DAO.AmigoDao;
import DAO.Dao;
import classes.Amigo;
import classes.Utils;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class TesteDaoAmigo {

    Dao<Amigo> dao;
    Amigo defaultAmigo = new Amigo();

    @Before
    public void setup() {
        dao = new AmigoDao<>();
    }

    public TesteDaoAmigo() {
    }

    @Test
    public void Tests() throws SQLException {
        testaQueryAmigo();
        testeInsereAmigo();
        testeFindAmigo();
        testeDeleteAmigo();
    }

    public void testaQueryAmigo() throws SQLException {
        ArrayList<Amigo> lista;

        lista = dao.query("SELECT * FROM AMIGO");
        assertEquals(lista.size() >= 3, true);
        
        lista = dao.query("SELECT * FROM AMIGO WHERE amigo.nome = 'Amigo_Inexistente'");
        assertEquals(lista, new ArrayList<Object>());
    }

    public void testeInsereAmigo() {
        defaultAmigo.setNome("Malaquias");
        defaultAmigo.setDataNascimento(Utils.stringToCalend("28/02/2000"));
        defaultAmigo.setCpf("555.555.555-55");
        defaultAmigo.setEndereco(null);
        defaultAmigo.setEmail("malaquias@bytecode.com");
        defaultAmigo.setFone("(10) 10010-0101");
        dao.insert(defaultAmigo);
        Amigo a2 = dao.find(defaultAmigo.getId());
        assertEquals(defaultAmigo.getNome(), a2.getNome());
    }

    public void testeFindAmigo() {
        Amigo amigo = dao.find(1); // Thairam
        assertEquals(amigo.getNome(), "Thairam");
        assertEquals(amigo.getCpf(), "123.456.789-00");
        assertEquals(amigo.getId(), 1);
    }

    public void testeDeleteAmigo() {
        System.out.println("Excluindo: " + defaultAmigo.getNome());
        dao.delete(defaultAmigo);
        defaultAmigo = dao.find(defaultAmigo.getId());
        assertNull(defaultAmigo);
    }
}
