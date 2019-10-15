package tfd.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import tfd.Utilidades.Utilidades;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmMotoristas extends JDialog implements ActionListener{

    //Declarando componentes
    private TitledBorder bordaTabela, bordaDados;
    private JPanel pnTabela, pnFundo, pnBotoes, pnDados;
    private JButton btInserir, btEditar, btSalvar, btCancelar, btExcluir;
    private JComboBox cbStatus;
    private JTextField txId, txMotorista;
    private JFormattedTextField txTelefone;
    private JLabel lbId, lbMotorista, lbTelefone, lbStatus;
    private MaskFormatter ftmtelefone;
    private DefaultTableModel modelo;
    private ListSelectionModel lms;
    private int linhaSelecionada = -1;
    private JTable tabela;
    private JScrollPane barraRolagem;
    

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
        //impossibilita edição da tabela
        this.modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        construirComponentes();
        habilitarCampos(false);
        resetarFormulario();
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
        modelo.addColumn("Status");        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);  
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleciona apenas uma linha
        lms = tabela.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    linhaSelecionadaTabela();
                }
            }
        });
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
        pnDados.add(txTelefone);

        lbStatus = new JLabel("Status:");
        lbStatus.setBounds(520, 20, 120, 20);
        pnDados.add(lbStatus);
        
        cbStatus = new JComboBox();
        cbStatus.addItem("");
        cbStatus.addItem("Ativo");
        cbStatus.addItem("Inativo");
        cbStatus.setBounds(520, 40, 120, 20);
        pnDados.add(cbStatus);
        
        //Construindo painel de botões
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(4, 292, 786, 38);
        
        //Contruindo botões
        
        btInserir = new JButton("Inserir", new ImageIcon(getClass().getResource("/tfd/visao/inserir.png")));
        pnBotoes.add(btInserir);
        
        btEditar = new JButton("Editar", new ImageIcon(getClass().getResource("/tfd/visao/editar.png")));
        pnBotoes.add(btEditar);
        
        btExcluir = new JButton("Excluir", new ImageIcon(getClass().getResource("/tfd/visao/deletar.png")));
        pnBotoes.add(btExcluir);
        
        btSalvar = new JButton("Salvar", new ImageIcon(getClass().getResource("/tfd/visao/salvar.png")));
        pnBotoes.add(btSalvar);
        
        btCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/tfd/visao/cancelar.png")));
        pnBotoes.add(btCancelar);
                
        pnBotoes.setBackground(Color.gray);

        pnFundo = new JPanel(null);
        pnFundo.add(pnTabela);
        pnFundo.add(pnDados);
        pnFundo.add(pnBotoes);
        pnFundo.setBackground(Color.gray);
        getContentPane().add(pnFundo);
        
        //registrando eventos
        btInserir.addActionListener(this);
        btEditar.addActionListener(this);
        btExcluir.addActionListener(this);
        btSalvar.addActionListener(this);
        btCancelar.addActionListener(this);
    }

    //tratando eventos
    
    private void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        //DAO a implementar
        //modelo.addRow(new Object[]{"1", "Robinho", Utilidades.formataStringMascara("33988117010", "(##)#####-####"),"Ativo"});
        //modelo.addRow(new Object[]{"1", "Robinho", Utilidades.formataStringMascara("33988117010", "(##)#####-####"),"Ativo"});
        
        String[] campos = {null, null, null, null};
    }
    
    private void linhaSelecionadaTabela(){
        linhaSelecionada = tabela.getSelectedRow();
        if(tabela.getSelectedRow() != -1){
            
            habilitarEdicaoExclusao(true);
        }else{
            habilitarEdicaoExclusao(false);
        }
    }
    
    private void limparDados(){
        txId.setText("");
        txMotorista.setText("");
        txTelefone.setText("");
        cbStatus.setSelectedIndex(0);
    }
    
    private void habilitarCampos(boolean habilitar){
        txMotorista.setEnabled(habilitar);
        txTelefone.setEnabled(habilitar);
        cbStatus.setEnabled(habilitar); 
        tabela.setEnabled(!habilitar);
    }
    
    private void resetarFormulario(){
        limparDados();
        btInserir.setEnabled(true);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(false);
        habilitarCampos(false);
        pesquisar(modelo);
    }
    
    private void habilitarEdicaoExclusao(boolean habilitar){
        btInserir.setEnabled(!habilitar);
        btEditar.setEnabled(habilitar);
        btExcluir.setEnabled(habilitar);
    }
    
    private void salvar(){
        //implementar salvar
        resetarFormulario();
    }
    
    private void excluir(){
        //implementar excluir
        resetarFormulario();
    }
    
    private void inserir(){
        limparDados();
        btInserir.setEnabled(false);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        habilitarCampos(true);
    }
    
    private void editar(){
        btInserir.setEnabled(false);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        habilitarCampos(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btInserir){
            inserir();
        }
        
        if(e.getSource() == btEditar){
            editar();
        }
        
        if(e.getSource() == btExcluir){
            excluir();
        }
        
        if(e.getSource() == btSalvar){
            salvar();
        }
        
        if(e.getSource() == btCancelar){
            resetarFormulario();
        }
    }
    
    
}
