package controller;

import dao.EmprestimoDao;
import dao.Dao;
import dao.ItemDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import model.Amigo;
import model.Emprestimo;
import model.Quadrinho;
import model.Item;
import model.Utils;

/**
 *
 * @author Thairam Michel
 */
public class EmprestimoController {

    Dao<Emprestimo> daoEmprestimo = new EmprestimoDao<>();
    Dao<Item> daoItem = new ItemDao<>();
    Item itemEmprestimo;

    public HashMap<Boolean, Object> efetuarEmprestimo(HashMap<Quadrinho, String> quadrinhosE, Amigo amigo, String dataDevolucao) {
        HashMap<Boolean, Object> resultadoData;
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        Calendar dataEmprestimoC = new GregorianCalendar();
        resultadoData = validarDataDevolucao(dataDevolucao, dataEmprestimoC);

        if (resultadoData.containsKey(false)) {
            return resultadoData;
        }

        try {
            Calendar dataDevolucaoC = Utils.stringToCalend(dataDevolucao);
            Emprestimo emprestimo = new Emprestimo(amigo, dataEmprestimoC, dataDevolucaoC, "ABERTO");
            daoEmprestimo.insert(emprestimo);
            // key = quadrinho_id
            // value = versão do empréstimo        
            for (Quadrinho quadrinho : quadrinhosE.keySet()) {
                itemEmprestimo = new Item(emprestimo, quadrinho, quadrinhosE.get(quadrinho));
                daoItem.insert(itemEmprestimo);
            }
            resposta.put(Boolean.TRUE, "Empréstimo efetuado com sucesso!\n");
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, e.getMessage());
            return resposta;
        }

        return resposta;
    }

    public HashMap<Boolean, Object> efetuarDevolucao(Emprestimo emprestimo) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        try {
            emprestimo.setEstado("FECHADO");
            daoEmprestimo.update(emprestimo);
            resposta.put(Boolean.TRUE, "\nDevolução efetuada com sucesso!");
            return resposta;
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "\nErro ao efetuar devolução!");
            return resposta;
        }
    }

    public ArrayList<Emprestimo> listarTodosOsEmprestimos() {
        ArrayList<Emprestimo> lista = new ArrayList<>();

        try {
            lista = daoEmprestimo.query("SELECT * FROM EMPRESTIMO");
        } catch (Exception e) {
            return lista;
        }
        return lista;
    }

    public boolean validarEscolhaDeQuadrinho(Quadrinho quadrinho, int opcao, HashMap<Quadrinho, String> quadrinhosE) {
        // Adicionar versão física
        if (opcao == 1) {
            if (!quadrinho.getDisponibilidadeFisica()) {
                return false;
            } else {
                quadrinhosE.put(quadrinho, "F");
                return true;
            }
        }

        // Adicionar versão digital
        if (opcao == 2) {
            if (!quadrinho.getDisponibilidadeDigital()) {
                return false;
            } else {
                quadrinhosE.put(quadrinho, "D");
                return true;
            }
        }
        return true;
    }

    private HashMap<Boolean, Object> validarDataDevolucao(String dataDevolucao, Calendar dataEmprestimoC) {
        HashMap<Boolean, Object> resultado = new HashMap<>();

        if (!validadorData(dataDevolucao)) {
            resultado.put(Boolean.FALSE, "data com formato inválido!\n");
            return resultado;
        }

        dataEmprestimoC.setTime(new Date());
        Calendar dataDevolucaoC = Utils.stringToCalend(dataDevolucao);

        if (dataDevolucaoC.before(dataEmprestimoC)) {
            resultado.put(Boolean.FALSE, "A data de devolução deve ser maior do que a data atual!\n");
            return resultado;
        }

        resultado.put(Boolean.TRUE, "data válida!");
        return resultado;
    }

    private boolean validadorData(String data) {
        String ER_DATA = "\\d{2}/\\d{2}/\\d{4}";
        return data.matches(ER_DATA);
    }
}
