package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tfd.modelo.EstabelecimentoBean;

/**
 *
 * @author Developer
 */
public class EstabelecimentoDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(EstabelecimentoBean e){
        String sql = "INSERT INTO estabelecimentos(nomeestabe) VALUES (?)";
        return executeCommand(sql, e.getNomeEstabelecimento());
    }
    
    public boolean alterar(EstabelecimentoBean e){
        String sql = "UPDATE estabelecimentos SET nomeestabe = ? WHERE id = ?";
        return executeCommand(sql, e.getNomeEstabelecimento(), e.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM estabelecimentos WHERE id = ?";
        return executeCommand(sql, id);
    }
    
    public ArrayList<EstabelecimentoBean> listar(){
        ArrayList<EstabelecimentoBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM estabelecimentos order by nomeestabe asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                EstabelecimentoBean e = new EstabelecimentoBean();
                e.setId(rs.getInt("id"));
                e.setNomeEstabelecimento(rs.getString("nomeestabe"));
                lista.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar estabelecimentos; "+e.getMessage());
        }
        return lista;
    }
}
