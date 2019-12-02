package unidade;

import static junit.framework.Assert.assertEquals;
import model.Endereco;
import org.junit.Test;

/**
 *
 * @author Thairam Michel
 */
public class testeModelEndereco {

    Endereco enderecoPadrao = new Endereco();
    String rua = "Rua dos bobos";
    String bairro = "Bairro dos bobos";
    String cidade = "Cidade dos bobos";
    String uf = "PB";
    String cep = "5577712";

    public void configuracao() {
        enderecoPadrao.setId(1);
        enderecoPadrao.setCep(cep);
        enderecoPadrao.setBairro(bairro);
        enderecoPadrao.setCidade(cidade);
        enderecoPadrao.setRua(rua);
        enderecoPadrao.setUf(uf);
    }

    @Test
    public void testaConstrutor() {
        Endereco endereco = new Endereco(cep, rua, bairro, cidade, uf);
        assertEquals(endereco.getValues().equals(""), false);
    }

    @Test
    public void testaMetodoGetFields() {
        String campos = enderecoPadrao.getFields();
        assertEquals(campos, "cep, rua, bairro, cidade, uf");
    }

    @Test
    public void testaMetodoGetValues() {
        configuracao();
        String valores = enderecoPadrao.getValues();
        assertEquals(valores.contains("1"), true);
        assertEquals(valores.contains(cep), true);
        assertEquals(valores.contains(rua), true);
        assertEquals(valores.contains(bairro), true);
        assertEquals(valores.contains(cidade), true);
        assertEquals(valores.contains(uf), true);
    }

    @Test
    public void testaMetodosGetKey() {
        configuracao();
        assertEquals(enderecoPadrao.getKeyField(), "id");
        assertEquals(enderecoPadrao.getKeyValue(), 1);
    }

}
