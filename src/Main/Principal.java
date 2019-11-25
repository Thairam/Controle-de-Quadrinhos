package Main;

import java.util.Scanner;
import model.*;
import controller.AmigoController;
import java.util.ArrayList;
import java.util.HashMap;
import menus.Menu;

/**
 * @author Thairam Michel
 */
public class Principal {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int opcao = 0;
            
            do {
                Menu.menuPrincipal();
                try {
                    opcao = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                }
            } while (opcao < 0 || opcao > 4);
            
            switch (opcao) {
                case 0:
                    System.exit(0);
                case 1:
                    AmigoController amigoControl = new AmigoController();
                    HashMap<Boolean, Object> ans = new HashMap<Boolean, Object>();
                    while (opcao != 0) {
                        do {
                            Menu.menuPrincipalAmigos();
                            try {
                                opcao = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Digite uma opção válida!");
                            }
                            
                            switch (opcao) {
                                case 0:
                                    break;
                                case 1:
                                    System.out.println(" *** Insira os dados do amigo ***");
                                    
                                    System.out.println("Nome: ");
                                    String nome = sc.nextLine();
                                    
                                    System.out.println("Data de nascimento(00/00/0000): ");
                                    String data = sc.nextLine();
                                    
                                    System.out.println("Cpf(111.111.111-00): ");
                                    String cpf = sc.nextLine();
                                    
                                    System.out.println("fone(083 99999-8888): ");
                                    String fone = sc.nextLine();
                                    
                                    System.out.println("Email: ");
                                    String email = sc.nextLine();
                                    
                                    Endereco endereco = null;
                                    ans = amigoControl.salvarAluno(endereco, nome, data, cpf, fone, email);
                                    
                                    System.out.println(ans.containsKey(true) ? ans.get(true) : ans.get(false));
                                    break;
                                case 2:
                                    ArrayList<Amigo> lista = amigoControl.listarTodosOsAmigos();
                                    if (!lista.isEmpty()) {
                                        Menu.apresentarAmigos(lista);
                                    } else {
                                        System.out.println("Não há nenhum amigo registrado!");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Informe o cpf do amigo: ");
                                    cpf = sc.nextLine();
                                    ans = amigoControl.buscarAmigoPorCpf(cpf);
                                    if (ans.containsKey(true)) {
                                        Amigo amigo = (Amigo) ans.get(true);
                                        System.out.println(amigo.getValues());
                                    } else {
                                        System.out.println(ans.get(false));
                                    }
                                    break;
                            }
                            
                        } while (opcao < 0 || opcao > 3);
                    }
            }
        }
    }
    
}
