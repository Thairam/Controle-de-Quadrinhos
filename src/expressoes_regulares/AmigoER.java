package expressoes_regulares;

/**
 *
 * @author Thairam Michel
 */
public class AmigoER {

    public static final String ER_NOME = "^((\\b[a-zA-ZÀ-ú']{1,40}\\b)\\s*){1,}$";
    public static final String ER_CPF = "\\d{11}|\\d{3}.\\d{3}.\\d{3}-\\d{2}";
    public static final String ER_FONE = "\\d{12}|\\d{3}\\s\\d{5}-\\d{4}|\\d{3}\\s\\d{5}\\d{4}|\\d{3}\\s\\d{5}\\s\\d{4}";
    public static final String ER_EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
    public static final String ER_DATA_NASCIMENTO = "\\d{2}/\\d{2}/\\d{4}";
}
