package tfd.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Timestamp;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import tfd.controle.Controle;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmMotoristas extends JDialog{
    
    //Declarando componentes
    private JMenuBar menu;
    private JMenu mnuCadastro, mnuRelatorios, mnuSuporte;
    private JMenuItem mnuContato;
    private TitledBorder bordaTitulo;
    private JPanel pnAgenda, pnFundo, pnBotoes, pnDados, pnSetorStatus, pnCargoStatus, pnPerfilStatus;
    private JButton btEditar, btAgendar, btVisualizar;
    private Icon icoAgendar, icoEditar, icoVisualizar, icoContato;
    private JLabel lbServidor, lbSetor, lbCargo, lbPerfil;
    private DefaultTableModel modelo = new DefaultTableModel();
    private JTable tabela;
    private JScrollPane barraRolagem;
    private Timestamp idAgendamento;
    
    //Método construtor
    public FrmMotoristas(){
        setTitle("Cadastro de Motoristas");//define o título
        URL url = this.getClass().getResource("/tfd/visao/favicon.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da tela
        setBounds((screenSize.width - 800)/2, (screenSize.height - 600)/2, 800, 600);//define o tamanho da janela e posiciona ao centro
        setResizable(false);//impossibilita redimencionamento
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//define ação ao fechar janela.
        construirComponentes();
    }

    //Inicializando, definindo e posicionando componentes
    private void construirComponentes() {
      
        
    }
}
