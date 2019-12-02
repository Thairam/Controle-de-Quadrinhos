package controller;

import dao.QuadrinhoDao;
import dao.Dao;
import expressoes_regulares.QuadrinhoER;
import java.util.ArrayList;
import java.util.HashMap;
import model.Quadrinho;

/**
 *
 * @author Thairam Michel
 */
public class QuadrinhoController {

    Dao<Quadrinho> dao = new QuadrinhoDao<>();
    private static ArrayList<String> camposInvalidos;
    ItemController itemControl = new ItemController();

    public HashMap<Boolean, Object> salvarQuadrinho(String nome, double valor, String editora,
            String isbn, String versaoFisicaS, String versaoDigitalS, String edicao,
            String genero, String curiosidade, double nota, String recomendavelS
    ) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();
        if (verificarCampos(nome, valor, editora, isbn, versaoFisicaS, versaoDigitalS, edicao, genero,
                nota, recomendavelS)) {
            try {
                boolean versaoFisica = versaoFisicaS.toLowerCase().contains("s");
                boolean versaoDigital = versaoDigitalS.toLowerCase().contains("s");
                boolean recomendavel = recomendavelS.toLowerCase().contains("s");

                Quadrinho quadrinho = new Quadrinho(null, nome, valor, editora, isbn, versaoFisica,
                        versaoDigital, edicao, genero, versaoFisica, versaoDigital, curiosidade, nota, recomendavel);
                dao.insert(quadrinho);
                resposta.put(Boolean.TRUE, quadrinho);
                return resposta;
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate")) {
                    resposta.put(Boolean.FALSE, "\nJá existe um registro com o mesmo isbn!");
                } else {
                    resposta.put(Boolean.FALSE, "\nErro ao salvar o quadrinho, por favor tente novamente!");
                }
                return resposta;
            }
        } else {
            String msgCampos = preencherMensagem();
            resposta.put(Boolean.FALSE, msgCampos);
        }

        return resposta;
    }

    public HashMap<Boolean, Object> atualizarQuadrinho(Quadrinho quadrinho, String nome, double valor, String editora,
            String isbn, String versaoFisicaS, String versaoDigitalS, String edicao,
            String genero, String curiosidade, double nota, String recomendavelS
    ) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();
        if (verificarCamposAtualizacao(nome, valor, editora, isbn, versaoFisicaS, versaoDigitalS, edicao, genero,
                nota, recomendavelS)) {
            try {
                definirValoresDouble(quadrinho, valor, nota);
                verificarInclusaoDeVersoes(quadrinho, versaoFisicaS, versaoDigitalS);
                definirCamposDoQuadrinho(quadrinho, nome, editora, isbn, edicao, genero, recomendavelS, curiosidade);
                dao.update(quadrinho);
                resposta.put(Boolean.TRUE, quadrinho);
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate")) {
                    resposta.put(Boolean.FALSE, "\nJá existe um registro com o mesmo isbn!");
                } else {
                    resposta.put(Boolean.FALSE, "\nErro ao salvar o quadrinho, por favor tente novamente!");
                }
                return resposta;
            }
        } else {
            String msgCampos = preencherMensagem();
            resposta.put(Boolean.FALSE, msgCampos);
        }

        return resposta;
    }

    public ArrayList<Quadrinho> listarTodosOsQuadrinhos() {
        ArrayList<Quadrinho> lista = new ArrayList<>();

        try {
            lista = dao.query("SELECT * FROM QUADRINHO");
        } catch (Exception e) {
            return lista;
        }
        return lista;
    }

    public ArrayList<Quadrinho> listarTodosOsQuadrinhosDisponiveis() {
        ArrayList<Quadrinho> lista;
        ArrayList<Quadrinho> disponiveis = new ArrayList<>();
        lista = listarTodosOsQuadrinhos();

        for (Quadrinho quadrinho : lista) {
            if (quadrinho.getDisponibilidadeFisica() || quadrinho.getDisponibilidadeDigital()) {
                disponiveis.add(quadrinho);
            }
        }
        return disponiveis;
    }

    public HashMap<Boolean, Object> buscarQuadrinhoPeloNome(String nome) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        try {
            ArrayList<Quadrinho> quadrinhos = dao.query("SELECT * FROM QUADRINHO WHERE "
                    + "quadrinho.nome = '" + nome + "'");
            resposta.put(Boolean.TRUE, quadrinhos.get(0));
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "Quadrinho não encontrado");
        }
        return resposta;
    }

    public HashMap<Boolean, Object> deletarQuadrinho(Quadrinho quadrinho) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();
        try {
            boolean exclusao = verificarEmprestimos(quadrinho);
            if (exclusao) {
                dao.delete(quadrinho);
                resposta.put(Boolean.TRUE, "\n** Quadrinho deletado com sucesso! **");
            } else {
                resposta.put(Boolean.FALSE, "\n** O quadrinho não pode ser excluido, pois está "
                        + "associado a um empréstimo! **");
            }
            return resposta;
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "\n** Erro inesperado ao deletar o quadrinho! **");
            return resposta;
        }
    }

    private void definirCamposDoQuadrinho(Quadrinho quadrinho, String nome, String editora, String isbn,
            String edicao, String genero, String recomendavelS, String curiosidade) {

        if (!"".equals(nome)) {
            quadrinho.setNome(nome);
        }
        if (!"".equals(editora)) {
            quadrinho.setEditora(editora);
        }
        if (!"".equals(isbn)) {
            quadrinho.setIsbn(isbn);
        }
        if (!"".equals(edicao)) {
            quadrinho.setEdicao(edicao);
        }
        if (!"".equals(genero)) {
            quadrinho.setGenero(genero);
        }
        if (!"".equals(curiosidade)) {
            quadrinho.setCuriosidade(curiosidade);
        }
        boolean recomendavel = "".equals(recomendavelS) ? quadrinho.isRecomendavel() : recomendavelS.contains("s");
        quadrinho.setRecomendavel(recomendavel);
    }

    private void verificarInclusaoDeVersoes(Quadrinho quadrinho, String versaoFisicaS, String versaoDigitalS) {

        boolean incluirVersaoFisica = "".equals(versaoFisicaS) ? quadrinho.isVersaoFisica() : versaoFisicaS.toLowerCase().contains("s");
        boolean incluirVersaoDigital = "".equals(versaoDigitalS) ? quadrinho.isVersaoDigital() : versaoDigitalS.toLowerCase().contains("s");

        if (!quadrinho.isVersaoFisica() && incluirVersaoFisica) {
            quadrinho.setVersaoFisica(true);
            quadrinho.setDisponibilidadeFisica(true);
        }

        if (!quadrinho.isVersaoDigital() && incluirVersaoDigital) {
            quadrinho.setVersaoDigital(true);
            quadrinho.setDisponibilidadeDigital(true);
        }
    }

    private void definirValoresDouble(Quadrinho quadrinho, double valor, double nota) {
        if (valor != 1111) {
            quadrinho.setValor(valor);
        }
        if (nota != 0.001) {
            quadrinho.setNota(nota);
        }
    }

    private boolean verificarEmprestimos(Quadrinho quadrinho) {
        return true;
    }

    private boolean verificarCampos(String nome, double valor, String editora, String isbn,
            String versaoFisica, String versaoDigital, String edicao,
            String genero, double nota, String recomendavel) {

        QuadrinhoController.camposInvalidos = new ArrayList<>();

        if (!nome.matches(QuadrinhoER.ER_NOME)) {
            QuadrinhoController.camposInvalidos.add("nome");
        }
        String valorS = Double.toString(valor);
        if (!valorS.matches(QuadrinhoER.ER_VALOR)) {
            QuadrinhoController.camposInvalidos.add("valor");
        }
        if (!editora.matches(QuadrinhoER.ER_EDITORA)) {
            QuadrinhoController.camposInvalidos.add("editora");
        }
        if (!isbn.matches(QuadrinhoER.ER_ISBN)) {
            QuadrinhoController.camposInvalidos.add("isbn");
        }
        if (!versaoFisica.matches(QuadrinhoER.ER_BOOLEAN)) {
            QuadrinhoController.camposInvalidos.add("versão física");
        }
        if (!versaoDigital.matches(QuadrinhoER.ER_BOOLEAN)) {
            QuadrinhoController.camposInvalidos.add("versão digital");
        }
        if (!edicao.matches(QuadrinhoER.ER_EDICAO)) {
            QuadrinhoController.camposInvalidos.add("edição");
        }
        if (!genero.matches(QuadrinhoER.ER_GENERO)) {
            QuadrinhoController.camposInvalidos.add("genero");
        }
        String notaS = Double.toString(nota);
        if ((!notaS.matches(QuadrinhoER.ER_NOTA) && !"".equals(notaS)) || nota > 10) {
            QuadrinhoController.camposInvalidos.add("nota");
        }
        if (!recomendavel.matches(QuadrinhoER.ER_BOOLEAN) && !"".equals(recomendavel)) {
            QuadrinhoController.camposInvalidos.add("recomendação");
        }

        return QuadrinhoController.camposInvalidos.isEmpty();

    }

    private boolean verificarCamposAtualizacao(String nome, double valor, String editora, String isbn,
            String versaoFisica, String versaoDigital, String edicao,
            String genero, double nota, String recomendavel) {

        QuadrinhoController.camposInvalidos = new ArrayList<>();

        if (!nome.matches(QuadrinhoER.ER_NOME) && !"".equals(nome)) {
            QuadrinhoController.camposInvalidos.add("nome");
        }
        String valorS = Double.toString(valor);
        if (!valorS.matches(QuadrinhoER.ER_VALOR) && !valorS.contains("1111")) {
            QuadrinhoController.camposInvalidos.add("valor");
        }
        if (!editora.matches(QuadrinhoER.ER_EDITORA) && !"".equals(editora)) {
            QuadrinhoController.camposInvalidos.add("editora");
        }
        if (!isbn.matches(QuadrinhoER.ER_ISBN) && !"".equals(isbn)) {
            QuadrinhoController.camposInvalidos.add("isbn");
        }
        if (!versaoFisica.matches(QuadrinhoER.ER_BOOLEAN) && !"".equals(versaoFisica)) {
            QuadrinhoController.camposInvalidos.add("versão física");
        }
        if (!versaoDigital.matches(QuadrinhoER.ER_BOOLEAN) && !"".equals(versaoDigital)) {
            QuadrinhoController.camposInvalidos.add("versão digital");
        }
        if (!edicao.matches(QuadrinhoER.ER_EDICAO) && !"".equals(edicao)) {
            QuadrinhoController.camposInvalidos.add("edição");
        }
        if (!genero.matches(QuadrinhoER.ER_GENERO) && !"".equals(genero)) {
            QuadrinhoController.camposInvalidos.add("genero");
        }
        String notaS = Double.toString(nota);
        if (!notaS.matches(QuadrinhoER.ER_NOTA) && (!notaS.contains("0.001") || nota > 10)) {
            QuadrinhoController.camposInvalidos.add("nota");
        }
        if (!recomendavel.matches(QuadrinhoER.ER_BOOLEAN) && !"".equals(recomendavel)) {
            QuadrinhoController.camposInvalidos.add("recomendação");
        }

        return QuadrinhoController.camposInvalidos.isEmpty();

    }

    private String preencherMensagem() {
        String msg = "";
        for (int i = 0; i < camposInvalidos.size() - 1; i++) {
            msg += camposInvalidos.get(i) + ", ";
        }
        msg += camposInvalidos.get(camposInvalidos.size() - 1);

        return msg;
    }
}
