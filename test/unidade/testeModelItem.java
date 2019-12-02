package unidade;

import java.util.Calendar;
import static junit.framework.Assert.assertEquals;
import model.Item;
import model.Amigo;
import model.Emprestimo;
import model.Quadrinho;
import model.Utils;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeModelItem {

    Item itemPadrao = new Item();
    Emprestimo emprestimoMock = getEmprestimoMock();
    Amigo amigoMock = getAmigoMock();
    Quadrinho quadrinhoMock = getQuadrinhoMock();

    public void configuracao() {
        itemPadrao.setId(1);
        itemPadrao.setEmprestimo(emprestimoMock);
        itemPadrao.setQuadrinho(quadrinhoMock);
        itemPadrao.setVersao("F");
    }

    @Test
    public void testaConstrutor() {
        Item item = new Item(emprestimoMock, quadrinhoMock, "F");
        assertEquals(item.getValues().equals(""), false);
    }

    @Test
    public void testaMetodoGetFields() {
        String campos = itemPadrao.getFields();
        assertEquals(campos, "id_emprestimo, id_quadrinho, versao");
    }

    @Test
    public void testaMetodoGetValues() {
        configuracao();
        String valores = itemPadrao.getValues();
        assertEquals(valores.contains(Integer.toString(emprestimoMock.getId())), true);
        assertEquals(valores.contains(Integer.toString(amigoMock.getId())), true);
        assertEquals(valores.contains("F"), true);
    }

    @Test
    public void testaMetodosGetKey() {
        configuracao();
        assertEquals(itemPadrao.getKeyField(), "id");
        assertEquals(itemPadrao.getKeyValue(), 1);
    }

    public Quadrinho getQuadrinhoMock() {
        String nome = "Watchmen";
        Double valor = 16.90;
        String editora = "DC Comics";
        String isbn = "0189554710253";
        boolean versao_fisica = true;
        boolean versao_digital = true;
        String edicao = "edição especial";
        String genero = "ficção cientíica";
        boolean disponibilidadeF = true;
        boolean disponibilidadeD = true;
        String curiosidade = "Serviu de inspiração para o filme";
        double nota = 10;
        boolean recomendavel = true;
        Quadrinho quadrinho = new Quadrinho(1, null, nome, valor, editora, isbn, versao_fisica, versao_digital,
                edicao, genero, disponibilidadeF, disponibilidadeD, curiosidade, nota, recomendavel);

        return quadrinho;
    }

    public Amigo getAmigoMock() {
        Calendar dataNascimento = Utils.stringToCalend("25/10/1994");
        Amigo amigo = new Amigo(1, null, "Thairam", dataNascimento, "111.222.333-10", "083 98866--7755", "thairam@gmail.com");

        return amigo;
    }

    public Emprestimo getEmprestimoMock() {
        Calendar dataEmprestimo = Utils.stringToCalend("01/12/2019");
        Calendar dataDevolucao = Utils.stringToCalend("10/12/2019");
        Emprestimo emprestimo = new Emprestimo(1, amigoMock, dataEmprestimo, dataDevolucao, "ABERTO");

        return emprestimo;
    }

}
