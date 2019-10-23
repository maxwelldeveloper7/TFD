package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tfd.modelo.CidadeBean;

/**
 *
 * @author Developer
 */
public class CidadeDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(CidadeBean c){
        String sql = "INSERT INTO cidades(nomecidade, uf) VALUES (?, ?)";
        return executeCommand(sql, c.getNomeCidade(), c.getUf());
    }
    
    public boolean alterar(CidadeBean c){
        String sql = "UPDATE cidades SET nomecidade = ?, uf = ? WHERE id = ?";
        return executeCommand(sql, c.getNomeCidade(), c.getUf(), c.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM cidades WHERE id = ?";
        return executeCommand(sql, id);
    }
    
    public ArrayList<CidadeBean> listar(){
        ArrayList<CidadeBean> cidades = new ArrayList<>();
        String sql = "SELECT * FROM cidades order by nomecidade asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                CidadeBean c = new CidadeBean();
                c.setId(rs.getInt("id"));
                c.setNomeCidade(rs.getString("nomecidade"));
                c.setUf(rs.getString("uf"));
                cidades.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar cidades; "+e.getMessage());
        }
        return cidades;
    }
}
