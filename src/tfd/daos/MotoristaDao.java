package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tfd.modelo.MotoristaBean;

/**
 *
 * @author Developer
 */
public class MotoristaDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(MotoristaBean m){
        String sql = "INSERT INTO motoristas(nomemotorista, telefonemoto, ativo) VALUES (?, ?, ?)";
        return executeCommand(sql, m.getNomeMotorista(), m.getTelefone(), m.isAtivo());
    }
    
    public boolean alterar(MotoristaBean m){
        String sql = "UPDATE motoristas SET nomemotorista = ?, telefonemoto = ?, ativo = ? WHERE id = ?";
        return executeCommand(sql, m.getNomeMotorista(), m.getTelefone(), m.isAtivo(), m.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM motoristas WHERE id = ?";
        return executeCommand(sql, id);
    }
    
    public ArrayList<MotoristaBean> listar(){
        ArrayList<MotoristaBean> motoristas = new ArrayList<>();
        String sql = "SELECT * FROM motoristas order by nomemotorista asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                MotoristaBean m = new MotoristaBean();
                m.setId(rs.getInt("id"));
                m.setNomeMotorista(rs.getString("nomemotorista"));
                m.setTelefone(rs.getString("telefonemoto"));
                m.setAtivo(rs.getBoolean("ativo"));
                motoristas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar motoristas; "+e.getMessage());
        }
        return motoristas;
    }
}
