package tfd.daos;

import java.sql.PreparedStatement;
import tfd.modelo.MotoristaBean;

/**
 *
 * @author Developer
 */
public class MotoristaDao extends GenericDAO{
    private PreparedStatement pstm = null;
    
    public boolean inserir(MotoristaBean m){
        String sql = "INSERT INTO motoristas(nomemotorista, telefonemoto, ativo) VALUES (?, ?, ?)";
        return executeCommand(sql, m.getNomeMotorista(), m.getTelefone(), m.isAtivo());
    }
}
