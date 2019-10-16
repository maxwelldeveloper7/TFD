package tfd.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Maxwell
 */
public class ConexaoPostgres {

    private static Connection con = null;
    private static String url;// = "jdbc:postgresql://0.tcp.ngrok.io:10685/comunicacao"; //servidor 10.0.0.101
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASSWORD = "311208";
    private static ConexaoPostgres instanciaSingleton = null;

    private ConexaoPostgres() {
    }

    public static ConexaoPostgres getInstancia() {
        if (instanciaSingleton == null) {
            instanciaSingleton = new ConexaoPostgres();
        }
        return instanciaSingleton;
    }

    public static Connection conectar() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            prop.load(input);
        } catch (IOException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        url = "jdbc:postgresql://" + prop.getProperty("host") + ":" + prop.getProperty("porta") + "/tfd";
        
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(url, USER, PASSWORD);
            con.setAutoCommit(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Driver não encontrado.\n" + ex.getMessage());
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao estabelecer conexão com o Banco de Dados.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);

        }
        return con;
    }

    public static void desconectar() {
        try {
            if (con != null) {
                con.close();
            } else {
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao desconectar Banco de Dados.\n" + ex.getMessage());
            System.exit(0);
        }
    }

    public static void commit() {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao efetivar transação.\n" + ex.getMessage());
                Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void rollback() {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao desfazer transação.\n" + ex.getMessage());
                Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
