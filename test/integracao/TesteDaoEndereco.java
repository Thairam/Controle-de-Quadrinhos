package integracao;

import model.Endereco;
import dao.EnderecoDao;
import dao.Dao;
import controller.EnderecoController;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class TesteDaoEndereco {

    Dao<Endereco> dao;
    EnderecoController enderecoController = new EnderecoController();

    public void testeDaoEndereco() {
        dao = new EnderecoDao();
    }

    public void TesteDaoEndereco() {
    }

    @Test
    public void testeQueryEndereco() throws Exception {
        ArrayList<Endereco> lista = enderecoController.listarTodosOsEnderecos();
        System.out.println("TAM: " + lista.size());

        for (Endereco endereco : lista) {
            System.out.println("Rua: " + endereco.getRua());
            System.out.println("Cidade: " + endereco.getCidade() + " Bairro: " + endereco.getBairro());
            System.out.println("Cep: " + endereco.getCep() + " UF: " + endereco.getUf());
            System.out.println("");
        }
    }
}
