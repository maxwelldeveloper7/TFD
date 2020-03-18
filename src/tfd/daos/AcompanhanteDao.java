package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tfd.modelo.AcompanhanteBean;

/**
 *
 * @author Developer
 */
public class AcompanhanteDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(AcompanhanteBean a){
        String sql = "INSERT INTO acompanhantes(nomeacomp, rgacomp, cpfacomp, enderecoacomp) VALUES (?, ?, ?, ?)";
        return executeCommand(sql, a.getNome(), a.getRg(), a.getCpf(), a.getEndereco());
    }
    
    public boolean alterar(AcompanhanteBean a){
        String sql = "UPDATE acompanhantes SET nomeacomp = ?, rgacomp = ?, cpfacomp = ?, enderecoacomp = ? WHERE id = ?";
        return executeCommand(sql,a.getNome(), a.getRg(), a.getCpf(), a.getEndereco(), a.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM acompanhantes WHERE id = ?";
        return executeCommand(sql, id);
    }
    
        
    public ArrayList<AcompanhanteBean> listar(){
        ArrayList<AcompanhanteBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM acompanhantes order by nomeacomp asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                AcompanhanteBean a = new AcompanhanteBean();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nomeacomp"));
                a.setRg(rs.getString("rgacomp"));
                a.setCpf(rs.getString("cpfacomp"));
                a.setEndereco(rs.getString("enderecoacomp"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar acompanhantes; "+e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<AcompanhanteBean> listar(String nome){
        ArrayList<AcompanhanteBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM acompanhantes WHERE nomeacomp LIKE ? order by nomeacomp asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1,"%"+ nome +"%");
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                AcompanhanteBean a = new AcompanhanteBean();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nomeacomp"));
                a.setRg(rs.getString("rgacomp"));
                a.setCpf(rs.getString("cpfacomp"));
                a.setEndereco(rs.getString("enderecoacomp"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar acompanhantes; "+e.getMessage());
        }
        return lista;
    }
    
    public AcompanhanteBean pesquisarCpf(String cpf){
        String sql = "SELECT * FROM acompanhantes WHERE cpfacomp = ?";
        AcompanhanteBean a = new AcompanhanteBean();
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1,cpf);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nomeacomp"));
                a.setRg(rs.getString("rgacomp"));
                a.setCpf(rs.getString("cpfacomp"));
                a.setEndereco(rs.getString("enderecoacomp"));
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar acompanhantes; "+e.getMessage());
        }        
        return a;
    }
}
