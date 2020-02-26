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
import java.text.ParseException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;
import tfd.Utilidades.Utilidades;
import tfd.controle.Controle;
import tfd.daos.PacienteDao;
import tfd.modelo.PacienteBean;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmPacientes extends JDialog implements ActionListener {

    //Declarando componentes
    private TitledBorder bordaTabela, bordaDados;
    private JPanel pnTabela, pnFundo, pnBotoes, pnDados;
    private JButton btMostrarTodos, btPesquisar, btInserir, btEditar, btSalvar, btCancelar, btExcluir;
    private JTextField txId, txPaciente, txRg, txEndereco, txMae, txPai;
    private JLabel lbId, lbPaciente, lbRg, lbCpf, lbEndereco, lbCartaoSus, lbDtNascimento, lbTelefone, lbMae, lbPai;
    private JFormattedTextField txCpf, txCartaoSus, txDtNascimento, txTelefone;
    private final DefaultTableModel modelo;
    private ListSelectionModel lms;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private List<PacienteBean> pacientes;
    public Integer dml;
    private MaskFormatter ftmCpf, ftmCartaoSus, ftmData, ftmTelefone;
    private String nomeParaPesquisar;

    //Método construtor
    public FrmPacientes(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Cadastro de Pacientes");//define o título
        URL url = this.getClass().getResource("/tfd/visao/clientes.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da tela
        setBounds((screenSize.width - 544) / 2, (screenSize.height - 600) / 2, 544, 600);//define o tamanho da janela e posiciona ao centro
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
    }

    //Inicializando, definindo e posicionando componentes
    private void construirComponentes() {
        //Construindo botões de Mostra tudo e Pesquisar

        btMostrarTodos = new JButton("Mostrar Todos", new ImageIcon(getClass().getResource("/tfd/visao/mostrartodos.png")));
        btMostrarTodos.setBounds(5, 6, 145, 30);

        btPesquisar = new JButton("Pesquisar", new ImageIcon(getClass().getResource("/tfd/visao/pesquisar.png")));
        btPesquisar.setBounds(155, 6, 145, 30);

        //painel que conterá a tabela
        pnTabela = new JPanel(null);
        pnTabela.setBounds(4, 43, 530, 250);
        bordaTabela = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Listagem de Pacientes");
        pnTabela.setBorder(bordaTabela);

        //construindo tabela
        tabela = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Cartão SUS");
        modelo.addColumn("RG");
        modelo.addColumn("CPF");
        tabela.setAutoResizeMode(0);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
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
        barraRolagem.setBounds(5, 17, 520, 228);
        pnTabela.add(barraRolagem);

        //Painel que conterá o formulário de dados
        pnDados = new JPanel(null);
        pnDados.setBounds(4, 298, 530, 230);
        bordaDados = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Dados");
        pnDados.setBorder(bordaDados);

        //Construindo formulário de dados
        lbId = new JLabel("Id:");
        lbId.setBounds(10, 20, 20, 20);//setBounds(coluna, linha, largura, altura)
        pnDados.add(lbId);
        txId = new JTextField();
        txId.setBounds(10, 40, 50, 20);
        txId.setEnabled(false);
        pnDados.add(txId);

        lbPaciente = new JLabel("Nome:");
        lbPaciente.setBounds(80, 20, 100, 20);
        pnDados.add(lbPaciente);
        txPaciente = new JTextField();
        txPaciente.setBounds(80, 40, 245, 20);
        pnDados.add(txPaciente);

        lbCartaoSus = new JLabel("Cartão SUS:");
        lbCartaoSus.setBounds(345, 20, 100, 20);
        pnDados.add(lbCartaoSus);

        try {
            ftmCartaoSus = new MaskFormatter("  ###  ####  ####  ####");
            ftmCartaoSus.setValidCharacters("0123456789");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Falha ao formatar campo:\n" + e.getMessage(), "Falha do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        txCartaoSus = new JFormattedTextField(ftmCartaoSus);
        txCartaoSus.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txCartaoSus.setColumns(15);
        txCartaoSus.setBounds(345, 40, 140, 20);
        pnDados.add(txCartaoSus);

        lbMae = new JLabel("Mãe:");
        lbMae.setBounds(10, 70, 120, 20);
        pnDados.add(lbMae);
        txMae = new JTextField();
        txMae.setBounds(10, 90, 245, 20);
        pnDados.add(txMae);

        lbPai = new JLabel("Pai:");
        lbPai.setBounds(275, 70, 120, 20);
        pnDados.add(lbPai);
        txPai = new JTextField();
        txPai.setBounds(275, 90, 245, 20);
        pnDados.add(txPai);

        lbRg = new JLabel("RG:");
        lbRg.setBounds(10, 120, 20, 20);
        pnDados.add(lbRg);
        txRg = new JTextField();
        txRg.setBounds(10, 140, 100, 20);
        pnDados.add(txRg);

        lbCpf = new JLabel("CPF:");
        lbCpf.setBounds(130, 120, 100, 20);
        pnDados.add(lbCpf);

        try {
            ftmCpf = new MaskFormatter("###.###.###-##");
            ftmCpf.setValidCharacters("0123456789");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Falha ao formatar campo:\n" + e.getMessage(), "Falha do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        txCpf = new JFormattedTextField(ftmCpf);
        txCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txCpf.setColumns(11);
        txCpf.setBounds(130, 140, 100, 20);
        pnDados.add(txCpf);

        lbDtNascimento = new JLabel("Nascimento:");
        lbDtNascimento.setBounds(250, 120, 200, 20);
        pnDados.add(lbDtNascimento);

        try {
            ftmData = new MaskFormatter("##/##/####");
            ftmData.setValidCharacters("0123456789");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Falha ao formatar campo:\n" + e.getMessage(), "Falha do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        txDtNascimento = new JFormattedTextField(ftmData);
        txDtNascimento.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txDtNascimento.setColumns(8);
        txDtNascimento.setBounds(250, 140, 80, 20);
        pnDados.add(txDtNascimento);

        lbTelefone = new JLabel("Celular:");
        lbTelefone.setBounds(350, 120, 100, 20);
        pnDados.add(lbTelefone);

        try {
            ftmTelefone = new MaskFormatter("(##)#####-####");
            ftmTelefone.setValidCharacters("0123456789");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Falha ao formatar campo:\n" + e.getMessage(), "Falha do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        txTelefone = new JFormattedTextField(ftmTelefone);
        txTelefone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txTelefone.setColumns(11);
        txTelefone.setBounds(350, 140, 100, 20);
        pnDados.add(txTelefone);

        lbEndereco = new JLabel("Endereço:");
        lbEndereco.setBounds(10, 170, 100, 20);
        pnDados.add(lbEndereco);

        txEndereco = new JTextField();
        txEndereco.setBounds(10, 190, 510, 20);
        pnDados.add(txEndereco);

        //Construindo painel de botões
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(4, 530, 530, 38);

        //Contruindo botões
        btInserir = new JButton("Inserir", new ImageIcon(getClass().getResource("/tfd/visao/inserir.png")));
        btInserir.setEnabled(true);
        pnBotoes.add(btInserir);

        btEditar = new JButton("Editar", new ImageIcon(getClass().getResource("/tfd/visao/editar.png")));
        btEditar.setEnabled(false);
        pnBotoes.add(btEditar);

        btExcluir = new JButton("Excluir", new ImageIcon(getClass().getResource("/tfd/visao/deletar.png")));
        btExcluir.setEnabled(false);
        pnBotoes.add(btExcluir);

        btSalvar = new JButton("Salvar", new ImageIcon(getClass().getResource("/tfd/visao/salvar.png")));
        btSalvar.setEnabled(false);
        pnBotoes.add(btSalvar);

        btCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/tfd/visao/cancelar.png")));
        btCancelar.setEnabled(false);
        pnBotoes.add(btCancelar);

        pnBotoes.setBackground(Color.white);

        pnFundo = new JPanel(null);
        pnFundo.add(btMostrarTodos);
        pnFundo.add(btPesquisar);
        pnFundo.add(pnTabela);
        pnFundo.add(pnDados);
        pnFundo.add(pnBotoes);
        pnFundo.setBackground(Color.white);
        getContentPane().add(pnFundo);

        //registrando eventos
        btMostrarTodos.addActionListener(this);
        btPesquisar.addActionListener(this);
        btInserir.addActionListener(this);
        btEditar.addActionListener(this);
        btExcluir.addActionListener(this);
        btSalvar.addActionListener(this);
        btCancelar.addActionListener(this);
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                Controle.especialidades = null;

                if (Controle.procedimentos != null) {
                    Controle.procedimentos.carregaCbEspecialidade();
                    Controle.procedimentos.cbEspecialidade.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                resetarFormulario();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    //tratando eventos    
    private void listar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        PacienteDao dao = new PacienteDao();

        pacientes = dao.listar();

        String[] campos = {null, null, null, null, null};

        for (int i = 0; i < pacientes.size(); i++) {
            modelo.addRow(campos);
            modelo.setValueAt(pacientes.get(i).getId() + "  ", i, 0);
            modelo.setValueAt("  " + pacientes.get(i).getNome(), i, 1);
            modelo.setValueAt("" + pacientes.get(i).getCns(), i, 2);
            modelo.setValueAt("" + pacientes.get(i).getRg(), i, 3);
            modelo.setValueAt("" + pacientes.get(i).getCpf(), i, 4);
        }
    }

    private void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        PacienteDao dao = new PacienteDao();

        pacientes = dao.listar(nomeParaPesquisar);

        String[] campos = {null, null, null, null, null};

        for (int i = 0; i < pacientes.size(); i++) {
            modelo.addRow(campos);
            modelo.setValueAt(pacientes.get(i).getId() + "  ", i, 0);
            modelo.setValueAt("  " + pacientes.get(i).getNome(), i, 1);
            modelo.setValueAt("" + pacientes.get(i).getCns(), i, 2);
            modelo.setValueAt("" + pacientes.get(i).getRg(), i, 3);
            modelo.setValueAt("" + pacientes.get(i).getCpf(), i, 4);
        }
        
        if (pacientes.isEmpty()){
            modelo.addRow(campos);
            modelo.setValueAt("Nenhum registro encontrado!",0,1);
        }
        nomeParaPesquisar = null;
    }

    private void linhaSelecionadaTabela() {
        if (tabela.getSelectedRow() != -1) {
            txId.setText(pacientes.get(tabela.getSelectedRow()).getId().toString());
            txPaciente.setText(pacientes.get(tabela.getSelectedRow()).getNome());
            txCartaoSus.setText(pacientes.get(tabela.getSelectedRow()).getCns());
            txPai.setText(pacientes.get(tabela.getSelectedRow()).getNomePai());
            txMae.setText(pacientes.get(tabela.getSelectedRow()).getNomeMae());
            txRg.setText(pacientes.get(tabela.getSelectedRow()).getRg());
            txCpf.setText(pacientes.get(tabela.getSelectedRow()).getCpf());
            txDtNascimento.setText(pacientes.get(tabela.getSelectedRow()).getNascimentoStr());
            txTelefone.setText(pacientes.get(tabela.getSelectedRow()).getTelefone());
            txEndereco.setText(pacientes.get(tabela.getSelectedRow()).getEndereco());
            habilitarEdicaoExclusao(true);
        } else {
            habilitarEdicaoExclusao(false);
            limparDados();
        }
    }

    private void limparDados() {
        txId.setText("");
        txPaciente.setText("");
        txCartaoSus.setText("");
        txMae.setText("");
        txPai.setText("");
        txRg.setText("");
        txCpf.setText("");
        txDtNascimento.setText("");
        txTelefone.setText("");
        txEndereco.setText("");
    }

    private void habilitarCampos(boolean habilitar) {
        txPaciente.setEnabled(habilitar);
        tabela.setEnabled(!habilitar);
        if (habilitar) {
            txPaciente.requestFocus();
        }
        txCartaoSus.setEnabled(habilitar);
        txMae.setEnabled(habilitar);
        txPai.setEnabled(habilitar);
        txRg.setEnabled(habilitar);
        txCpf.setEnabled(habilitar);
        txDtNascimento.setEnabled(habilitar);
        txTelefone.setEnabled(habilitar);
        txEndereco.setEnabled(habilitar);
    }

    private void resetarFormulario() {
        limparDados();
        btInserir.setEnabled(true);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(false);
        habilitarCampos(false);
        //pacientes = null;
        dml = null;
        tabela.clearSelection();//pesquisar(modelo);
        btInserir.requestFocus();
    }

    private void habilitarEdicaoExclusao(boolean habilitar) {
        btInserir.setEnabled(!habilitar);
        btEditar.setEnabled(habilitar);
        btExcluir.setEnabled(habilitar);
    }

    private void salvar() {

        PacienteDao dao = new PacienteDao();

        if (dml == 1) {
            PacienteBean p = new PacienteBean(txPaciente.getText().trim(), txCartaoSus.getText(), txCpf.getText(), txRg.getText().trim().toUpperCase(), txTelefone.getText(), txPai.getText().trim(), txMae.getText().trim(), Utilidades.formataDataSQL(txDtNascimento.getText()), txEndereco.getText().trim());
            if (dao.inserir(p)) {
                JOptionPane.showMessageDialog(this, "Cadastro salvo com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (dml == 2) {
            PacienteBean p = new PacienteBean(Integer.parseInt(txId.getText()), txPaciente.getText().trim(), txCartaoSus.getText(), txCpf.getText(), txRg.getText().trim().toUpperCase(), txTelefone.getText(), txPai.getText().trim(), txMae.getText().trim(), Utilidades.formataDataSQL(txDtNascimento.getText()), txEndereco.getText().trim());
            if (dao.alterar(p)) {
                JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        resetarFormulario();
    }

    private void excluir() {
        PacienteDao dao = new PacienteDao();

        int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {
            if (dao.excluir(Integer.parseInt(txId.getText().trim()))) {
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
        if (e.getSource() == btMostrarTodos) {
            listar(modelo);
        }

        if (e.getSource() == btPesquisar) {
            nomeParaPesquisar = "";
            nomeParaPesquisar = JOptionPane.showInputDialog(this, "Informe o nome do Paciente:");
            if (nomeParaPesquisar != null && !nomeParaPesquisar.isEmpty()) {
                pesquisar(modelo);
            } else {
                if (nomeParaPesquisar != null) {
                    JOptionPane.showMessageDialog(this, "Você precisa informar o nome do paciente!", "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
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
    }

    private boolean validarFormulario() {
        if (txPaciente.getText().trim().equals("")) {
            txPaciente.requestFocus();
            return false;
        }
        return true;
    }
}
