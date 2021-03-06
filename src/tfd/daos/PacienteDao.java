package tfd.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tfd.modelo.PacienteBean;

/**
 *
 * @author Maxwell
 */
public class PacienteDao extends GenericDAO{
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public boolean inserir(PacienteBean p){
        String sql = "INSERT INTO pacientes(nomepa, cns, rgpa, cpfpa, dtnascimento, paipa, maepa, telefonepa, enderecopa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeCommand(sql, p.getNome(), p.getCns(), p.getRg(), p.getCpf(), p.getNascimento(), p.getNomePai(), p.getNomeMae(), p.getTelefone(), p.getEndereco());
    }
    
    public boolean alterar(PacienteBean p){
        String sql = "UPDATE pacientes SET nomepa = ?, cns = ?, rgpa = ?, cpfpa = ?, dtnascimento = ?, paipa = ?, maepa = ?, telefonepa = ?, enderecopa = ? WHERE id = ?";
        return executeCommand(sql, p.getNome(), p.getCns(), p.getRg(), p.getCpf(), p.getNascimento(), p.getNomePai(), p.getNomeMae(), p.getTelefone(), p.getEndereco(), p.getId());
    }
    
    public boolean excluir(Integer id){
        String sql = "DELETE FROM pacientes WHERE id = ?";
        return executeCommand(sql, id);
    }
    
    public ArrayList<PacienteBean> listar(){
        ArrayList<PacienteBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes order by nomepa asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                PacienteBean p = new PacienteBean();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nomepa"));
                p.setCns(rs.getString("cns"));
                p.setRg(rs.getString("rgpa"));
                p.setCpf(rs.getString("cpfpa"));
                p.setNascimento(rs.getDate("dtnascimento"));
                p.setNomePai(rs.getString("paipa"));
                p.setNomeMae(rs.getString("maepa"));
                p.setTelefone(rs.getString("telefonepa"));
                p.setEndereco(rs.getString("enderecopa"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar pacientes; "+e.getMessage());
        }
        return lista;
    }
    
    
    public ArrayList<PacienteBean> listar(String nome){
        ArrayList<PacienteBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE nomepa LIKE ? order by nomepa asc";
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1,"%"+ nome +"%");
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                PacienteBean p = new PacienteBean();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nomepa"));
                p.setCns(rs.getString("cns"));
                p.setRg(rs.getString("rgpa"));
                p.setCpf(rs.getString("cpfpa"));
                p.setNascimento(rs.getDate("dtnascimento"));
                p.setNomePai(rs.getString("paipa"));
                p.setNomeMae(rs.getString("maepa"));
                p.setTelefone(rs.getString("telefonepa"));
                p.setEndereco(rs.getString("enderecopa"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar pacientes; "+e.getMessage());
        }
        return lista;
    }

    public PacienteBean pesquisarCpf(String cpf){
        String sql = "SELECT * FROM pacientes WHERE cpfpa = ?";
        PacienteBean a = new PacienteBean();
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1,cpf);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nomepa"));
                a.setCns(rs.getString("cns"));
                a.setRg(rs.getString("rgpa"));
                a.setCpf(rs.getString("cpfpa"));
                a.setNascimento(rs.getDate("dtnascimento"));
                a.setNomePai(rs.getString("paipa"));
                a.setNomeMae(rs.getString("maepa"));
                a.setTelefone(rs.getString("telefonepa"));
                a.setEndereco(rs.getString("enderecopa"));
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar pacientes; "+e.getMessage());
        }        
        return a;
    }
    
    public PacienteBean pesquisarCns(String cns){
        String sql = "SELECT * FROM pacientes WHERE cns = ?";
        PacienteBean a = new PacienteBean();
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1,cns);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nomepa"));
                a.setCns(rs.getString("cns"));
                a.setRg(rs.getString("rgpa"));
                a.setCpf(rs.getString("cpfpa"));
                a.setNascimento(rs.getDate("dtnascimento"));
                a.setNomePai(rs.getString("paipa"));
                a.setNomeMae(rs.getString("maepa"));
                a.setTelefone(rs.getString("telefonepa"));
                a.setEndereco(rs.getString("enderecopa"));
            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar pacientes; "+e.getMessage());
        }        
        return a;
    }
}
