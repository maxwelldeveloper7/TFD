package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tfd.modelo.EspecialidadeBean;
import tfd.modelo.ProcedimentoBean;

/**
 *
 * @author Developer
 */
public class ProcedimentoDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(ProcedimentoBean p){
        String sql = "INSERT INTO procedimentos(nomeproc, cdespeci) VALUES (?, ?)";
        return executeCommand(sql, p.getNomeProcedimento(), p.getEspecialidade().getId());
    }
    
    public boolean alterar(ProcedimentoBean p){
        String sql = "UPDATE procedimentos SET nomeproc = ?, cdespeci = ? WHERE id = ?";
        return executeCommand(sql,p.getNomeProcedimento(), p.getEspecialidade().getId(), p.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM procedimentos WHERE id = ?";
        return executeCommand(sql, id);
    }
    
    public ArrayList<ProcedimentoBean> listar(){
        ArrayList<ProcedimentoBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM vpproc order by nomeproc asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                ProcedimentoBean p = new ProcedimentoBean();
                p.setId(rs.getInt("id"));
                p.setNomeProcedimento(rs.getString("nomeproc"));
                EspecialidadeBean e = new EspecialidadeBean();
                e.setId(rs.getInt("cdespeci"));
                e.setNomeEspecialidade(rs.getString("nomeespe"));
                p.setEspecialidade(e);
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar procedimentos; "+e.getMessage());
        }
        return lista;
    }
}
