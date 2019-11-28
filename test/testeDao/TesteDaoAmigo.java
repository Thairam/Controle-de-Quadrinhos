package testeDao;

import dao.AmigoDao;
import dao.Dao;
import controller.AmigoController;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Amigo;
import model.Endereco;
import model.Utils;
import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class TesteDaoAmigo {

    Dao<Amigo> dao;
    Amigo defaultAmigo = new Amigo();
    Amigo amigoMock = new Amigo();
    AmigoController amigoControl = new AmigoController();

    public TesteDaoAmigo() {
        dao = new AmigoDao<>();
        amigoMock.setNome("Malaquias");
        amigoMock.setDataNascimento(Utils.stringToCalend("28/02/2000"));
        amigoMock.setCpf("555.555.555-55");
        amigoMock.setEndereco(null);
        amigoMock.setEmail("malaquias@bytecode.com");
        amigoMock.setFone("083 98564-0071");
    }

    @Test
    public void InserirAmigoComSucesso() throws Exception {
        // amigo válido
        String nome = amigoMock.getNome();
        String dataNasc = Utils.calendToString(amigoMock.getDataNascimento());
        String cpf = amigoMock.getCpf();
        Endereco endereco = null;
        String email = amigoMock.getEmail();
        String fone = amigoMock.getFone();

        HashMap<Boolean, Object> resposta = amigoControl.salvarAmigo(endereco, nome, dataNasc, cpf, fone, email);
        Amigo amigo = (Amigo) resposta.get(true);

        assertEquals(resposta.containsKey(true), true);
        assertEquals(amigo.getNome(), nome);
        assertEquals(Utils.calendToString(amigo.getDataNascimento()), dataNasc);
        assertEquals(amigo.getCpf(), cpf);
        assertEquals(amigo.getEndereco(), null);
        assertEquals(amigo.getEmail(), email);
        assertEquals(amigo.getFone(), fone);

        // O método foi implementado, mas não será utilizado na aplicação de fato.
        amigoControl.deletarAmigo(amigo);
    }

    @Test
    public void InserirAmigoComDadosInvalidos() throws Exception {
        // nome e cpf inválidos
        String nome = "ab";
        String dataNasc = "28/12/1990";
        String cpf = "05.225.547-12";
        Endereco endereco = null;
        String email = "abdias@hotmail.com";
        String fone = "021 88851-9521";
        HashMap<Boolean, Object> resposta = amigoControl.salvarAmigo(endereco, nome, dataNasc, cpf, fone, email);
        assertEquals(resposta.containsKey(false), true);
    }

    @Test
    public void InserirAmigoComDadosInvalidos2() throws Exception {
        // todos os campos inválidos
        String nome = "ab";
        String dataNasc = "2/02/2000";
        String cpf = "55.555.555-55";
        Endereco endereco = null;
        String email = "email_invalido_bytecode.com";
        String fone = "(10) 0010-0101";
        HashMap<Boolean, Object> resposta = amigoControl.salvarAmigo(endereco, nome, dataNasc, cpf, fone, email);
        assertEquals(resposta.containsKey(false), true);
    }

    @Test
    public void ListarTodosOsAmigos() throws SQLException, Exception {
        ArrayList<Amigo> lista = amigoControl.listarTodosOsAmigos();
        assertEquals(lista.size() >= 3, true); // 3 amigos foram registrados na criação do banco.
    }

    @Test
    public void BuscarAmigoPeloCpf() throws SQLException, Exception {
        HashMap<Boolean, Object> resposta = amigoControl.buscarAmigoPorCpf("123.456.789-00");
        assertEquals(resposta.containsKey(true), true);
        Amigo amigo = (Amigo) resposta.get(true);
        assertEquals(amigo.getNome(), "Thairam");
        assertEquals(amigo.getCpf(), "123.456.789-00");
    }

    @Test
    public void BuscarAmigoPeloCpf2() throws SQLException, Exception {
        HashMap<Boolean, Object> resposta = amigoControl.buscarAmigoPorCpf("111.111.111-00");
        assertEquals(resposta.containsKey(false), true);
        assertEquals(resposta.get(false), "Amigo não encontrado");
    }

    @Test
    public void BuscarAmigoPeloID() throws Exception {
        HashMap<Boolean, Object> resposta = amigoControl.buscarAmigoPeloID(2);
        Amigo Adson = (Amigo) resposta.get(true);
        assertEquals(Adson.getId(), 1);
        assertEquals(Adson.getNome(), "Adson");
        assertEquals(Adson.getCpf(), "222.222.222-22");
    }

    @Test
    public void AtualizarAmigo() throws Exception {
        defaultAmigo = (Amigo) amigoControl.buscarAmigoPeloID(1).get(true);

        defaultAmigo.setFone("083 99141-7022");
        HashMap<Boolean, String> resposta = amigoControl.atualizarAmigo(defaultAmigo);
        assertEquals(resposta.containsKey(true), true);
        assertEquals(resposta.get(true), "Registro de " + defaultAmigo.getNome() + "foi atualizado com sucesso");
    }
}
