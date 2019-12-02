package menu;

import java.util.ArrayList;
import java.util.HashMap;
import model.*;

/**
 *
 * @author Thairam Michel
 */
public class Menu {

    public static void menuPrincipal() {
        System.out.println("\n***** MENU PRINCIPAL *****");
        System.out.println("1: Amigos\n2: Quadrinhos\n3: Empréstimo\n4: Endereços\n5: Coleções\n0: Finalizar\n");
    }

    public static void menuPrincipalAmigos() {
        System.out.println("\n****** MENU AMIGO ******");
        System.out.println("1: Adicionar amigo\n2: Atualizar amigo\n3: Listar amigos\n4: Buscar amigo por cpf\n0: Voltar\n");
    }

    public static void menuPrincipalEnderecos() {
        System.out.println("\n****** MENU ENDEREÇO ******");
        System.out.println("1: Adicionar endereço\n2: Atualizar endereço\n3: Listar endereços\n4: Excluir endereço\n0: Voltar\n");
    }

    public static void menuPrincipalQuadrinhos() {
        System.out.println("\n****** MENU QUADRINHO ******");
        System.out.println("1: Adicionar quadrinho\n2: Atualizar quadrinho\n3: "
                + "Listar quadrinhos\n4: Buscar quadrinho pelo nome\n"
                + "0: Voltar\n");
    }

    public static void menuPrincipalColecoes() {
        System.out.println("\n****** MENU COLEÇÕES ******");
        System.out.println("1: Adicionar coleção\n2: Atualizar coleção\n3: "
                + "Listar coleções\n0: Voltar\n");
    }

    public static void menuPrincipalEmprestimos() {
        System.out.println("\n****** MENU EMPRÈSTIMO ******");
        System.out.println("1: Efetuar empréstimo\n2: Efetuar devolução\n3: Listar empréstimos\n0: Voltar\n");
    }

    public static void apresentarAmigos(ArrayList<Amigo> amigos, String mensagem) {
        if (amigos.isEmpty()) {
            System.out.println("\n*** Nenhum amigo registrado ***");
        } else {
            System.out.println(mensagem);
            for (Amigo amigo : amigos) {
                System.out.println(
                        "(" + (amigos.indexOf(amigo) + 1) + "): "
                        + "Nome: " + amigo.getNome() + " || Cpf: " + amigo.getCpf()
                        + " || Data de Nascimento: " + Utils.calendToString(amigo.getDataNascimento())
                        + " || Email: " + amigo.getEmail());
            }
            System.out.println();
        }
    }

    public static void apresentarEnderecos(ArrayList<Endereco> enderecos, String msg1, String msg2) {
        if (enderecos.isEmpty()) {
            System.out.println("\n*** Nenhum endereço registrado ***");
        } else {
            System.out.println(msg1);
            for (int i = 0; i < enderecos.size(); i++) {
                System.out.println((i + 1) + ": " + enderecos.get(i).getCidade() + " - "
                        + enderecos.get(i).getRua() + " - " + enderecos.get(i).getBairro() + " - "
                        + enderecos.get(i).getCep() + " - " + enderecos.get(i).getUf());
            }
        }
        System.out.print("\n" + msg2);
    }

    public static void apresentarQuadrinhos(ArrayList<Quadrinho> quadrinhos, String msg1, String msg2) {
        if (quadrinhos.isEmpty()) {
            System.out.println("\n** Nenhum quadrinho registrado! **\n");
        } else {
            System.out.println(msg1);
            for (Quadrinho quadrinho : quadrinhos) {
                System.out.println(
                        "(" + (quadrinhos.indexOf(quadrinho) + 1) + "): "
                        + "Nome: " + quadrinho.getNome() + " || Valor: R$" + String.format("%.2f", quadrinho.getValor())
                        + " || Edição: " + quadrinho.getEdicao() + " || Genêro: " + quadrinho.getGenero()
                        + "\nVersões registradas: \n(*) Versão Física: "
                        + (quadrinho.isVersaoFisica() ? "registrada" : "não registrada")
                        + " || " + (quadrinho.getDisponibilidadeFisica() ? "Disponível\n" : "Indisponível\n")
                        + "(*) Versão Digital: " + (quadrinho.isVersaoDigital() ? "registrada" : "não registrada")
                        + " || " + (quadrinho.getDisponibilidadeDigital() ? "Disponível\n" : "Indisponível\n"));
            }
            System.out.println(msg2);
        }
    }

