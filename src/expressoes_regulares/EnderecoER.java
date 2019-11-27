package expressoes_regulares;

/**
 *
 * @author Thairam Michel
 */
public class EnderecoER {

    public static final String ER_RUA = "^((\\b[a-zA-ZÀ-ú']{1,40}\\b)\\s*){1,}$";
    public static final String ER_BAIRRO = "^((\\b[a-zA-ZÀ-ú']{1,40}\\b)\\s*){1,}$";
    public static final String ER_CIDADE = "^((\\b[a-zA-ZÀ-ú']{1,40}\\b)\\s*){1,}$";
    public static final String ER_CEP = "\\d{8}|\\d{5}-\\d{3}|\\d{2}.\\d{3}-\\d{3}";
    public static final String ER_UF = "[a-zA-Z]{2,2}";
}
