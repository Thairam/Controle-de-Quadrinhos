package DAO;

import classes.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Thairam Michel
 * @param <T>
 */
public class EmprestimoDao<T> extends Dao<T> {
    
    public EmprestimoDao() {
        super("emprestimo");
    }
    
    @Override
    protected T getObject(ResultSet resultSet) throws SQLException {
        Amigo amigo = null;
        
        if (resultSet.getInt("id_amigo") != 0) {
            amigo = (new AmigoDao<Amigo>()).find(resultSet.getInt("id_amigo"));
        }
        
        Calendar emprestimo, devolucao = null;
        
        Date dataEmprestimo = resultSet.getDate("data_emprestimo");
        Date dataDevolucao = resultSet.getDate("data_devolucao");
        
        emprestimo = Calendar.getInstance();
        emprestimo.setTime(dataEmprestimo);
        
        devolucao = Calendar.getInstance();
        devolucao.setTime(dataDevolucao);

        //add para corrigir erro de atraso em um dia na data
        emprestimo.add(Calendar.DAY_OF_MONTH, 1);        
        devolucao.add(Calendar.DAY_OF_MONTH, 1);        
        
        return ((T) new Emprestimo(resultSet.getInt("id"),
                amigo,
                emprestimo,
                devolucao,
                resultSet.getString("estado")
        ));
    }
}