    public static void apresentarEmprestimos(ArrayList<Emprestimo> emprestimos, String msg1, String msg2, String msg3) {
        if (emprestimos.isEmpty()) {
            System.out.println(msg1);
        } else {
            System.out.println("\n*** LISTA DE EMPRÈSTIMOS ***");
            System.out.println(msg2);
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(
                        "(" + (emprestimos.indexOf(emprestimo) + 1) + "): "
                        + "Amigo: " + emprestimo.getAmigo().getNome()
                        + " || Data do empréstimo: " + Utils.calendToString(emprestimo.getDataEmprestimo())
                        + " || Data da devolução: " + Utils.calendToString(emprestimo.getDataDevolucao())
                        + " || O empréstimo está: " + emprestimo.getEstado());
            }
        }
        System.out.println(msg3);
    }

    public static void apresentarAmigo(HashMap<Boolean, Object> resultAmigo) {
        if (resultAmigo.containsKey(true)) {
            Amigo amigo = (Amigo) resultAmigo.get(true);
            System.out.println("\n***** AMIGO *****");
            System.out.println("Nome: " + amigo.getNome() + " || Cpf: " + amigo.getCpf()
                    + " || Data de Nascimento: " + Utils.calendToString(amigo.getDataNascimento())
                    + " || Email: " + amigo.getEmail() + "\n");
        } else {
            System.out.println("\n*** " + resultAmigo.get(false) + " ***\n");
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
                    + "Disponibilidade: " + (quadrinho.getDisponibilidadeFisica() ? "Disponível" : "indisponível")
                    + "\n");
        } else {
            System.out.println(resultQuadrinho.get(false) + "\n");
        }

    }

    public static void apresentarComprovanteEmprestimo(Emprestimo emprestimo) {
        System.out.println("\n*** COMPROVANTE DO EMPRÉSTIMO ***");
        System.out.println("Empréstimo efetuado para: " + emprestimo.getAmigo().getNome());
        System.out.println("Data do empréstimo: " + Utils.calendToString(emprestimo.getDataEmprestimo()));
        System.out.println("Data da devolução: " + Utils.calendToString(emprestimo.getDataDevolucao()) + "\n");
    }

    public static void apresentarComprovanteDevolucao(Emprestimo emprestimo) {
        System.out.println("\n*** COMPROVANTE DA DEVOLUÇÃO ***");
        System.out.println("Amigo: " + emprestimo.getAmigo().getNome());
        System.out.println("Data do empréstimo: " + Utils.calendToString(emprestimo.getDataEmprestimo()));
        System.out.println("Data da devolução: " + Utils.calendToString(emprestimo.getDataDevolucao()) + "\n");
        System.out.println("*** DEVOLUÇÃO EFETUADA COM SUCESSO ***\n");
    }

    public static void apresentarQuadrinhosDisponiveis(ArrayList<Quadrinho> quadrinhos, String msg1, String msg2) {
        if (quadrinhos.isEmpty()) {
            System.out.println("\n** Não há nenhum quadrinho disponível para empréstimo no momento! **\n");
        } else {
            System.out.println(msg1);
            for (Quadrinho quadrinho : quadrinhos) {
                System.out.println(
                        "(" + (quadrinhos.indexOf(quadrinho) + 1) + "): "
                        + "Nome: " + quadrinho.getNome() + " || Valor: R$" + String.format("%.2f", quadrinho.getValor())
                        + " || Edição: " + quadrinho.getEdicao() + " || Genêro: " + quadrinho.getGenero());
                if (quadrinho.getDisponibilidadeFisica()) {
                    System.out.println("(F): Versão Física Disponível");
                }
                if (quadrinho.getDisponibilidadeDigital()) {
                    System.out.println("(D): Versão Digital Disponível");
                }
                System.out.println("");
            }
            System.out.println(msg2);
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
