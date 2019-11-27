package controller;

import dao.AmigoDao;
import dao.Dao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import model.Endereco;
import model.Amigo;
import model.Utils;
import expressoes_regulares.AmigoER;

/**
 * @author Thairam Michel
 */
public class AmigoController {

    Dao<Amigo> dao = new AmigoDao<>();
    private static ArrayList<String> camposInvalidos = new ArrayList<>();

    public HashMap<Boolean, Object> salvarAluno(Endereco endereco, String nome, String dataNasc, String cpf, String fone, String email) {

        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        if (verificarCampos(nome, dataNasc, cpf, fone, email)) {
            try {
                Calendar dataNascimento = Utils.stringToCalend(dataNasc);
                Amigo amigo = new Amigo(endereco, nome, dataNascimento, cpf, fone, email);
                dao.insert(amigo);
                resposta.put(Boolean.TRUE, amigo);
                return resposta;
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate")) {
                    resposta.put(Boolean.FALSE, "Já existe um registro com o mesmo cpf!");
                }
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

    public ArrayList<Amigo> listarTodosOsAmigos() {
        ArrayList<Amigo> lista = new ArrayList<>();

        try {
            lista = dao.query("SELECT * FROM AMIGO");
        } catch (Exception e) {
            return lista;
        }
        return lista;
    }

    public HashMap<Boolean, Object> buscarAmigoPorCpf(String cpf) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        try {
            ArrayList<Amigo> amigos = dao.query("SELECT * FROM AMIGO WHERE amigo.cpf = '" + cpf + "'");
            resposta.put(Boolean.TRUE, amigos.get(0));
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "Amigo não encontrado");
        }
        return resposta;
    }

    public HashMap<Boolean, Object> buscarAmigoPeloID(int amigoID) {
        HashMap<Boolean, Object> resposta = new HashMap<Boolean, Object>();

        try {
            Amigo amigo = dao.find(amigoID);
            resposta.put(Boolean.TRUE, amigo);
        } catch (Exception e) {
            resposta.put(Boolean.FALSE, "Amigo não encontrado");
        }
        return resposta;
    }

    public HashMap<Boolean, String> atualizarAmigo(Amigo amigo) {
        HashMap<Boolean, String> resposta = new HashMap<Boolean, String>();

        try {
            if (verificarAmigo(amigo)) {
                dao.update(amigo);
                resposta.put(Boolean.TRUE, "Registro de " + amigo.getNome() + "foi atualizado com sucesso");
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

    public boolean deletarAmigo(Amigo amigo) {
        try {
            dao.delete(amigo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean verificarCampos(String nome, String dataNasc, String cpf, String fone, String email) {

        AmigoController.camposInvalidos = new ArrayList<>();

        if (!nome.matches(AmigoER.ER_NOME)) {
            AmigoController.camposInvalidos.add("nome");
        }
        if (!dataNasc.matches(AmigoER.ER_DATA_NASCIMENTO)) {
            AmigoController.camposInvalidos.add("data de nascimento");
        }
        if (!cpf.matches(AmigoER.ER_CPF)) {
            AmigoController.camposInvalidos.add("cpf");
        }
        if (!fone.matches(AmigoER.ER_FONE)) {
            AmigoController.camposInvalidos.add("telefone");
        }
        if (!email.matches(AmigoER.ER_EMAIL)) {
            AmigoController.camposInvalidos.add("email");
        }

        return AmigoController.camposInvalidos.isEmpty();
    }

    public static boolean verificarCamposAtualizacao(String nome, String dataNasc, String cpf, String fone, String email) {

        AmigoController.camposInvalidos = new ArrayList<>();

        if (!nome.matches(AmigoER.ER_NOME) && !"".equals(nome)) {
            AmigoController.camposInvalidos.add("nome");
        }
        if (!dataNasc.matches(AmigoER.ER_DATA_NASCIMENTO) && !"".equals(dataNasc)) {
            AmigoController.camposInvalidos.add("data de nascimento");
        }
        if (!cpf.matches(AmigoER.ER_CPF) && !"".equals(cpf)) {
            AmigoController.camposInvalidos.add("cpf");
        }
        if (!fone.matches(AmigoER.ER_FONE) && !"".equals(fone)) {
            AmigoController.camposInvalidos.add("telefone");
        }
        if (!email.matches(AmigoER.ER_EMAIL) && !"".equals(email)) {
            AmigoController.camposInvalidos.add("email");
        }

        return AmigoController.camposInvalidos.isEmpty();
    }

    public boolean nomeValidador(String nome) {
        return nome.matches(AmigoER.ER_NOME);
    }

    public boolean dataNascimentoValidador(String data) {
        return data.matches(AmigoER.ER_DATA_NASCIMENTO);
    }

    public boolean cpfValidador(String cpf) {
        return cpf.matches(AmigoER.ER_CPF);
    }

    public boolean foneValidador(String fone) {
        return fone.matches(AmigoER.ER_FONE);
    }

    public boolean emailValidador(String email) {
        return email.matches(AmigoER.ER_EMAIL);
    }

    private static boolean verificarAmigo(Amigo amigo) {
        String nome = amigo.getNome();
        String data = Utils.calendToString(amigo.getDataNascimento());
        String cpf = amigo.getCpf();
        String fone = amigo.getFone();
        String email = amigo.getEmail();

        return verificarCampos(nome, data, cpf, fone, email);
    }

}
