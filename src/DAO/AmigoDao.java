package DAO;

import classes.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Thairam Michel
 */
public class AmigoDao<T> extends Dao<T> {

    public AmigoDao() {
        super("amigo");
    }

    @Override
    protected T getObject(ResultSet resultSet) throws SQLException {
        Endereco endereco = null;

        if (resultSet.getInt("id_endereco") != 0) {
            EnderecoDao dao = new EnderecoDao();
            endereco = (Endereco) dao.find(resultSet.getInt("id_endereco"));
        }

        Calendar nascimento = null;
        
        Date dataNascimento = resultSet.getDate("data_nascimento");
        
        nascimento = Calendar.getInstance();
        nascimento.setTime(dataNascimento);
        nascimento.add(Calendar.DAY_OF_MONTH, 1); //add para corrigir erro de atraso em um dia na data
        
        return ((T) new Amigo(resultSet.getInt("id"),
                endereco,
                resultSet.getString("nome"),
                nascimento,
                resultSet.getString("cpf"),
                resultSet.getString("fone"),
                resultSet.getString("email")
        ));
    }

}
