package integracao;

import dao.*;
import controller.QuadrinhoController;
import java.util.ArrayList;
import model.Amigo;
import model.Quadrinho;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeDaoQuadrinho {

    Dao<Quadrinho> daoQuadrinho;
    ArrayList<Quadrinho> quadrinhos;

    QuadrinhoController quadrinhoControl = new QuadrinhoController();

//    @Test
//    public void testeDaoQuadrinho() throws Exception {
//        System.out.println("=======teste find==========");
//        Quadrinho quadrinho1 = dao.find(1);
//        System.out.println("Nome: " + quadrinho1.getNome());
//        System.out.println("valor: " + quadrinho1.getValor());
//        System.out.println("editora: " + quadrinho1.getEditora() + " isbn: " + quadrinho1.getIsbn());
//        System.out.println("Versao Fisica: " + quadrinho1.isVersaoFisica() + " Versao Digital: " + quadrinho1.isVersaoDigital());
//        System.out.println("Ediçao: " + quadrinho1.getEdicao() + " Genêro: " + quadrinho1.getGenero());
//        System.out.println("Curiosidade: " + quadrinho1.getCuriosidade());
//        System.out.println("Nota: " + quadrinho1.getNota() + " Recomendavel: " + quadrinho1.isRecomendavel());
//        System.out.println("Colecao: " + quadrinho1.getColecao().getNome());
//        System.out.println("===========================");
//    }
    @Test
    public void testeFindQuadrinho() {
        quadrinhos = quadrinhoControl.listarTodosOsQuadrinhos();
        for (Quadrinho quadrinho : quadrinhos) {
            System.out.println(quadrinho.getDisponibilidadeFisica());
            System.out.println(quadrinho.getDisponibilidadeDigital());
        }
    }
}
