package expressoes_regulares;

/**
 *
 * @author Thairam Michel
 */
public class QuadrinhoER {

    public static final String ER_NOME = "^((\\b[a-zA-ZÀ-ú']{3,40}\\b)\\s*){1,}$";
    public static final String ER_VALOR = "\\d{1,3}(\\.\\d{1,3})*";
    public static final String ER_EDITORA = "^((\\b[a-zA-ZÀ-ú']{1,40}\\b)\\s*){1,}$";
    public static final String ER_ISBN = "\\d{10}|\\d{13}";
    public static final String ER_BOOLEAN = "^[sS]|[nN]";
    public static final String ER_EDICAO = "^((\\b[a-zA-ZÀ-ú0-9']{1,40}\\b)\\s*){1,}$";
    public static final String ER_GENERO = "^((\\b[a-zA-ZÀ-ú']{3,40}\\b)\\s*){1,}$";
    public static final String ER_CURIOSIDADE = "^((\\b[a-zA-ZÀ-ú']{3,40}\\b)\\s*){1,}$";
    public static final String ER_NOTA = "\\d{1,2}\\.\\d{1,2}";
}
