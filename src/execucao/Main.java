package execucao;

import java.util.Scanner;
import model.*;
import controller.*;
import java.util.ArrayList;
import java.util.HashMap;
import menu.Menu;

/**
 * @author Thairam Michel
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AmigoController amigoControl = new AmigoController();
        EnderecoController enderecoControl = new EnderecoController();
        QuadrinhoController quadrinhoControl = new QuadrinhoController();
        EmprestimoController emprestimoControl = new EmprestimoController();

        HashMap<Boolean, Object> resultAmigo;
        HashMap<Boolean, Object> resultEndereco;
        HashMap<Boolean, Object> resultQuadrinho;
        HashMap<Boolean, Object> resultEmprestimo;

        ArrayList<Endereco> enderecos;
        ArrayList<Quadrinho> quadrinhos;
        ArrayList<Emprestimo> emprestimos;
        ArrayList<Amigo> amigos;

        String login;
        String senha;
        boolean permissao = false;

        do {
            System.out.println("*** INFORME SUAS CREDENCIAIS DE ACESSO ***");
            System.out.print("Login: ");
            login = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextLine();
            if ("admin".equals(login) && "admin".equals(senha)) {
                permissao = true;
            }
            System.out.println((permissao ? "* Suas credenciais foram autenticadas com sucesso! *"
                    : "* Dados inválidos, por favor informe suas credenciais corretamente! *") + "\n");
        } while (!permissao);

        System.out.println("*** BEM VINDO AO CONTROLE DE QUADRINHOS ***");

        while (true) {
            int opcao = 0;

            do {
                Menu.menuPrincipal();
                try {
                    System.out.print("Digite uma das opções: ");
                    opcao = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Digite uma opção válida!");
                }
            } while (opcao < 0 || opcao > 5);

            switch (opcao) {
                case 0:
                    System.exit(0);
                case 1:
                    // Amigos

                    while (opcao != 0) {
                        enderecos = enderecoControl.listarTodosOsEnderecos();
                        amigos = amigoControl.listarTodosOsAmigos();

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

                                    System.out.println("Nome: ");
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
                                    String mensagem2 = "Opção(Digite 0 para sair): ";

                                    do {
                                        Menu.apresentarEnderecos(enderecos, mensagem1, mensagem2);
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
                                    resultAmigo = amigoControl.salvarAmigo(endereco, nome, dataNascimento, cpf, fone, email);

                                    System.out.println(resultAmigo.containsKey(true)
                                            ? "** Amigo cadastrado com sucesso **"
                                            : "\n* Foram fornecidos valores inválidos para os seguintes campos: \n"
                                            + resultAmigo.get(false));

                                    break;
                                case 2:
                                    // Atualizar amigo
                                    System.out.println("Informe o cpf do amigo(111.111.111-00) que será atualizado: ");
                                    cpf = sc.nextLine();
                                    resultAmigo = amigoControl.buscarAmigoPeloCpf(cpf);
                                    Menu.apresentarAmigo(resultAmigo);

                                    if (resultAmigo.containsKey(true)) {
                                        Amigo amigo = (Amigo) resultAmigo.get(true);

                                        System.out.println("** Atenção: caso não pretenda atualizar um determinado campo, apenas aperte a tecla enter! **");
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
                                        mensagem2 = "Caso não deseje modificar o endereço digite (0): ";
                                        do {
                                            Menu.apresentarEnderecos(enderecos, mensagem1, mensagem2);
                                            try {
                                                opEndereco = Integer.parseInt(sc.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Digite uma opção válida!");
                                            }
                                        } while (opEndereco < 0 || opEndereco > enderecos.size());

                                        boolean camposValidos = amigoControl
                                                .verificarCamposAtualizacao(amigo, nome, dataNascimento, cpf, fone, email);

                                        if (camposValidos) {
                                            if (opEndereco != 0) {
                                                amigo.setEndereco(enderecos.get(opEndereco - 1));
                                            }

                                            HashMap<Boolean, Object> resultAtualizacaoAmigo = amigoControl
                                                    .atualizarAmigo(amigo);

                                            System.out.println(resultAtualizacaoAmigo.containsKey(true)
                                                    ? "\n** Amigo atualizado com sucesso **"
                                                    : "(*) Os campos a seguir foram fornecidos com valores inválidos: \n(*) "
                                                    + resultAmigo.get(false) + ".\n");
                                        } else {
                                            System.out.println("\n*** Dados inválidos, foram inseridos ao tentar "
                                                    + "atualizar o amigo ***");
                                        }
                                    }

                                    break;
                                case 3:
                                    String mensagem = "\n***** AMIGOS *****";
                                    Menu.apresentarAmigos(amigos, mensagem);
                                    break;
                                case 4:
                                    // Buscar amigo pelo cpf
                                    System.out.println("Informe o cpf do amigo(111.111.111-00): ");
                                    cpf = sc.nextLine();
                                    resultAmigo = amigoControl.buscarAmigoPeloCpf(cpf);
                                    Menu.apresentarAmigo(resultAmigo);
                                    break;
                            }

                        } while (opcao < 0 || opcao > 4);
                    }
                case 2:
                    // Quadrinhos
                    while (opcao != 0) {
                        int opcaoQuadrinho = 0;
                        quadrinhos = quadrinhoControl.listarTodosOsQuadrinhos();
                        do {
                            Menu.menuPrincipalQuadrinhos();
                            try {
                                System.out.print("Digite uma das opções: ");
                                opcao = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Digite uma opção válida!");
                            }
                        } while (opcao < 0 || opcao > 4);

                        switch (opcao) {
                            case 0:
                                break;
                            case 1:
                                // Adicionar quadrinho
                                System.out.println(" *** Insira os dados do quadrinho ***");

                                try {
                                    System.out.print("Informe o nome do quadrinho: ");
                                    String nomeQuadrinho = sc.nextLine();

                                    System.out.print("Informe o valor do quadrinho(ex: 16.90): ");
                                    double valor = Double.parseDouble(sc.nextLine());

                                    System.out.print("Informe a editora(DC Comics): ");
                                    String editora = sc.nextLine();

                                    System.out.println("Informe o ISBN(ex: 9870044550127 - "
                                            + "informar apenas dígitos(10 ou 13) sem espaços): ");
                                    String isbn = sc.nextLine();

                                    System.out.println("Informe as versões disponíveis: ");

                                    System.out.print("Versão Física(s ou n): ");
                                    String versaoFisica = sc.nextLine();

                                    System.out.print("Versão Digital(s ou n): ");
                                    String versaoDigital = sc.nextLine();

                                    System.out.print("Informe a edição(ex: primeira edição): ");
                                    String edicao = sc.nextLine();

                                    System.out.print("Informe o genêro: ");
                                    String genero = sc.nextLine();

                                    System.out.println("\n*** Caso não deseje informar os campos opcionais, aperte a tecla enter ***");

                                    System.out.println("(Opcional) Informe alguma curiosidade sobre o quadrinho"
                                            + "(ex: Inspirou o filme): ");
                                    String curiosidade = sc.nextLine();

                                    System.out.print("(Opcional) Informe se o você leria novamente o quadrinho"
                                            + "(s ou n): ");
                                    String relevancia = sc.nextLine();

                                    System.out.print("(Opcional) Atribua uma nota para o quadrinho(0 - 10): ");
                                    String notaS = sc.nextLine();
                                    double nota = Double.parseDouble(notaS.equals("") ? "0" : notaS);

                                    resultQuadrinho = quadrinhoControl.salvarQuadrinho(nomeQuadrinho, valor, editora, isbn,
                                            versaoFisica, versaoDigital, edicao, genero, curiosidade,
                                            nota, relevancia);

                                    System.out.println(resultQuadrinho.containsKey(true)
                                            ? "Quadrinho cadastrado com sucesso!\n"
                                            : "(*) Os campos a seguir foram fornecidos com valores inválidos: \n(*) "
                                            + resultQuadrinho.get(false) + ".\n");

                                } catch (NumberFormatException e) {
                                    System.out.println("\n** Dado inválido para o campo! **");
                                    System.out.println("** É importante seguir os padrões dos exemplos fornecidos nos campos! **");
                                    System.out.println("** Informe um valor válido da próxima vez! **\n");
                                    break;
                                }

                                break;
                            case 2:
                                // Atualizar quadrinho
                                Menu.apresentarQuadrinhos(quadrinhos, "", "");
                                opcaoQuadrinho = 0;
                                do {
                                    System.out.println("** Informe o quadrinho, que será "
                                            + "atualizado **\n** Digite 0"
                                            + " para sair!  **");
                                    try {
                                        opcaoQuadrinho = Integer.parseInt(sc.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Digite uma opção válida!");
                                    }
                                } while (opcaoQuadrinho < 0 || opcaoQuadrinho > quadrinhos.size());

                                if (opcaoQuadrinho == 0) {
                                    break;
                                }

                                Quadrinho quadrinho = quadrinhos.get(opcaoQuadrinho - 1);

                                System.out.println("*** ATENÇÂO ***");
                                System.out.println("*** Caso não deseje alterar o valor de um campo, aperte a tecla ENTER ***\n");
                                try {
                                    System.out.print("Informe o novo nome do quadrinho: ");
                                    String nomeQuadrinho = sc.nextLine();

                                    System.out.print("Informe o novo valor do quadrinho(ex: 16.90): ");
                                    String valorS = sc.nextLine();
                                    double valor = Double.parseDouble(valorS.equals("") ? "1111" : valorS);

                                    System.out.print("Informe a editora(DC Comics): ");
                                    String editora = sc.nextLine();

                                    System.out.println("Informe o ISBN(ex: 9870044550127 - "
                                            + "informar apenas dígitos(10 ou 13) sem espaços): ");
                                    String isbn = sc.nextLine();

                                    String versaoFisica = "";
                                    if (!quadrinho.isVersaoFisica()) {
                                        System.out.print("Deseja incluir a versão física do quadrinho(s ou n): ");
                                        versaoFisica = sc.nextLine();
                                    }

                                    String versaoDigital = "";
                                    if (!quadrinho.isVersaoDigital()) {
                                        System.out.print("Deseja incluir a versão Digital(s ou n): ");
                                        versaoDigital = sc.nextLine();
                                    }

                                    System.out.print("Informe a nova edição(ex: primeira edição): ");
                                    String edicao = sc.nextLine();

                                    System.out.print("Informe o novo genêro: ");
                                    String genero = sc.nextLine();

                                    System.out.println("\n*** Caso não deseje informar os campos opcionais, aperte a tecla enter ***");

                                    System.out.println("(Opcional) Informe alguma curiosidade sobre o quadrinho"
                                            + "(ex: Inspirou o filme): ");
                                    String curiosidade = sc.nextLine();

                                    System.out.print("(Opcional) Informe se você leria novamente o quadrinho"
                                            + "(s ou n): ");
                                    String relevancia = sc.nextLine();

                                    System.out.print("(Opcional) Atribua uma nota para o quadrinho(0 - 10): ");
                                    String notaS = sc.nextLine();
                                    double nota = Double.parseDouble(notaS.equals("") ? "0.001" : notaS);

                                    resultQuadrinho = quadrinhoControl.atualizarQuadrinho(
                                            quadrinho, nomeQuadrinho, valor, editora, isbn,
                                            versaoFisica, versaoDigital, edicao, genero,
                                            curiosidade, nota, relevancia);

                                    if (resultQuadrinho.containsKey(true)) {
                                        System.out.println("\n*** Quadrinho atualizado com sucesso! ***\n");
                                    } else {
                                        String erro = (String) resultQuadrinho.get(false);
                                        if (erro.contains("encontra-se emprestada")) {
                                            System.out.println("*** Atenção ***");
                                            System.out.println("Não é possível atualizar a(s) versões disponíveis do quadrinho, "
                                                    + "pois esta(s) estão emprestadas. Por favor efetue a devolução antes!\n");
                                        } else if (erro.contains("Erro")) {
                                            System.out.println(erro + "\n");
                                        } else {
                                            System.out.println("(*) Os campos a seguir foram fornecidos com valores inválidos: \n(*) "
                                                    + erro + "\n");
                                        }
                                    }

                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("\n** Dado inválido para o campo! **");
                                    System.out.println("** É importante seguir os padrões dos exemplos fornecidos nos campos! **");
                                    System.out.println("** Informe um valor válido da próxima vez! **\n");
                                    break;
                                }
                            case 3:
                                // Listar quadrinhos
                                Menu.apresentarQuadrinhos(quadrinhos, "", "");
                                opcaoQuadrinho = 0;
                                do {
                                    System.out.println("** Informe o quadrinho, para visualizar "
                                            + "possíveis curiosidades sobre ele **\n** Digite 0"
                                            + " para sair!  **");
                                    try {
                                        opcaoQuadrinho = Integer.parseInt(sc.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Digite uma opção válida!");
                                    }
                                } while (opcaoQuadrinho < 0 || opcaoQuadrinho > quadrinhos.size());

                                if (opcaoQuadrinho != 0) {
                                    Menu.apresentarCuriosidadeQuadrinho(quadrinhos.get(opcaoQuadrinho - 1));
                                }

                                break;

                            case 4:
                                // Buscar quadrinho pelo nome
                                System.out.println("Informe o nome do quadrinho: ");
                                String nome = sc.nextLine();

                                resultQuadrinho = quadrinhoControl
                                        .buscarQuadrinhoPeloNome(nome);

                                Menu.apresentarQuadrinho(resultQuadrinho);
                                break;
                        }
                    }

                    break;
                case 3:
                    // Empréstimo
                    while (opcao != 0) {
                        emprestimos = emprestimoControl.listarTodosOsEmprestimos();
                        quadrinhos = quadrinhoControl.listarTodosOsQuadrinhosDisponiveis();
                        amigos = amigoControl.listarTodosOsAmigos();

                        do {
                            Menu.menuPrincipalEmprestimos();
                            try {
                                opcao = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Digite uma opção válida!");
                            }
                        } while (opcao < 0 || opcao > 3);

                        switch (opcao) {

                            case 0:
                                break;
                            case 1:
                                // Efetuar empréstimo
                                System.out.println("*** EFETUANDO EMPRÈSTIMO ***");
                                HashMap<Quadrinho, String> quadrinhosEmprestimo = new HashMap<Quadrinho, String>();
                                Amigo amigo;
                                while (opcao != 0) {
                                    try {
                                        if (opcao == 0) {
                                            break;
                                        }

                                        do {
                                            do {
                                                quadrinhos = quadrinhoControl.listarTodosOsQuadrinhosDisponiveis();
                                                Menu.apresentarQuadrinhosDisponiveis(quadrinhos, "", "");
                                                if (quadrinhos.isEmpty()) {
                                                    opcao = 0;
                                                    break;
                                                }
                                                System.out.print("Informe o quadrinho (Digite 0 para cancelar): ");
                                                opcao = Integer.parseInt(sc.nextLine());
                                            } while (opcao < 0 || opcao > quadrinhos.size());

                                            if (opcao == 0) {
                                                break;
                                            }

                                            Quadrinho quadrinho = quadrinhos.get(opcao - 1);

                                            do {
                                                System.out.println("\n** Informe a versão desejada **");
                                                System.out.print("(1) Física\n"
                                                        + "(2) Digital\n"
                                                        + "(0) Cancelar\n"
                                                        + "opcão: ");
                                                opcao = Integer.parseInt(sc.nextLine());
                                            } while (opcao < 0 || opcao > 2);

                                            if (opcao == 0) {
                                                break;
                                            }

                                            if (!emprestimoControl.validarEscolhaDeQuadrinho(quadrinho, opcao,
                                                    quadrinhosEmprestimo)) {
                                                System.out.println("\nVocê tentou efetuar o empréstimo de uma versão"
                                                        + " indisponível, da próxima vez esteja mais atento!");
                                                opcao = 0;
                                                break;
                                            }

                                            do {
                                                System.out.print("\nInforme uma opção válida: \n"
                                                        + "(1) concluir empréstimo\n"
                                                        + "(2) continuar adicionando\n"
                                                        + "(0) para cancelar\n"
                                                        + "opcão: ");
                                                opcao = Integer.parseInt(sc.nextLine());
                                            } while (opcao < 0 || opcao > 2);

                                        } while (opcao == 2);

                                        if (opcao == 0) {
                                            break;
                                        }

                                        System.out.println("\n** INFORMAR DATA DE DEVOLUÇÃO **");
                                        System.out.println("* Por padrão, a data do empréstimo é a data atual! *\n");

                                        System.out.print("Informar data de devolução(00/00/0000)\n"
                                                + "opção (Digite 0 para cancelar): ");
                                        String dataDevolucao = sc.nextLine();

                                        if ("0".equals(dataDevolucao)) {
                                            break;
                                        }

                                        System.out.println("\n** INFORMAR AMIGO **");
                                        do {
                                            Menu.apresentarAmigos(amigos, "");
                                            System.out.print("Informe o amigo (digite 0 para cancelar): ");
                                            opcao = Integer.parseInt(sc.nextLine());
                                        } while (opcao < 0 || opcao > amigos.size());

                                        if (opcao == 0) {
                                            break;
                                        }

                                        amigo = amigos.get(opcao - 1);

                                        resultEmprestimo = emprestimoControl
                                                .efetuarEmprestimo(quadrinhosEmprestimo, amigo, dataDevolucao);

                                        if (resultEmprestimo.containsKey(true)) {
                                            Menu.apresentarComprovanteEmprestimo(((Emprestimo) resultEmprestimo.get(true)));
                                        } else {
                                            System.out.println("\n" + resultEmprestimo.get(false));
                                        }

                                        break;

                                    } catch (NumberFormatException e) {
                                        System.out.println("Digite uma opção válida!");
                                        break;
                                    }
                                }

                                break;

                            case 2:
                                // Efetuar devolução
                                emprestimos = emprestimoControl.listarTodosOsEmprestimosDisponiveis();
                                try {
                                    do {
                                        String msg1 = "\n** Nenhum empréstimo registrado **";
                                        String msg2 = "** Informe o empréstimo que será devolvido **\n";
                                        String msg3 = "\n** Para cancelar digite 0 **";
                                        Menu.apresentarEmprestimos(emprestimos, msg1, msg2, msg3);
                                        System.out.print("opcao: ");
                                        opcao = Integer.parseInt(sc.nextLine());
                                    } while (opcao < 0 || opcao > emprestimos.size());

                                    if (opcao == 0) {
                                        break;
                                    }

                                    resultEmprestimo = emprestimoControl.efetuarDevolucao(emprestimos.get(opcao - 1));

                                    if (resultEmprestimo.containsKey(true)) {
                                        Menu.apresentarComprovanteDevolucao(((Emprestimo) resultEmprestimo.get(true)));
                                    } else {
                                        System.out.println("\n" + resultEmprestimo.get(false));
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("\nDa próxima vez, digite uma opção válida!");
                                    break;
                                }

                                break;
                            case 3:
                                // listar empréstimos
                                String msg1 = "\n** Nenhum empréstimo registrado! **";
                                Menu.apresentarEmprestimos(emprestimos, msg1, "", "");
                                break;
                        }
                    }

                    break;
                case 4:
                    // Endereços
                    while (opcao != 0) {
                        enderecos = enderecoControl.listarTodosOsEnderecos();

                        do {
                            Menu.menuPrincipalEnderecos();
                            try {
                                System.out.print("Digite uma das opções: ");
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
                                        ? "\n*** Endereço cadastrado com sucesso ***\n"
                                        : "(*) Os campos a seguir foram fornecidos com valores inválidos: \n(*) "
                                        + resultEndereco.get(false) + ".\n");
                                break;
                            case 2:
                                // Atualizar endereço

                                int opEndereco = 0;

                                String mensagem1 = "*** Informe o endereço a ser atualizado ***";
                                String mensagem2 = "Opção(Digite 0 para cancelar): ";
                                do {
                                    Menu.apresentarEnderecos(enderecos, mensagem1, mensagem2);
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
                                        .verificarCamposAtualizacao(endereco, rua, bairro, cidade, uf, cep);

                                if (camposValidos) {

                                    HashMap<Boolean, Object> resultAtualizacaoEndereco
                                            = enderecoControl.atualizarEndereco(endereco);

                                    System.out.println(resultAtualizacaoEndereco.containsKey(true)
                                            ? "\n*** Endereço atualizado com sucesso ***\n"
                                            : "\n*** Já existe um registro com o mesmo cep ***\n");
                                } else {
                                    System.out.println("Dados inválidos!\n");
                                }

                                break;

                            case 3:
                                // Listar endereços
                                String msg1 = "**** ENDEREÇOS ***";
                                Menu.apresentarEnderecos(enderecos, msg1, "");
                                break;
                            case 4:
                                // Excluir endereço
                                mensagem1 = "*** Informe o endereço a ser deletado ***";
                                mensagem2 = "Opção(Digite 0 para cancelar): ";
                                opEndereco = 0;
                                do {
                                    Menu.apresentarEnderecos(enderecos, mensagem1, mensagem2);
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
                                        ? "\n*** Endereço deletado com sucesso ***" : "\n*** Falha na operação ***");

                                break;

                        }

                    }
                    break;
                case 5:
                    // Coleções
                    System.out.println("\n********************************************************");
                    System.out.println("*** Atualmente o recurso (Coleção) está indísponível ***");
                    System.out.println("********************************************************");
                    break;

            }
        }
    }
}
