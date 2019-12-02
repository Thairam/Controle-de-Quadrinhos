package unidade;

import static junit.framework.Assert.assertEquals;
import model.Quadrinho;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeModelQuadrinho {

    Quadrinho quadrinhoPadrao = new Quadrinho();
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

    public void configuracao() {
        quadrinhoPadrao.setId(1);
        quadrinhoPadrao.setNome(nome);
        quadrinhoPadrao.setValor(valor);
        quadrinhoPadrao.setEditora(editora);
        quadrinhoPadrao.setIsbn(isbn);
        quadrinhoPadrao.setVersaoFisica(versao_fisica);
        quadrinhoPadrao.setVersaoDigital(versao_digital);
        quadrinhoPadrao.setEdicao(edicao);
        quadrinhoPadrao.setGenero(genero);
        quadrinhoPadrao.setDisponibilidadeFisica(disponibilidadeF);
        quadrinhoPadrao.setDisponibilidadeDigital(disponibilidadeD);
        quadrinhoPadrao.setCuriosidade(curiosidade);
        quadrinhoPadrao.setNota(nota);
        quadrinhoPadrao.setRecomendavel(recomendavel);
    }

    @Test
    public void testaConstrutor() {
        Quadrinho quadrinho = new Quadrinho(null, nome, 0, editora, isbn, versao_fisica, versao_digital,
                edicao, genero, disponibilidadeF, disponibilidadeD, curiosidade, 0, recomendavel);
        assertEquals(quadrinho.getValues().equals(""), false);
    }

    @Test
    public void testaMetodoGetFields() {
        String campos = quadrinhoPadrao.getFields();
        assertEquals(campos, "id_colecao, nome, valor, editora, isbn, versao_fisica, "
                + "versao_digital, edicao, genero, disponibilidadeF, disponibilidadeD, "
                + "curiosidade, nota, recomendavel");

    }

    @Test
    public void testeMetodoGetValues() {
        configuracao();
        System.out.println(quadrinhoPadrao.getValues());
        String valores = quadrinhoPadrao.getValues();
        assertEquals(valores.contains(nome), true);
        assertEquals(valores.contains("16.9"), true);
        assertEquals(valores.contains(editora), true);
        assertEquals(valores.contains(isbn), true);
        assertEquals(valores.contains(edicao), true);
        assertEquals(valores.contains(genero), true);
        assertEquals(valores.contains(curiosidade), true);
        assertEquals(valores.contains("10"), true);
    }

    @Test
    public void testaMetodosGetKey() {
        configuracao();
        assertEquals(quadrinhoPadrao.getKeyField(), "id");
        assertEquals(quadrinhoPadrao.getKeyValue(), 1);
    }
}
