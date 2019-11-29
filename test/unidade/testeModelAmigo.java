package unidade;

import java.util.Calendar;
import static junit.framework.Assert.assertEquals;
import model.Amigo;
import model.Utils;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeModelAmigo {

    Amigo amigoPadrao = new Amigo();
    String nomePadrao = "Thairam Michel";
    Calendar dataNascimentoPadrao = Utils.stringToCalend("25/10/1994");
    String cpfPadrao = "111.222.333-44";
    String fonePadrao = "083 98877-6655";
    String emailPadrao = "thairam@hotmail.com";

    public void configuracao() {
        amigoPadrao.setId(10);
        amigoPadrao.setEndereco(null);
        amigoPadrao.setNome(nomePadrao);
        amigoPadrao.setDataNascimento(dataNascimentoPadrao);
        amigoPadrao.setCpf(cpfPadrao);
        amigoPadrao.setFone(fonePadrao);
        amigoPadrao.setEmail(emailPadrao);
    }

    @Test
    public void testaConstrutor1() {
        // informando id(0) e endereço(null)
        Amigo amigo = new Amigo(0, null, nomePadrao, dataNascimentoPadrao, cpfPadrao, fonePadrao, emailPadrao);
        assertEquals(amigo.getValues().equals(""), false);
    }

    @Test
    public void testaConstrutor2() {
        // informando apenas endereço(null)
        Amigo amigo = new Amigo(null, nomePadrao, dataNascimentoPadrao, cpfPadrao, fonePadrao, emailPadrao);
        assertEquals(amigo.getValues().equals(""), false);
    }

    @Test
    public void testaDefinicoesDeAtributos() {
        Amigo amigo = new Amigo();
        amigo.setId(1);
        amigo.setEndereco(null);
        amigo.setNome("João");
        amigo.setDataNascimento(dataNascimentoPadrao);
        amigo.setCpf("423.157.856-10");
        amigo.setFone("083 99622-3547");
        amigo.setEmail("joao@gmail.com");

        assertEquals(amigo.getId(), 1);
        assertEquals(amigo.getEndereco(), null);
        assertEquals(amigo.getNome(), "João");
        assertEquals(amigo.getDataNascimento(), dataNascimentoPadrao);
        assertEquals(amigo.getCpf(), "423.157.856-10");
        assertEquals(amigo.getFone(), "083 99622-3547");
        assertEquals(amigo.getEmail(), "joao@gmail.com");
    }

    @Test
    public void testaInstancia() {
        Amigo amigo = new Amigo(0, null, nomePadrao, dataNascimentoPadrao, cpfPadrao, fonePadrao, emailPadrao);
        assertEquals(amigo.getId(), 0);
        assertEquals(amigo.getEndereco(), null);
        assertEquals(amigo.getNome(), nomePadrao);
        assertEquals(amigo.getDataNascimento(), dataNascimentoPadrao);
        assertEquals(amigo.getCpf(), cpfPadrao);
        assertEquals(amigo.getFone(), fonePadrao);
        assertEquals(amigo.getEmail(), emailPadrao);
    }

    @Test
    public void testaMetodoGetFields() {
        String campos = amigoPadrao.getFields();
        assertEquals(campos, "id_endereco, nome, data_nascimento, cpf, fone, email");
    }

    @Test
    public void testaMetodoGetValues() {
        configuracao();
        String valores = amigoPadrao.getValues();
        assertEquals(valores.contains("null"), true);
        assertEquals(valores.contains("Thairam Michel"), true);
        assertEquals(valores.contains("1994/10/25"), true);
        assertEquals(valores.contains("083 98877-6655"), true);
        assertEquals(valores.contains("thairam@hotmail.com"), true);
    }

    @Test
    public void testaMetodosGetKey() {
        configuracao();
        assertEquals(amigoPadrao.getKeyField(), "id");
        assertEquals(amigoPadrao.getKeyValue(), 10);
    }
}
