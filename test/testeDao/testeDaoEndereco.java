//package testeDao;
//
//import model.Endereco;
//import DAO.*;
//import java.util.ArrayList;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author Thairam Michel
// */
//public class testeDaoEndereco {
//
//    Dao<Endereco> dao;
//
//    @Before
//    public void setUp() {
//        dao = new EnderecoDao();
//    }
//
//    public void TesteDaoEndereco() {
//    }
//
//    @Test
//    public void testeQueryEndereco() throws Exception {
//        ArrayList<Endereco> lista;
//        lista = dao.query("SELECT * FROM ENDERECO");
//
//        for (Endereco endereco : lista) {
//            System.out.println("Rua: " + endereco.getRua());
//            System.out.println("Cidade: " + endereco.getCidade() + " Bairro: " + endereco.getBairro());
//            System.out.println("Cep: " + endereco.getCep() + " UF: " + endereco.getUf());
//            System.out.println("");
//        }
//
//        lista = dao.query("SELECT * FROM ENDERECO WHERE id >= 4");
//
//        for (Endereco endereco : lista) {
//            System.out.println("Rua: " + endereco.getRua());
//            System.out.println("Cidade: " + endereco.getCidade() + " Bairro: " + endereco.getBairro());
//            System.out.println("Cep: " + endereco.getCep() + " UF: " + endereco.getUf());
//        }
//    }
//}
