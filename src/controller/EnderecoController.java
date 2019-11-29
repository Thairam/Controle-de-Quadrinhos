package controller;

import dao.Dao;
import model.Endereco;
import dao.EnderecoDao;
import java.util.ArrayList;
import java.util.HashMap;
import model.Utils;
import expressoes_regulares.EnderecoER;

/**
 *
 * @author Thairam Michel
 */
public class EnderecoController {

    Dao<Endereco> dao = new EnderecoDao();
    private static ArrayList<String> camposInvalidos;

    public EnderecoController() {
        camposInvalidos = new ArrayList<>();
    }

    public HashMap<Boolean, Object> salvarEndereco(String rua, String bairro, String cidade, String UF, String cep) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        if (verificarCampos(rua, bairro, cidade, UF, cep)) {
            try {
                Endereco endereco = new Endereco(cep, rua, bairro, cidade, UF);
                dao.insert(endereco);
                resposta.put(Boolean.TRUE, endereco);
                return resposta;
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate")) {
                    resposta.put(Boolean.FALSE, "Já existe um registro com o mesmo cep, para este endereço!");
                } else {
                    resposta.put(Boolean.FALSE, "Erro ao salvar o endereço, por favor tente novamente!");

                }
                return resposta;
            }
        } else {
            String msgCampos = preencherMensagem();
            resposta.put(Boolean.FALSE, msgCampos);
        }
        return resposta;

    }

    public ArrayList<Endereco> listarTodosOsEnderecos() {
        ArrayList<Endereco> lista = new ArrayList<>();

        try {
            lista = dao.query("SELECT * FROM ENDERECO");
        } catch (Exception e) {
            return lista;
        }
        return lista;

    }

    public HashMap<Boolean, Object> atualizarEndereco(Endereco endereco) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        try {
            if (verificarEndereco(endereco)) {
                dao.update(endereco);
                resposta.put(Boolean.TRUE, "Endereco atualizado com sucesso!");
            } else {
                String msgCampos = "Campos com valores inválidos: \n";
                for (int i = 0; i < camposInvalidos.size(); i++) {
                    msgCampos += (i + 1) + ": " + camposInvalidos.get(i) + "\n";
                }
                msgCampos += "Por favor, informe o(s) valor(es) do(s) campo(s) corretamente.\n";
                resposta.put(Boolean.FALSE, msgCampos);
            }
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, e.getMessage());
            return resposta;
        }
        return resposta;
    }

    public boolean deletarEndereco(Endereco endereco) {
        try {
            dao.delete(endereco);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean verificarCampos(String rua, String bairro, String cidade, String uf, String cep) {

        EnderecoController.camposInvalidos = new ArrayList<>();

        if (!rua.matches(EnderecoER.ER_RUA)) {
            EnderecoController.camposInvalidos.add("rua");
        }
        if (!bairro.matches(EnderecoER.ER_BAIRRO)) {
            EnderecoController.camposInvalidos.add("bairro");
        }
        if (!cidade.matches(EnderecoER.ER_CIDADE)) {
            EnderecoController.camposInvalidos.add("cidade");
        }
        if (!uf.matches(EnderecoER.ER_UF)) {
            EnderecoController.camposInvalidos.add("uf");
        }
        if (!cep.matches(EnderecoER.ER_CEP)) {
            EnderecoController.camposInvalidos.add("cep");
        }

        return EnderecoController.camposInvalidos.isEmpty();
    }

    public static boolean verificarCamposAtualizacao(String rua, String bairro, String cidade, String uf, String cep) {

        EnderecoController.camposInvalidos = new ArrayList<>();

        if (!rua.matches(EnderecoER.ER_RUA) && !"".equals(rua)) {
            EnderecoController.camposInvalidos.add("rua");
        }
        if (!bairro.matches(EnderecoER.ER_BAIRRO) && !"".equals(bairro)) {
            EnderecoController.camposInvalidos.add("bairro");
        }
        if (!cidade.matches(EnderecoER.ER_CIDADE) && !"".equals(cidade)) {
            EnderecoController.camposInvalidos.add("cidade");
        }
        if (!uf.matches(EnderecoER.ER_UF) && !"".equals(uf)) {
            EnderecoController.camposInvalidos.add("uf");
        }
        if (!cep.matches(EnderecoER.ER_CEP) && !"".equals(cep)) {
            EnderecoController.camposInvalidos.add("cep");
        }

        return EnderecoController.camposInvalidos.isEmpty();
    }

    private static boolean verificarEndereco(Endereco endereco) {
        String rua = endereco.getRua();
        String bairro = endereco.getBairro();
        String cidade = endereco.getCidade();
        String uf = endereco.getUf();
        String cep = endereco.getCep();

        return verificarCampos(rua, bairro, cidade, uf, cep);
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
