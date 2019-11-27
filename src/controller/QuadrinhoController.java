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

    public HashMap<Boolean, Object> salvarQuadrinho(String nome, double valor, String editora,
            String isbn, String versaoFisicaS, String versaoDigitalS, String edicao,
            String genero, String disponibilidadeS, String curiosidade, double nota, String recomendavelS
    ) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();
        if (verificarCampos(nome, valor, editora, isbn, versaoFisicaS, versaoDigitalS, edicao, genero,
                nota, recomendavelS)) {
            try {
                boolean versaoFisica = versaoFisicaS.toLowerCase().contains("s");
                boolean versaoDigital = versaoDigitalS.toLowerCase().contains("s");
                boolean disponibilidade = (versaoFisica || versaoDigital);
                boolean recomendavel = recomendavelS.toLowerCase().contains("s");

                Quadrinho quadrinho = new Quadrinho(null, nome, valor, editora, isbn, versaoFisica,
                        versaoDigital, edicao, genero, disponibilidade, curiosidade, nota, recomendavel);
                dao.insert(quadrinho);
                resposta.put(Boolean.TRUE, quadrinho);
                return resposta;
            } catch (Exception e) {
                resposta.put(Boolean.FALSE, e.getMessage());
                return resposta;
            }
        } else {
            String msgCampos = "Campos com valores inválidos: \n";
            for (int i = 0; i < camposInvalidos.size(); i++) {
                msgCampos += (i + 1) + ": " + camposInvalidos.get(i) + "\n";
            }
            msgCampos += "Por favor, informe o(s) valor(es) do(s) campo(s) corretamente.\n";
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

}
