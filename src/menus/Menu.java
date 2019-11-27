package menus;

import java.util.ArrayList;
import java.util.HashMap;
import model.Amigo;
import model.Endereco;
import model.Utils;

/**
 *
 * @author Thairam Michel
 */
public class Menu {

    public static void menuPrincipal() {
        System.out.println("***** MENU PRINCIPAL *****");
        System.out.println("1: Amigos\n2: Quadrinhos\n3: Empréstimo\n4: Coleções\n5: Endereços\n0: finalizar");
    }

    public static void menuPrincipalAmigos() {
        System.out.println("****** MENU AMIGO ******");
        System.out.println("1: Adicionar amigo\n2: Atualizar amigo\n3: Listar amigos\n4: Buscar amigo por cpf\n0: Voltar");
    }

    public static void menuPrincipalEnderecos() {
        System.out.println("****** MENU ENDEREÇO ******");
        System.out.println("1: Adicionar endereço\n2: Atualizar endereço\n3: Listar endereços\n0: Voltar");
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
            System.out.println(msg2);
        }
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
            System.out.println("");
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

}
