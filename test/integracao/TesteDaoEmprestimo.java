package integracao;

import model.*;
import dao.*;
import controller.*;
import java.util.ArrayList;
import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class TesteDaoEmprestimo {

    Dao<Emprestimo> daoEmprestimo = new EmprestimoDao<>();
    Dao<Quadrinho> daoQuadrinho = new QuadrinhoDao<>();
    Dao<Item> daoItem = new ItemDao<>();

    ArrayList<Quadrinho> quadrinhos;
    ArrayList<Amigo> amigos;

    Amigo amigoPadrao = new Amigo();
    Quadrinho quadrinhoPadrao = new Quadrinho();
    Emprestimo emprestimoPadrao = new Emprestimo();

    QuadrinhoController quadrinhoControl = new QuadrinhoController();
    AmigoController amigoControl = new AmigoController();
    EmprestimoController emprestimoControl = new EmprestimoController();

    String dataDevolucaoPadraoS = "25/12/2019";

    @After
    public void limparEmprestimo() {
        emprestimoControl.deletarEmprestimo(emprestimoPadrao);
        amigoControl.deletarAmigo(amigoPadrao);
        quadrinhoControl.deletarQuadrinho(quadrinhoPadrao);
    }

    @Test
    public void emprestimoComSucesso() {
        preCondicoesEmprestimo();
        HashMap<Quadrinho, String> quadrinhosEmprestimo = new HashMap<Quadrinho, String>();
        quadrinhosEmprestimo.put(quadrinhoPadrao, "F"); //(F) versão física do quadrinho

        HashMap<Boolean, Object> resposta
                = emprestimoControl.efetuarEmprestimo(quadrinhosEmprestimo, amigoPadrao, dataDevolucaoPadraoS);

        emprestimoPadrao = (Emprestimo) resposta.get(true);
        assertEquals(resposta.containsKey(true), true);
        assertEquals(((Emprestimo) resposta.get(true)).getAmigo(), amigoPadrao);
        assertEquals(((Emprestimo) resposta.get(true)).getEstado(), "ABERTO");
        assertEquals(((Emprestimo) resposta.get(true)).getDataDevolucao(), Utils.stringToCalend(dataDevolucaoPadraoS));
    }

    @Test
    public void dataComFormatoInvalido() {
        preCondicoesEmprestimo();
        HashMap<Quadrinho, String> quadrinhosEmprestimo = new HashMap<Quadrinho, String>();
        quadrinhosEmprestimo.put(quadrinhoPadrao, "F");

        String dataDevolucaoInvalida = "1/01/2050"; // formato de data inválido para a aplicação

        HashMap<Boolean, Object> resposta
                = emprestimoControl.efetuarEmprestimo(quadrinhosEmprestimo, amigoPadrao, dataDevolucaoInvalida);

        emprestimoPadrao = (Emprestimo) resposta.get(true);
        assertEquals(resposta.containsKey(false), true);
        assertEquals(((String) resposta.get(false))
                .contains("data com formato inválido"), true);
    }

    @Test
    public void efetuandoDevolucao() {
        preCondicoesEmprestimo();
        HashMap<Quadrinho, String> quadrinhosEmprestimo = new HashMap<Quadrinho, String>();
        quadrinhosEmprestimo.put(quadrinhoPadrao, "F"); //(F) versão física do quadrinho

        HashMap<Boolean, Object> resposta
                = emprestimoControl.efetuarEmprestimo(quadrinhosEmprestimo, amigoPadrao, dataDevolucaoPadraoS);

        emprestimoPadrao = (Emprestimo) resposta.get(true);

        HashMap<Boolean, Object> respostaDevolucao = emprestimoControl.efetuarDevolucao(emprestimoPadrao);
        assertEquals(respostaDevolucao.containsKey(true), true);
        assertEquals(((String) respostaDevolucao.get(true)).contains("Devolução efetuada com sucesso"), true);
    }

    @Test
    public void emprestimoComDataInvalida() {
        preCondicoesEmprestimo();
        HashMap<Quadrinho, String> quadrinhosEmprestimo = new HashMap<Quadrinho, String>();
        quadrinhosEmprestimo.put(quadrinhoPadrao, "F");

        String dataDevolucaoInvalida = "01/01/2019"; // inferior a data do emprestimo

        HashMap<Boolean, Object> resposta
                = emprestimoControl.efetuarEmprestimo(quadrinhosEmprestimo, amigoPadrao, dataDevolucaoInvalida);

        emprestimoPadrao = (Emprestimo) resposta.get(true);
        assertEquals(resposta.containsKey(false), true);
        assertEquals(((String) resposta.get(false))
                .contains("A data de devolução deve ser maior do que a data atual"), true);
    }

    @Test
    public void listarEmprestimos() {
        preCondicoesEmprestimo();
        HashMap<Quadrinho, String> quadrinhosEmprestimo = new HashMap<Quadrinho, String>();
        quadrinhosEmprestimo.put(quadrinhoPadrao, "F"); //(F) versão física do quadrinho

        HashMap<Boolean, Object> resposta
                = emprestimoControl.efetuarEmprestimo(quadrinhosEmprestimo, amigoPadrao, dataDevolucaoPadraoS);

        emprestimoPadrao = (Emprestimo) resposta.get(true);

        ArrayList<Emprestimo> lista = emprestimoControl.listarTodosOsEmprestimos();
        assertEquals(lista.size() == 1, true);
    }

    @Test
    public void listaVazia() {
        ArrayList<Emprestimo> lista = emprestimoControl.listarTodosOsEmprestimos();
        assertEquals(lista.isEmpty(), true);
    }

    public void preCondicoesEmprestimo() {
        salvarAmigoPadrao();
        salvarQuadrinhoPadrao();
    }

    public void salvarQuadrinhoPadrao() {
        String nomePadrao = "Watchmen";
        Double valorPadrao = 16.90;
        String editoraPadrao = "DC Comics";
        String isbnPadrao = "0189554710253";
        String edicaoPadrao = "edição especial";
        String generoPadrao = "ficção cientíica";
        String curiosidade = "Serviu de inspiração para o filme";
        double nota = 10;

        HashMap<Boolean, Object> resposta1 = quadrinhoControl
                .salvarQuadrinho(nomePadrao, valorPadrao, editoraPadrao, isbnPadrao, "s", "s", edicaoPadrao,
                        generoPadrao, curiosidade, nota, "s");

        quadrinhoPadrao = (Quadrinho) resposta1.get(true);
    }

    public void salvarAmigoPadrao() {
        String nome = "joao da silva";
        String dataNasc = "10/10/1990";
        String cpf = "777.666.555-00";
        Endereco endereco = null;
        String email = "joaosilva@hotmail.com";
        String fone = "083 99971-2585";

        HashMap<Boolean, Object> resposta = amigoControl.salvarAmigo(endereco, nome, dataNasc, cpf, fone, email);
        amigoPadrao = (Amigo) resposta.get(true);
    }
}
