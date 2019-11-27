package Main;

import java.util.Scanner;
import model.*;
import controller.AmigoController;
import controller.EnderecoController;
import java.util.ArrayList;
import java.util.HashMap;
import menus.Menu;

/**
 * @author Thairam Michel
 */
public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AmigoController amigoControl = new AmigoController();
        EnderecoController enderecoControl = new EnderecoController();
        HashMap<Boolean, Object> resultAmigo = new HashMap<Boolean, Object>();
        HashMap<Boolean, Object> resultEndereco;
        ArrayList<Endereco> enderecos = new ArrayList<>();

        while (true) {
            int opcao = 0;

            do {
                Menu.menuPrincipal();
                try {
                    System.out.print("Digite uma das opções: ");
                    opcao = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                }
            } while (opcao < 0 || opcao > 5);

            switch (opcao) {
                case 0:
                    System.exit(0);
                case 1: // Amigos
//                    AmigoController amigoControl = new AmigoController();
//                    EnderecoController enderecoControl = new EnderecoController();
//                    HashMap<Boolean, Object> resultAmigo = new HashMap<Boolean, Object>();
//                    ArrayList<Endereco> enderecos = new ArrayList<>();
//                    enderecos = enderecoControl.listarTodosOsEnderecos();
                    enderecos = enderecoControl.listarTodosOsEnderecos();

                    while (opcao != 0) {
                        do {
                            Menu.menuPrincipalAmigos();
                            try {
                                System.out.print("Digite uma das opções: ");
                                opcao = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Digite uma opção válida!");
                            }

                            switch (opcao) {
                                case 0:
                                    break;
                                case 1:
                                    // Adicionar amigo
                                    System.out.println(" *** Insira os dados do amigo ***");

                                    System.out.println("Nome(Ao menos 3 letras): ");
                                    String nome = sc.nextLine();

                                    System.out.println("Data de nascimento(00/00/0000): ");
                                    String dataNascimento = sc.nextLine();

                                    System.out.println("Cpf(111.111.111-00): ");
                                    String cpf = sc.nextLine();

                                    System.out.println("telefone(083 98765-4321): ");
                                    String fone = sc.nextLine();

                                    System.out.println("Email(example@gmail.com): ");
                                    String email = sc.nextLine();

                                    int opEndereco = 0;

                                    String mensagem1 = "Escolha um dos endereços: ";
                                    String mensagem2 = "Digite 0 para sair!";

                                    do {
                                        Menu.apresentarListaEnderecos(enderecos, mensagem1, mensagem2);
                                        try {
                                            opEndereco = Integer.parseInt(sc.nextLine());
                                        } catch (Exception e) {
                                            System.out.println("Digite uma opção válida!");
                                        }
                                    } while (opEndereco < 0 || opEndereco > enderecos.size());

                                    if (opEndereco == 0) {
                                        break;
                                    }

                                    Endereco endereco = enderecos.get(opEndereco - 1);
                                    resultAmigo = amigoControl.salvarAluno(endereco, nome, dataNascimento, cpf, fone, email);

                                    System.out.println(resultAmigo.containsKey(true)
                                            ? "Amigo cadastrado com sucesso" : resultAmigo.get(false));
                                    break;
                                case 2:
                                    // Atualizar amigo
                                    System.out.println("Informe o cpf do amigo(111.111.111-00): ");
                                    cpf = sc.nextLine();
                                    resultAmigo = amigoControl.buscarAmigoPorCpf(cpf);
                                    Menu.apresentarAmigo(resultAmigo);

                                    if (resultAmigo.containsKey(true)) {
                                        Amigo amigo = (Amigo) resultAmigo.get(true);

                                        System.out.println("Atenção: caso não pretenda atualizar um determinado campo, apenas aperte a tecla enter!");
                                        System.out.println("Informe o novo nome: ");
                                        nome = sc.nextLine();

                                        System.out.println("Informe a nova data de nascimento(00/00/0000): ");
                                        dataNascimento = sc.nextLine();

                                        System.out.println("Informe o novo cpf(111.111.111-00): ");
                                        cpf = sc.nextLine();

                                        System.out.println("Informe o novo telefone(083 98765-4321): ");
                                        fone = sc.nextLine();

                                        System.out.println("Informe o novo email(example@gmail.com): ");
                                        email = sc.nextLine();

                                        opEndereco = 0;
                                        mensagem1 = "Escolha um dos endereços: ";
                                        mensagem2 = "Caso não deseje modificar o endereço digite 0!";
                                        do {
                                            Menu.apresentarListaEnderecos(enderecos, mensagem1, mensagem2);
                                            try {
                                                opEndereco = Integer.parseInt(sc.nextLine());
                                            } catch (Exception e) {
                                                System.out.println("Digite uma opção válida!");
                                            }
                                        } while (opEndereco < 0 || opEndereco > enderecos.size());

                                        boolean camposValidos = amigoControl
                                                .verificarCamposAtualizacao(nome, dataNascimento, cpf, fone, email);

                                        if (camposValidos) {
                                            if (!"".equals(nome)) {
                                                amigo.setNome(nome);
                                            }
                                            if (!"".equals(dataNascimento)) {
                                                amigo.setDataNascimento(Utils.stringToCalend(dataNascimento));
                                            }
                                            if (!"".equals(cpf)) {
                                                amigo.setCpf(cpf);
                                            }
                                            if (!"".equals(fone)) {
                                                amigo.setFone(fone);
                                            }
                                            if (!"".equals(email)) {
                                                amigo.setEmail(email);
                                            }
                                            if (opEndereco != 0) {
                                                amigo.setEndereco(enderecos.get(opEndereco - 1));
                                            }

                                            HashMap<Boolean, String> resultAtualizacaoAmigo = amigoControl
                                                    .atualizarAmigo(amigo);

                                            System.out.println(resultAtualizacaoAmigo.containsKey(true)
                                                    ? "Amigo atualizado com sucesso"
                                                    : "ERRO!");
                                        } else {
                                            System.out.println("Dados inválidos!");
                                        }
                                    }

                                    break;
                                case 3:
                                    // Listar todos os amigos
                                    ArrayList<Amigo> lista = amigoControl.listarTodosOsAmigos();
                                    Menu.apresentarListaAmigos(lista);
                                    break;
                                case 4:
                                    // Buscar amigo pelo cpf
                                    System.out.println("Informe o cpf do amigo(111.111.111-00): ");
                                    cpf = sc.nextLine();
                                    resultAmigo = amigoControl.buscarAmigoPorCpf(cpf);
                                    Menu.apresentarAmigo(resultAmigo);
                                    break;
                            }

                        } while (opcao < 0 || opcao > 4);
                    }
                case 2:
                    // Quadrinhos
                    break;
                case 3:
                    // Empréstimo
                    break;
                case 4:
                    // Coleções
                    break;
                case 5:
                    // Endereços
                    while (opcao != 0) {
                        enderecos = enderecoControl.listarTodosOsEnderecos();

                        do {
                            Menu.menuPrincipalEnderecos();
                            try {
                                opcao = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Digite uma opção válida!");
                            }
                        } while (opcao < 0 || opcao > 4);

                        switch (opcao) {
                            case 0:
                                break;
                            case 1:
                                // Adicionar endereço
                                System.out.println("Informe o nome da rua: ");
                                String rua = sc.nextLine();

                                System.out.println("Informe o bairro: ");
                                String bairro = sc.nextLine();

                                System.out.println("Informe a cidade: ");
                                String cidade = sc.nextLine();

                                System.out.println("Informe o estado(UF): ");
                                String uf = sc.nextLine();

                                System.out.println("Informe o cep(58110223): ");
                                String cep = sc.nextLine();

                                resultEndereco = enderecoControl.salvarEndereco(rua, bairro, cidade, uf, cep);

                                System.out.println(resultEndereco.containsKey(true)
                                        ? "Endereço cadastrado com sucesso" : resultEndereco.get(false));

                                break;
                            case 2:
                                // Atualizar endereço

                                int opEndereco = 0;

                                String mensagem1 = "Informe o endereço a ser atualizado: ";
                                String mensagem2 = "Digite 0 para cancelar!";
                                do {
                                    Menu.apresentarListaEnderecos(enderecos, mensagem1, mensagem2);
                                    try {
                                        opEndereco = Integer.parseInt(sc.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Digite uma opção válida!");
                                    }
                                } while (opEndereco < 0 || opEndereco > enderecos.size());

                                if (opEndereco == 0) {
                                    break;
                                }

                                Endereco endereco = enderecos.get(opEndereco - 1);
                                mensagem1 = "*** Endereço que será utilizado ***";
                                Menu.apresentarEndereco(endereco, mensagem1);

                                System.out.println("Atenção: caso não pretenda atualizar um determinado campo, apenas aperte a tecla enter!");

                                System.out.println("Informe o novo nome da rua: ");
                                rua = sc.nextLine();

                                System.out.println("Informe o novo bairro: ");
                                bairro = sc.nextLine();

                                System.out.println("Informe a nova cidade: ");
                                cidade = sc.nextLine();

                                System.out.println("Informe o novo estado(UF): ");
                                uf = sc.nextLine();

                                System.out.println("Informe o novo cep(58110223): ");
                                cep = sc.nextLine();

                                boolean camposValidos = enderecoControl
                                        .verificarCamposAtualizacao(rua, bairro, cidade, uf, cep);

                                if (camposValidos) {
                                    if (!"".equals(rua)) {
                                        endereco.setRua(rua);
                                    }
                                    if (!"".equals(bairro)) {
                                        endereco.setBairro(bairro);
                                    }
                                    if (!"".equals(cidade)) {
                                        endereco.setCidade(cidade);
                                    }
                                    if (!"".equals(uf)) {
                                        endereco.setUf(uf);
                                    }
                                    if (!"".equals(cep)) {
                                        endereco.setCep(cep);
                                    }

                                    HashMap<Boolean, Object> resultAtualizacaoEndereco
                                            = enderecoControl.atualizarEndereco(endereco);

                                    System.out.println(resultAtualizacaoEndereco.containsKey(true)
                                            ? "Endereço atualizado com sucesso\n"
                                            : "Já existe um registro com o mesmo cep!\n");
                                } else {
                                    System.out.println("Dados inválidos!\n");
                                }

                                break;

                            case 3:
                                // Listar endereços
                                Menu.apresentarListaEnderecos(enderecos, "", "");
                                break;
                            case 4:
                                // Excluir endereço
                                mensagem1 = "Informe o endereço a ser deletado: ";
                                mensagem2 = "Digite 0 para cancelar!";
                                opEndereco = 0;
                                do {
                                    Menu.apresentarListaEnderecos(enderecos, mensagem1, mensagem2);
                                    try {
                                        opEndereco = Integer.parseInt(sc.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Digite uma opção válida!");
                                    }
                                } while (opEndereco < 0 || opEndereco > enderecos.size());

                                if (opEndereco == 0) {
                                    break;
                                }

                                boolean result = enderecoControl.deletarEndereco(enderecos.get(opEndereco - 1));

                                System.out.println(result
                                        ? "Endereço deletado com sucesso!" : "Falha na operação!");

                                break;

                        }

                    }
                    break;
            }
        }
    }

}
