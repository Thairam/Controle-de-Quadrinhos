package controller;

import dao.Dao;
import dao.ItemDao;
import java.util.ArrayList;
import model.Item;
import model.Quadrinho;

/**
 *
 * @author Thairam Michel
 */
public class ItemController {

    Dao<Item> daoItem = new ItemDao<>();
    ArrayList<Item> lista = new ArrayList<>();

    public ArrayList<Item> obterEmprestimosAbertosDoQuadrinho(Quadrinho quadrinho) throws Exception {

        lista = daoItem.query("SELECT DISTINCT item.id, item.versao, item.id_emprestimo, item.id_quadrinho from item inner join "
                + "quadrinho ON item.id_quadrinho = " + quadrinho.getId() + " inner join emprestimo "
                + "WHERE emprestimo.estado = 'ABERTO';");

        return lista;
    }

}
