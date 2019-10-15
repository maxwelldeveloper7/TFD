package tfd.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmMotoristas extends JDialog {

    //Declarando componentes
    private JMenuBar menu;
    private JMenu mnuCadastro, mnuRelatorios, mnuSuporte;
    private JMenuItem mnuContato;
    private TitledBorder bordaTabela, bordaDados;
    private JPanel pnTabela, pnFundo, pnBotoes, pnDados;
    private JButton btInserir, btAtualizar, btSalvar, btCancelar, btExcluir;
    private Icon icoAgendar, icoEditar, icoVisualizar, icoContato;
    private JTextField txId, txMotorista;
    private JFormattedTextField txTelefone;
    private JLabel lbId, lbMotorista, lbTelefone;
    private MaskFormatter ftmtelefone;
    private DefaultTableModel modelo = new DefaultTableModel();
    private JTable tabela;
    private JScrollPane barraRolagem;
    private Timestamp idAgendamento;

    //Método construtor
    public FrmMotoristas() {
        setTitle("Cadastro de Motoristas");//define o título
        URL url = this.getClass().getResource("/tfd/visao/favicon.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da tela
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 364) / 2, 800, 364);//define o tamanho da janela e posiciona ao centro
        setResizable(false);//impossibilita redimencionamento
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//define ação ao fechar janela.
        construirComponentes();
    }

    //Inicializando, definindo e posicionando componentes
    private void construirComponentes() {
        //painel que conterá a tabela
        pnTabela = new JPanel(null);
        pnTabela.setBounds(4, 4, 786, 200);
        bordaTabela = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Listagem de Motoristas");
        pnTabela.setBorder(bordaTabela);

        //construindo tabela
        tabela = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Motorista");
        modelo.addColumn("Telafone");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        pesquisar(modelo);
        barraRolagem = new JScrollPane(tabela);
        barraRolagem.setBounds(5, 17, 776, 178);
        pnTabela.add(barraRolagem);

        //Painel que conterá o formulário de dados
        pnDados = new JPanel(null);
        pnDados.setBounds(4, 208, 786, 80);
        bordaDados = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Dados");
        pnDados.setBorder(bordaDados);

        //Construindo formulário de dados
        lbId = new JLabel("Id:");
        lbId.setBounds(10, 20, 20, 20);//setBounds(coluna, linha, largura, altura)
        pnDados.add(lbId);
        txId = new JTextField();
        txId.setBounds(10, 40, 30, 20);
        txId.setEnabled(false);
        pnDados.add(txId);

        lbMotorista = new JLabel("Motorista:");
        lbMotorista.setBounds(60, 20, 100, 20);
        pnDados.add(lbMotorista);
        txMotorista = new JTextField();
        txMotorista.setBounds(60, 40, 300, 20);
        txMotorista.setEnabled(false);
        pnDados.add(txMotorista);

        lbTelefone = new JLabel("Telefone:");
        lbTelefone.setBounds(380, 20, 100, 20);
        pnDados.add(lbTelefone);

        try {
            ftmtelefone = new MaskFormatter("(##)#####-####");
            ftmtelefone.setValidCharacters("0123456789");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Falha ao formatar campo:\n" + e.getMessage(), "Falha do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        
        txTelefone = new JFormattedTextField(ftmtelefone);
        txTelefone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txTelefone.setColumns(11);
        txTelefone.setBounds(380, 40, 120, 20);
        txTelefone.setEnabled(false);
        pnDados.add(txTelefone);

        
        //Construindo painel de botões
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(4, 292, 786, 40);
        
        //Contruindo botões
        
        btInserir = new JButton("Inserir", new ImageIcon(getClass().getResource("/tfd/visao/inserir.png")));
        pnBotoes.add(btInserir);
        
        btAtualizar = new JButton("Editar", new ImageIcon(getClass().getResource("/tfd/visao/editar.png")));
        pnBotoes.add(btAtualizar);

        pnFundo = new JPanel(null);
        pnFundo.add(pnTabela);
        pnFundo.add(pnDados);
        pnFundo.add(pnBotoes);
        pnFundo.setBackground(Color.gray);
        getContentPane().add(pnFundo);
    }

    private void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        //DAO a implementar
        modelo.addRow(new Object[]{"1", "Robinho", "33-3621-4545"});
    }
}
