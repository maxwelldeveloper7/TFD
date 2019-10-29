package tfd.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import tfd.controle.Controle;
import tfd.daos.CidadeDao;
import tfd.daos.EspecialidadeDao;
import tfd.daos.ProcedimentoDao;
import tfd.modelo.CidadeBean;
import tfd.modelo.EspecialidadeBean;
import tfd.modelo.ProcedimentoBean;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmProcedimentos extends JDialog implements ActionListener {

    //Declarando componentes
    private TitledBorder bordaTabela, bordaDados;
    private JPanel pnTabela, pnFundo, pnBotoes, pnDados;
    private JButton btInserir, btEditar, btSalvar, btCancelar, btExcluir, btEspecialidades;
    private JComboBox cbEspecialidade;
    private JTextField txId, txProcedimento;
    private JLabel lbId, lbProcedimento, lbEspecialidade;
    private final DefaultTableModel modelo;
    private ListSelectionModel lms;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private List<ProcedimentoBean> procedimentos;
    public Integer dml;
    List<EspecialidadeBean> lista = new ArrayList<>();

    //Método construtor
    public FrmProcedimentos(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Cadastro de Procedimentos");//define o título
        URL url = this.getClass().getResource("/tfd/visao/procedimentos.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da tela
        setBounds((screenSize.width - 764) / 2, (screenSize.height - 364) / 2, 764, 364);//define o tamanho da janela e posiciona ao centro
        setResizable(false);//impossibilita redimencionamento
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//define ação ao fechar janela.

        //impossibilita edição da tabela
        this.modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
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
        pnTabela.setBounds(4, 4, 750, 200);
        bordaTabela = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Listagem de Procedimentos");
        pnTabela.setBorder(bordaTabela);

        //construindo tabela
        tabela = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Procedimento");
        modelo.addColumn("Especialidade");
        //tabela.setAutoResizeMode(0);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(28);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(320);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(268);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.LEFT);
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleciona apenas uma linha
        lms = tabela.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    linhaSelecionadaTabela();
                }
            }
        });
        barraRolagem = new JScrollPane(tabela);
        barraRolagem.setBounds(5, 17, 740, 178);
        pnTabela.add(barraRolagem);

        //Painel que conterá o formulário de dados
        pnDados = new JPanel(null);
        pnDados.setBounds(4, 208, 750, 80);
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

        lbProcedimento = new JLabel("Procedimento:");
        lbProcedimento.setBounds(60, 20, 100, 20);
        pnDados.add(lbProcedimento);
        txProcedimento = new JTextField();
        txProcedimento.setBounds(60, 40, 360, 20);
        pnDados.add(txProcedimento);

        lbEspecialidade = new JLabel("Especialidade:");
        lbEspecialidade.setBounds(440, 20, 100, 20);
        pnDados.add(lbEspecialidade);

        cbEspecialidade = new JComboBox();
        cbEspecialidade.setBounds(440, 40, 270, 20);
        carregaCbEspecialidade();
        pnDados.add(cbEspecialidade);

        btEspecialidades = new JButton(new ImageIcon(getClass().getResource("/tfd/visao/especialidades.png")));
        btEspecialidades.setBounds(712, 40, 20, 20);
        btEspecialidades.setToolTipText("Cadastro de Especialidades");
        pnDados.add(btEspecialidades);

        //Construindo painel de botões
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(4, 292, 750, 38);

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
        btEspecialidades.addActionListener(this);
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                Controle.procedimentos = null;
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    //tratando eventos    
    private void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        ProcedimentoDao dao = new ProcedimentoDao();

        procedimentos = dao.listar();

        String[] campos = {null, null, null};

        for (int i = 0; i < procedimentos.size(); i++) {
            modelo.addRow(campos);
            modelo.setValueAt(procedimentos.get(i).getId() + "  ", i, 0);
            modelo.setValueAt("  " + procedimentos.get(i).getNomeProcedimento(), i, 1);
            modelo.setValueAt("  "+procedimentos.get(i).getEspecialidade().getNomeEspecialidade(), i, 2);
        }
    }

    private void linhaSelecionadaTabela() {
        if (tabela.getSelectedRow() != -1) {
            txId.setText(procedimentos.get(tabela.getSelectedRow()).getId().toString());
            txProcedimento.setText(procedimentos.get(tabela.getSelectedRow()).getNomeProcedimento());
            cbEspecialidade.setSelectedItem(procedimentos.get(tabela.getSelectedRow()).getEspecialidade().getNomeEspecialidade());
            habilitarEdicaoExclusao(true);
        } else {
            habilitarEdicaoExclusao(false);
            limparDados();
        }
    }

    private void limparDados() {
        txId.setText("");
        txProcedimento.setText("");
        cbEspecialidade.setSelectedIndex(0);
    }

    private void habilitarCampos(boolean habilitar) {
        txProcedimento.setEnabled(habilitar);
        cbEspecialidade.setEnabled(habilitar);
        btEspecialidades.setEnabled(habilitar);
        tabela.setEnabled(!habilitar);
        txProcedimento.requestFocus();
    }

    private void resetarFormulario() {
        limparDados();
        btInserir.setEnabled(true);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(false);
        habilitarCampos(false);
        procedimentos = null;
        dml = null;
        pesquisar(modelo);
        btInserir.requestFocus();
    }

    private void habilitarEdicaoExclusao(boolean habilitar) {
        btInserir.setEnabled(!habilitar);
        btEditar.setEnabled(habilitar);
        btExcluir.setEnabled(habilitar);
    }

    private void salvar() {
        System.out.println(validarFormulario());
        if (validarFormulario()) {

            EspecialidadeBean e = new EspecialidadeBean();
            
            for (EspecialidadeBean especialidade : lista) {
                if (especialidade.getNomeEspecialidade().equals(cbEspecialidade.getSelectedItem().toString())) {
                    e.setId(especialidade.getId());
                    e.setNomeEspecialidade(especialidade.getNomeEspecialidade());
                    break;
                }
            }
            
            if (dml == 1) {
                
                ProcedimentoBean p = new ProcedimentoBean(txProcedimento.getText(), e);
                if (new ProcedimentoDao().inserir(p)) {
                    JOptionPane.showMessageDialog(this, "Cadastro salvo com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (dml == 2) {
                ProcedimentoBean p = new ProcedimentoBean(Integer.parseInt(txId.getText()), txProcedimento.getText(), e);
                if (new ProcedimentoDao().alterar(p)) {
                    JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            resetarFormulario();
        }
    }

    private void excluir() {
        int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {
            if (new CidadeDao().excluir(Integer.parseInt(txId.getText().trim()))) {
                JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        resetarFormulario();
    }

    private void inserir() {
        limparDados(); //limpa o formlário para novos registros
        //habilita e desabilita botões necessários
        btInserir.setEnabled(false);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        habilitarCampos(true);//habilita campos para preenchimento
        dml = 1;//seta 1 para inserção quando do acionar o botão salvar
    }

    private void editar() {
        //habilita e desabilita botões necessários
        btInserir.setEnabled(false);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        habilitarCampos(true);//habilita campos para edição
        dml = 2;//seta 2 para alteração quando do acionar o botão salvar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btInserir) {
            inserir();
        }

        if (e.getSource() == btEditar) {
            editar();
        }

        if (e.getSource() == btExcluir) {
            excluir();
        }

        if (e.getSource() == btSalvar) {
            salvar();
        }

        if (e.getSource() == btCancelar) {
            resetarFormulario();
        }

        if (e.getSource() == btEspecialidades) {
            Controle.abreFrmEspecialidades(null, true);
        }
    }

    public void carregaCbEspecialidade() {
        lista.clear();

        lista = new EspecialidadeDao().listar();

        cbEspecialidade.removeAllItems();

        cbEspecialidade.addItem("Nenhum item selecionado");

        for (EspecialidadeBean especialidadeBean : lista) {
            cbEspecialidade.addItem(especialidadeBean.getNomeEspecialidade());
        }
    }

    private boolean validarFormulario() {
        if(txProcedimento.getText().trim().equals("")){
            txProcedimento.requestFocus();
            return false;
        }
        
        if(cbEspecialidade.getSelectedIndex() == 0){
            cbEspecialidade.requestFocus();
            return false;
        }
        
        return true;
    }

    
}
