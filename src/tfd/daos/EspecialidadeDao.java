package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tfd.modelo.EspecialidadeBean;

/**
 *
 * @author Developer
 */
public class EspecialidadeDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(EspecialidadeBean e){
        String sql = "INSERT INTO especialidades(nomeespe) VALUES (?)";
        return executeCommand(sql, e.getNomeEspecialidade());
    }
    
    public boolean alterar(EspecialidadeBean e){
        String sql = "UPDATE especialidades SET nomeespe = ? WHERE id = ?";
        return executeCommand(sql, e.getNomeEspecialidade(), e.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM especialidades WHERE id = ?";
        return executeCommand(sql, id);
    }
    
    public ArrayList<EspecialidadeBean> listar(){
        ArrayList<EspecialidadeBean> especialidades = new ArrayList<>();
        String sql = "SELECT * FROM especialidades order by nomeespe asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                EspecialidadeBean e = new EspecialidadeBean();
                e.setId(rs.getInt("id"));
                e.setNomeEspecialidade(rs.getString("nomeespe"));
                especialidades.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar especialidades; "+e.getMessage());
        }
        return especialidades;
    }
}
