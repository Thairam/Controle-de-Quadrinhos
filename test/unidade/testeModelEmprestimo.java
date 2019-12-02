package unidade;

import java.util.Calendar;
import static junit.framework.Assert.assertEquals;
import model.Amigo;
import model.Emprestimo;
import model.Utils;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeModelEmprestimo {

    Emprestimo emprestimoPadrao = new Emprestimo();
    Calendar dataEmprestimo = Utils.stringToCalend("01/12/2019");
    Calendar dataDevolucao = Utils.stringToCalend("10/12/2019");
    String estado = "ABERTO";
    Amigo amigoMock = getAmigoMock();

    public void configuracao() {
        emprestimoPadrao.setId(1);
        emprestimoPadrao.setAmigo(amigoMock);
        emprestimoPadrao.setDataEmprestimo(dataEmprestimo);
        emprestimoPadrao.setDataDevolucao(dataDevolucao);
        emprestimoPadrao.setEstado(estado);
    }

    @Test
    public void testaConstrutor() {
        Emprestimo emprestimo = new Emprestimo(amigoMock, dataEmprestimo, dataDevolucao, estado);
        assertEquals(emprestimo.getValues().equals(""), false);
    }

    @Test
    public void testaMetodoGetFields() {
        String campos = emprestimoPadrao.getFields();
        assertEquals(campos, "id_amigo, data_emprestimo, data_devolucao, estado");
    }

    @Test
    public void testaMetodoGetValues() {
        configuracao();
        String valores = emprestimoPadrao.getValues();
        assertEquals(valores.contains("1"), true);
        assertEquals(valores.contains("2019/12/01"), true);
        assertEquals(valores.contains("2019/12/10"), true);
        assertEquals(valores.contains(estado), true);
    }

    @Test
    public void testaMetodosGetKey() {
        configuracao();
        assertEquals(emprestimoPadrao.getKeyField(), "id");
        assertEquals(emprestimoPadrao.getKeyValue(), 1);
    }

    public Amigo getAmigoMock() {
        Calendar dataNascimento = Utils.stringToCalend("25/10/1994");
        Amigo amigo = new Amigo(1, null, "Thairam", dataNascimento, "111.222.333-10", "083 98866--7755", "thairam@gmail.com");

        return amigo;
    }

}
