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
        
        String estado = "aberto";
        
        Emprestimo emprestimo = new Emprestimo(amigo, dataEmprestimo, dataDevolucao, estado);
        
        daoEmprestimo.insert(emprestimo);
        
        Item item = new Item(emprestimo, quadrinho, "F");
        
        daoItem.insert(item);
        
        emprestimo.setEstado("FECHADO");
        daoEmprestimo.update(emprestimo);
        
    }
}
