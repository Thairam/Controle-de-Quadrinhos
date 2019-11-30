package integracao;

import model.*;
import dao.*;
import controller.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

    QuadrinhoController quadrinhoControl = new QuadrinhoController();
    AmigoController amigoControl = new AmigoController();
    EmprestimoController emprestimoControl = new EmprestimoController();

    @Test
    public void testeQueryEmprestimo() throws Exception {
        HashMap<Boolean, Object> resultQuadrinho = quadrinhoControl.buscarQuadrinhoPeloNome("Batman");
        Quadrinho quadrinho = (Quadrinho) resultQuadrinho.get(true);

        HashMap<Boolean, Object> resultAmigo = amigoControl.buscarAmigoPeloID(1);
        Amigo amigo = (Amigo) resultAmigo.get(true);

        Calendar dataEmprestimo = new GregorianCalendar();
        dataEmprestimo.setTime(new Date());

        Calendar dataDevolucao;
        dataDevolucao = Utils.stringToCalend("25/12/2019");

        Calendar dataDevolucao2;
        dataDevolucao2 = Utils.stringToCalend("30/12/2019");

        String estado = "ABERTO";

        Emprestimo emprestimo = new Emprestimo(amigo, dataEmprestimo, dataDevolucao, estado);
        Emprestimo emprestimo2 = new Emprestimo(amigo, dataEmprestimo, dataDevolucao2, estado);

        daoEmprestimo.insert(emprestimo);
        Item item = new Item(emprestimo, quadrinho, "F");

        daoEmprestimo.insert(emprestimo2);
        Item item2 = new Item(emprestimo2, quadrinho, "D");

        System.out.println("ITEM: " + item.getId());
        System.out.println("ITEM 2: " + item2.getId());

        daoItem.insert(item);
        daoItem.insert(item2);
//        
//        ArrayList<Emprestimo> lista = daoEmprestimo.query("SELECT * FROM item I INNER JOIN emprestimo E "
//                + "ON E.id = I.id_emprestimo;");
//        
//        for (Emprestimo e : lista) {
//            System.out.println("E: " + e.getValues());
//        }
//        
//        ArrayList<Quadrinho> quadrinhos = daoQuadrinho.query("SELECT DISTINCT Q.id, Q.id_colecao, Q.nome, Q.valor, "
//                + "Q.editora, Q.isbn, Q.versao_fisica, Q.versao_digital, Q.edicao, Q.genero, "
//                + "Q.disponibilidadeF, Q.disponibilidadeD, Q.curiosidade, Q.nota, Q.recomendavel "
//                + "FROM quadrinho Q INNER JOIN item I "
//                + "ON Q.id = I.id_quadrinho INNER JOIN emprestimo ON I.id_emprestimo = "
//                + emprestimo.getId() + ";");
//        
//        for (Quadrinho q : quadrinhos) {
//            System.out.println("Q: " + q.getId());
//        }
//        
//        ArrayList<Item> itemVersao = daoItem.query("SELECT DISTINCT I.id, I.id_emprestimo, I.id_quadrinho, "
//                + "I.versao FROM item I INNER JOIN emprestimo "
//                + "ON I.id_emprestimo = " + emprestimo.getId() + ";");
//        
//        for (Item i : itemVersao) {
//            System.out.println("I: " + i.getVersao());
//        }
//        emprestimo.setEstado("FECHADO");
//        daoEmprestimo.update(emprestimo);
    }
}
