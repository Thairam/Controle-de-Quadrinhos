package controller;

import dao.EmprestimoDao;
import dao.Dao;
import dao.ItemDao;
import dao.QuadrinhoDao;
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
    Dao<Quadrinho> daoQuadrinho = new QuadrinhoDao<>();
    Dao<Item> daoItem = new ItemDao<>();

    Item itemEmprestimo;

    public HashMap<Boolean, Object> efetuarEmprestimo(HashMap<Quadrinho, String> quadrinhosE, Amigo amigo,
            String dataDevolucao) {
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
            resposta.put(Boolean.TRUE, emprestimo);
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "Erro Inesperado ao efetuar o empréstimo, por favor tente novamente!");
            return resposta;
        }

        return resposta;
    }

    public HashMap<Boolean, Object> efetuarDevolucao(Emprestimo emprestimo) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        try {
            ArrayList<Quadrinho> quadrinhos = daoQuadrinho.query("SELECT DISTINCT Q.id, Q.id_colecao, Q.nome, Q.valor, "
                    + "Q.editora, Q.isbn, Q.versao_fisica, Q.versao_digital, Q.edicao, Q.genero, "
                    + "Q.disponibilidadeF, Q.disponibilidadeD, Q.curiosidade, Q.nota, Q.recomendavel "
                    + "FROM quadrinho Q INNER JOIN item I "
                    + "ON Q.id = I.id_quadrinho INNER JOIN emprestimo ON I.id_emprestimo = "
                    + emprestimo.getId() + ";");

            ArrayList<Item> itens = daoItem.query("SELECT DISTINCT I.id, I.id_emprestimo, I.id_quadrinho, "
                    + "I.versao FROM item I INNER JOIN emprestimo "
                    + "ON I.id_emprestimo = " + emprestimo.getId() + ";");

            // Ambas as versões do mesmo quadrinho podem fazer parte do mesmo empréstimo!
            for (int i = 0; i < quadrinhos.size(); i++) {
                if (itens.get(i).getVersao().equals("F")) {
                    quadrinhos.get(i).setDisponibilidadeFisica(true);
                } else {
                    quadrinhos.get(i).setDisponibilidadeDigital(true);
                }
                for (int j = (i + 1); j < quadrinhos.size(); j++) {
                    if (quadrinhos.get(i).getId() == quadrinhos.get(j).getId()) {
                        if (itens.get(j).getVersao().equals("F")) {
                            quadrinhos.get(j).setDisponibilidadeFisica(true);
                        } else {
                            quadrinhos.get(j).setDisponibilidadeDigital(true);
                        }
                    }
                }
            }

            for (Quadrinho quadrinho : quadrinhos) {
                daoQuadrinho.update(quadrinho);
            }

            emprestimo.setEstado("FECHADO");
            daoEmprestimo.update(emprestimo);
            resposta.put(Boolean.TRUE, emprestimo);
            return resposta;
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "\nErro ao efetuar devolução, por favor tente novamente!");
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

    public boolean deletarEmprestimo(Emprestimo emprestimo) {
        try {
            daoEmprestimo.delete(emprestimo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Emprestimo> listarTodosOsEmprestimosDisponiveis() {
        ArrayList<Emprestimo> lista = new ArrayList<>();

        try {
            lista = daoEmprestimo.query("SELECT * FROM emprestimo E WHERE E.estado = 'ABERTO'");
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
