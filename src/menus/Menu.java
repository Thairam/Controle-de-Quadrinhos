package menus;

import java.util.ArrayList;
import model.Amigo;
import model.Utils;

/**
 *
 * @author Thairam Michel
 */
public class Menu {

    public static void menuPrincipal() {
        System.out.println("==============================");
        System.out.println("Escolha uma opção válida: ");
        System.out.println("1: Amigos\n2: Quadrinhos\n3: Empréstimo\n4: Coleções\n0: finalizar");
        System.out.println("==============================");
    }

    public static void menuPrincipalAmigos() {
        System.out.println("==============================");
        System.out.println("1: adicionar amigo\n2: listar amigos\n3: buscar amigo por cpf\n0: voltar");
        System.out.println("==============================");
    }

    public static void apresentarAmigos(ArrayList<Amigo> lista) {
        System.out.println("==========================================================================================");
        for (Amigo amigo : lista) {
            System.out.println("Nome: " + amigo.getNome() + " || Cpf: " + amigo.getCpf()
                    + " || Data de Nascimento: " + Utils.calendToString(amigo.getDataNascimento())
                    + " || Email: " + amigo.getEmail());
        }
        System.out.println("==========================================================================================");
    }

    public static void apresentarAmigo() {

    }

}
