package integracao;

import dao.*;
import controller.QuadrinhoController;
import java.util.ArrayList;
import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import model.Quadrinho;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeDaoQuadrinho {

    String nomePadrao = "Watchmen";
    Double valorPadrao = 16.90;
    String editoraPadrao = "DC Comics";
    String isbnPadrao = "0189554710253";
    boolean versao_fisica = true;
    boolean versao_digital = true;
    String edicaoPadrao = "edição especial";
    String generoPadrao = "ficção cientíica";
    boolean disponibilidadeF = true;
    boolean disponibilidadeD = true;
    String curiosidade = "Serviu de inspiração para o filme";
    double nota = 10;
    boolean recomendavel = true;

    Dao<Quadrinho> daoQuadrinho;
    ArrayList<Quadrinho> quadrinhos;
    Quadrinho quadrinhoPadrao = new Quadrinho();
    Quadrinho quadrinhoInvalido = new Quadrinho();
    QuadrinhoController quadrinhoControl = new QuadrinhoController();

    @After
    public void limpar() {
        quadrinhoControl.deletarQuadrinho(quadrinhoPadrao);
    }

    @Test
    public void InserirQuadrinhoComSucesso() {
        HashMap<Boolean, Object> resposta = quadrinhoControl
                .salvarQuadrinho(nomePadrao, valorPadrao, editoraPadrao, isbnPadrao, "s", "s", edicaoPadrao,
                        generoPadrao, curiosidade, nota, "s");

        quadrinhoPadrao = (Quadrinho) resposta.get(true);

        assertEquals(resposta.containsKey(true), true);
        assertEquals(quadrinhoPadrao.getNome(), nomePadrao);
        assertEquals(quadrinhoPadrao.getValor(), valorPadrao);
        assertEquals(quadrinhoPadrao.getEditora(), editoraPadrao);
        assertEquals(quadrinhoPadrao.getIsbn(), isbnPadrao);
        assertEquals(quadrinhoPadrao.isVersaoFisica(), versao_fisica);
        assertEquals(quadrinhoPadrao.isVersaoDigital(), versao_digital);
        assertEquals(quadrinhoPadrao.getEdicao(), edicaoPadrao);
        assertEquals(quadrinhoPadrao.getGenero(), generoPadrao);
        assertEquals(quadrinhoPadrao.getDisponibilidadeFisica(), disponibilidadeF);
        assertEquals(quadrinhoPadrao.getDisponibilidadeDigital(), disponibilidadeD);
        assertEquals(quadrinhoPadrao.getCuriosidade(), curiosidade);
        assertEquals(quadrinhoPadrao.getNota(), nota);
        assertEquals(quadrinhoPadrao.isRecomendavel(), recomendavel);
    }

    @Test
    public void InserirQuadrinhoDuplicado() {

        salvarQuadrinhoPadrao();

        HashMap<Boolean, Object> resposta = quadrinhoControl
                .salvarQuadrinho("novo quadringo", 25, "nova edicao", isbnPadrao, "s", "s", "primeira",
                        "ação", "", 7, "s");

        String erro = (String) resposta.get(false);
        assertEquals(resposta.containsKey(false), true);
        assertEquals(erro.contains("registro com o mesmo isbn"), true);
    }

    @Test
    public void InserirQuadrinhoComDadosInvalido() {
        String nome = "";
        double valor = -1;
        String editora = "";
        String isbn = "564564";

        HashMap<Boolean, Object> resposta = quadrinhoControl
                .salvarQuadrinho(nome, valor, editora, isbn, "s", "s", edicaoPadrao, generoPadrao, curiosidade,
                        nota, "s");

        String camposInvalidos = (String) resposta.get(false);
        assertEquals(resposta.containsKey(false), true);
        assertEquals(camposInvalidos, "nome, valor, editora, isbn");
    }

    @Test
    public void atualizarQuadrinhoComSucesso() {

        salvarQuadrinhoPadrao();

        String novoNome = "novo nome do quadrinho";
        double novoValor = 100;
        String novaCuriosidade = "nova curiosidade para o quadrinho";

        HashMap<Boolean, Object> respostaAtualizacao = quadrinhoControl
                .atualizarQuadrinho(quadrinhoPadrao, novoNome, novoValor, editoraPadrao, isbnPadrao, "s",
                        "s", edicaoPadrao, generoPadrao, novaCuriosidade, nota, "s");

        Quadrinho quadrinhoAtualizado = (Quadrinho) respostaAtualizacao.get(true);
        assertEquals(respostaAtualizacao.containsKey(true), true);
        assertEquals(quadrinhoAtualizado.getNome(), novoNome);
        assertEquals(quadrinhoAtualizado.getValor(), novoValor);
        assertEquals(quadrinhoAtualizado.getEditora(), editoraPadrao);
        assertEquals(quadrinhoAtualizado.getIsbn(), isbnPadrao);
        assertEquals(quadrinhoAtualizado.isVersaoFisica(), versao_fisica);
        assertEquals(quadrinhoAtualizado.isVersaoDigital(), versao_digital);
        assertEquals(quadrinhoAtualizado.getEdicao(), edicaoPadrao);
        assertEquals(quadrinhoAtualizado.getGenero(), generoPadrao);
        assertEquals(quadrinhoAtualizado.getDisponibilidadeFisica(), disponibilidadeF);
        assertEquals(quadrinhoAtualizado.getDisponibilidadeDigital(), disponibilidadeD);
        assertEquals(quadrinhoAtualizado.getCuriosidade(), novaCuriosidade);
        assertEquals(quadrinhoAtualizado.getNota(), nota);
        assertEquals(quadrinhoAtualizado.isRecomendavel(), recomendavel);
    }

    @Test
    public void obterTodosOsQuadrinhos() {
        salvarQuadrinhoPadrao();

        ArrayList<Quadrinho> resposta = quadrinhoControl.listarTodosOsQuadrinhos();
        assertEquals(resposta.size() >= 4, true); // Por padrão é criado 3 quadrinhos, na criação do banco de dados.
    }

    @Test
    public void buscarQuadrinhoPeloNome() {
        salvarQuadrinhoPadrao();

        HashMap<Boolean, Object> resposta = quadrinhoControl.buscarQuadrinhoPeloNome(quadrinhoPadrao.getNome());
        assertEquals(resposta.containsKey(true), true);
        assertEquals(((Quadrinho) resposta.get(true)).getNome(), quadrinhoPadrao.getNome());
    }

    @Test
    public void QuadrinhoNaoEncontrado() {
        HashMap<Boolean, Object> resposta = quadrinhoControl.buscarQuadrinhoPeloNome(quadrinhoPadrao.getNome());
        assertEquals(resposta.containsKey(false), true);
        assertEquals((String) resposta.get(false), "Quadrinho não encontrado");
    }

    @Test
    public void deletarQuadrinhoComSucesso() {
        salvarQuadrinhoPadrao();

        HashMap<Boolean, Object> resposta = quadrinhoControl.deletarQuadrinho(quadrinhoPadrao);
        assertEquals(resposta.containsKey(true), true);
        assertEquals(((String) resposta.get(true)).contains("Quadrinho deletado com sucesso"), true);
    }

    public void salvarQuadrinhoPadrao() {
        HashMap<Boolean, Object> resposta1 = quadrinhoControl
                .salvarQuadrinho(nomePadrao, valorPadrao, editoraPadrao, isbnPadrao, "s", "s", edicaoPadrao,
                        generoPadrao, curiosidade, nota, "s");

        quadrinhoPadrao = (Quadrinho) resposta1.get(true);
    }

}
