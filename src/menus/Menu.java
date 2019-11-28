package menus;

import java.util.ArrayList;
import java.util.HashMap;
import model.*;

/**
 *
 * @author Thairam Michel
 */
public class Menu {

    public static void menuPrincipal() {
        System.out.println("***** MENU PRINCIPAL *****");
        System.out.println("1: Amigos\n2: Quadrinhos\n3: Empréstimo\n4: Coleções\n5: Endereços\n0: finalizar\n");
    }

    public static void menuPrincipalAmigos() {
        System.out.println("****** MENU AMIGO ******");
        System.out.println("1: Adicionar amigo\n2: Atualizar amigo\n3: Listar amigos\n4: Buscar amigo por cpf\n0: Voltar\n");
    }

    public static void menuPrincipalEnderecos() {
        System.out.println("****** MENU ENDEREÇO ******");
        System.out.println("1: Adicionar endereço\n2: Atualizar endereço\n3: Listar endereços\n4: Excluir endereço\n0: Voltar\n");
    }

    public static void menuPrincipalQuadrinhos() {
        System.out.println("****** MENU QUADRINHO ******");
        System.out.println("1: Adicionar quadrinho\n2: Atualizar quadrinho\n3: "
                + "Listar quadrinhos\n4: Excluir quadrinho\n5: Buscar quadrinho pelo nome\n"
                + "0: Voltar\n");
    }

    public static void apresentarListaAmigos(ArrayList<Amigo> amigos) {
        if (amigos.isEmpty()) {
            System.out.println("Nenhum amigo registrado!");
        } else {
            System.out.println("");
            for (Amigo amigo : amigos) {
                System.out.println("Nome: " + amigo.getNome() + " || Cpf: " + amigo.getCpf()
                        + " || Data de Nascimento: " + Utils.calendToString(amigo.getDataNascimento())
                        + " || Email: " + amigo.getEmail());
            }
            System.out.println("\n");
        }
    }

    public static void apresentarListaEnderecos(ArrayList<Endereco> enderecos, String msg1, String msg2) {
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço registrado!");
        } else {
            System.out.println(msg1);
            for (int i = 0; i < enderecos.size(); i++) {
                System.out.println((i + 1) + ":" + enderecos.get(i).getCidade() + " - "
                        + enderecos.get(i).getRua() + " - " + enderecos.get(i).getBairro() + " - "
                        + enderecos.get(i).getCep() + " - " + enderecos.get(i).getUf());
            }
            System.out.println(msg2 + "\n");
        }
    }

    public static void apresentarListaQuadrinhos(ArrayList<Quadrinho> quadrinhos, String msg1, String msg2) {
        if (quadrinhos.isEmpty()) {
            System.out.println("Nenhum quadrinho registrado!");
        } else {
            System.out.println(msg1);
            for (Quadrinho quadrinho : quadrinhos) {
                System.out.println(
                        "(" + (quadrinhos.indexOf(quadrinho) + 1) + "): "
                        + "Nome: " + quadrinho.getNome() + " || Valor: R$" + String.format("%.2f", quadrinho.getValor())
                        + " || Edição: " + quadrinho.getEdicao() + " || Genêro: " + quadrinho.getGenero()
                        + "\nVersões registradas: \n* Física: "
                        + (quadrinho.isVersaoFisica() ? "registrada\n" : "não registrada\n") + "* Digital: "
                        + (quadrinho.isVersaoDigital() ? "registrada\n" : "não registrada\n")
                        + "Disponibilidade: " + (quadrinho.isDisponibilidade() ? "Disponível" : "indisponível")
                        + "\n");
            }
            System.out.println(msg2);
        }
    }

    public static void apresentarAmigo(HashMap<Boolean, Object> resultAmigo) {
        if (resultAmigo.containsKey(true)) {
            Amigo amigo = (Amigo) resultAmigo.get(true);
            System.out.println("Nome: " + amigo.getNome() + " || Cpf: " + amigo.getCpf()
                    + " || Data de Nascimento: " + Utils.calendToString(amigo.getDataNascimento())
                    + " || Email: " + amigo.getEmail() + "\n");
        } else {
            System.out.println(resultAmigo.get(false) + "\n");
        }
    }

    public static void apresentarEndereco(Endereco endereco, String msg) {
        System.out.println(msg);
        System.out.println(endereco.getCidade() + " - "
                + endereco.getRua() + " - " + endereco.getBairro() + " - "
                + endereco.getCep() + " - " + endereco.getUf() + "\n");
    }

    public static void apresentarQuadrinho(HashMap<Boolean, Object> resultQuadrinho) {
        if (resultQuadrinho.containsKey(true)) {
            Quadrinho quadrinho = (Quadrinho) resultQuadrinho.get(true);
            System.out.println(
                    "Nome: " + quadrinho.getNome() + " || Valor: R$" + String.format("%.2f", quadrinho.getValor())
                    + " || Edição: " + quadrinho.getEdicao() + " || Genêro: " + quadrinho.getGenero()
                    + "\nVersões registradas: \n* Física: "
                    + (quadrinho.isVersaoFisica() ? "registrada\n" : "não registrada\n") + "* Digital: "
                    + (quadrinho.isVersaoDigital() ? "registrada\n" : "não registrada\n")
                    + "Disponibilidade: " + (quadrinho.isDisponibilidade() ? "Disponível" : "indisponível")
                    + "\n");
        } else {
            System.out.println(resultQuadrinho.get(false) + "\n");
        }

    }

    public static void apresentarCuriosidadeQuadrinho(Quadrinho quadrinho) {

        if ("".equals(quadrinho.getCuriosidade()) && "".equals(quadrinho.isRecomendavel())) {
            System.out.println("O quadrinho ainda não possui curiosidades registradas!");
        } else {
            System.out.println("\n*** INFORMAÇÔES ***");

            System.out.println("Curiosidade: " + (!quadrinho.getCuriosidade().equals("")
                    ? quadrinho.getCuriosidade() : "Nenhuma curiosidade registrada!"));

            System.out.println("Recomendação: " + (quadrinho.isRecomendavel()
                    ? "È recomendável a leitura do quadrinho!" : "Não é recomendável a leitura do quadrinho!"));

            System.out.println("NOTA: " + quadrinho.getNota() + "\n");

        }
    }

}
