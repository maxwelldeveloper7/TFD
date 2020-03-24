package tfd.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
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
import tfd.daos.AcompanhanteDao;
import tfd.modelo.AcompanhanteBean;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmAcompanhante extends JDialog implements ActionListener {

    //Declarando componentes
    private TitledBorder bordaTabela, bordaDados;
    private JPanel pnTabela, pnFundo, pnBotoes, pnDados;
    private JButton btMostrarTodos, btPesquisar, btInserir, btEditar, btSalvar, btCancelar, btExcluir;
    private JTextField txId, txAcompanhante, txRg, txEndereco;
    private JLabel lbId, lbAcompanhante, lbRg, lbCpf, lbEndereco;
    private JFormattedTextField txCpf;
    private final DefaultTableModel modelo;
    private ListSelectionModel lms;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private List<AcompanhanteBean> acompanhantes;
    public Integer dml;
    private MaskFormatter ftmCpf;    
    private String nomeParaPesquisar;

    //Método construtor
    public FrmAcompanhante(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Cadastro de Acompanhantes");//define o título
        URL url = this.getClass().getResource("/tfd/visao/acompanhantes.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da tela
        setBounds((screenSize.width - 544) / 2, (screenSize.height - 454) / 2, 544, 454);//define o tamanho da janela e posiciona ao centro
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
        
        btMostrarTodos = new JButton("Mostrar Todos", new ImageIcon(getClass().getResource("/tfd/visao/mostrartodos.png")));
        btMostrarTodos.setBounds(5, 6, 145, 30);

        btPesquisar = new JButton("Pesquisar", new ImageIcon(getClass().getResource("/tfd/visao/pesquisar.png")));
        btPesquisar.setBounds(155, 6, 145, 30);

        //painel que conterá a tabela
        pnTabela = new JPanel(null);
        pnTabela.setBounds(4, 43, 530, 200);
        bordaTabela = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Listagem de Acompanhantes");
        pnTabela.setBorder(bordaTabela);

        //construindo tabela
        tabela = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("RG");
        modelo.addColumn("CPF");
        modelo.addColumn("Endereço");
        tabela.setAutoResizeMode(0);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(200);
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
        barraRolagem.setBounds(5, 17, 520, 178);
        pnTabela.add(barraRolagem);

        //Painel que conterá o formulário de dados
        pnDados = new JPanel(null);
        pnDados.setBounds(4, 248, 530, 130);
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

        lbAcompanhante = new JLabel("Nome:");
        lbAcompanhante.setBounds(60, 20, 100, 20);
        pnDados.add(lbAcompanhante);
        txAcompanhante = new JTextField();
        txAcompanhante.setBounds(60, 40, 220, 20);
        pnDados.add(txAcompanhante);

        lbRg = new JLabel("RG:");
        lbRg.setBounds(300, 20, 20, 20);
        pnDados.add(lbRg);
        txRg = new JTextField();
        txRg.setBounds(300, 40, 100, 20);
        pnDados.add(txRg);

        lbCpf = new JLabel("CPF:");
        lbCpf.setBounds(420, 20, 100, 20);
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
        txCpf.setBounds(420, 40, 100, 20);
        pnDados.add(txCpf);

        lbEndereco = new JLabel("Endereço:");
        lbEndereco.setBounds(10, 70, 100, 20);
        pnDados.add(lbEndereco);

        txEndereco = new JTextField();
        txEndereco.setBounds(10, 90, 510, 20);
        pnDados.add(txEndereco);

        //Construindo painel de botões
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(4, 382, 530, 38);

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
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        txCpf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!txCpf.getText().equals("   .   .   -  ")) {
                    
                    if(Utilidades.cpfValido(txCpf.getText()) ){
                        if(dml == 1){
                            AcompanhanteDao dao = new AcompanhanteDao();
                            AcompanhanteBean a;
                            a = dao.pesquisarCpf(Utilidades.getDigitos(txCpf.getText()));
                            if(a.getCpf() != null){
                                JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
                                resetarFormulario();
                                pesquisarPorCpf(modelo, a.getCpf());
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "CPF não informado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    //tratando eventos    
    private void listar(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        AcompanhanteDao dao = new AcompanhanteDao();

        acompanhantes = dao.listar();

        String[] campos = {null, null, null, null, null};

        for (int i = 0; i < acompanhantes.size(); i++) {
            modelo.addRow(campos);
            modelo.setValueAt(acompanhantes.get(i).getId() + "  ", i, 0);
            modelo.setValueAt("  " + acompanhantes.get(i).getNome(), i, 1);
            modelo.setValueAt("" + acompanhantes.get(i).getRg(), i, 2);
            modelo.setValueAt("" + Utilidades.mascara(acompanhantes.get(i).getCpf(),"###.###.###-##"), i, 3);
            modelo.setValueAt("" + acompanhantes.get(i).getEndereco(), i, 4);
        }
    }
    
    private void pesquisarPorNome(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        AcompanhanteDao dao = new AcompanhanteDao();

        acompanhantes = dao.listar(Utilidades.iniciaisMaiuscula(nomeParaPesquisar));

        String[] campos = {null, null, null, null, null};

        for (int i = 0; i < acompanhantes.size(); i++) {
            modelo.addRow(campos);
            modelo.setValueAt(acompanhantes.get(i).getId() + "  ", i, 0);
            modelo.setValueAt("  " + acompanhantes.get(i).getNome(), i, 1);
            modelo.setValueAt("" + acompanhantes.get(i).getRg(), i, 2);
            modelo.setValueAt("" + Utilidades.mascara(acompanhantes.get(i).getCpf(),"###.###.###-##"), i, 3);
            modelo.setValueAt("" + acompanhantes.get(i).getEndereco(), i, 4);
        }
        
        if (acompanhantes.isEmpty()){
            modelo.addRow(campos);
            modelo.setValueAt("Nenhum registro encontrado!",0,1);
        }
        nomeParaPesquisar = null;
    }
    
    private void pesquisarPorCpf(DefaultTableModel modelo, String cpf) {
        modelo.setRowCount(0);
        AcompanhanteDao dao = new AcompanhanteDao();               
        acompanhantes = new ArrayList<>();
        System.out.println(acompanhantes);
        
        acompanhantes.add(dao.pesquisarCpf(cpf));

        String[] campos = {null, null, null, null, null};

        for (int i = 0; i < acompanhantes.size(); i++) {
            modelo.addRow(campos);
            modelo.setValueAt(acompanhantes.get(i).getId() + "  ", i, 0);
            modelo.setValueAt("  " + acompanhantes.get(i).getNome(), i, 1);
            modelo.setValueAt("" + acompanhantes.get(i).getRg(), i, 2);
            modelo.setValueAt("" + Utilidades.mascara(acompanhantes.get(i).getCpf(),"###.###.###-##"), i, 3);
            modelo.setValueAt("" + acompanhantes.get(i).getEndereco(), i, 4);
        }
        
        if (acompanhantes.isEmpty()){
            modelo.addRow(campos);
            modelo.setValueAt("Nenhum registro encontrado!",0,1);
        }
        nomeParaPesquisar = null;
    }

    private void linhaSelecionadaTabela() {
        if (tabela.getSelectedRow() != -1) {
            txId.setText(acompanhantes.get(tabela.getSelectedRow()).getId().toString());
            txAcompanhante.setText(acompanhantes.get(tabela.getSelectedRow()).getNome());
            txRg.setText(acompanhantes.get(tabela.getSelectedRow()).getRg());
            txCpf.setText(acompanhantes.get(tabela.getSelectedRow()).getCpf());
            txEndereco.setText(acompanhantes.get(tabela.getSelectedRow()).getEndereco());
            habilitarEdicaoExclusao(true);
        } else {
            habilitarEdicaoExclusao(false);
            limparDados();
        }
    }

    private void limparDados() {
        txId.setText("");
        txAcompanhante.setText("");
        txRg.setText("");
        txCpf.setText("");
        txEndereco.setText("");
    }

    private void habilitarCampos(boolean habilitar) {
        txAcompanhante.setEnabled(habilitar);
        tabela.setEnabled(!habilitar);
        if (habilitar) {
            txAcompanhante.requestFocus();
        }
        txRg.setEnabled(habilitar);
        txCpf.setEnabled(habilitar);
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
        //acompanhantes = null;
        dml = null;
        //pesquisar(modelo);
        tabela.clearSelection();
        btInserir.requestFocus();
    }

    private void habilitarEdicaoExclusao(boolean habilitar) {
        btInserir.setEnabled(!habilitar);
        btEditar.setEnabled(habilitar);
        btExcluir.setEnabled(habilitar);
    }

    private void salvar() {

        AcompanhanteDao dao = new AcompanhanteDao();

        if (dml == 1) {
            AcompanhanteBean a = new AcompanhanteBean(txAcompanhante.getText(), txRg.getText().trim().toUpperCase(), Utilidades.getDigitos(txCpf.getText()), txEndereco.getText().trim());
            if (dao.inserir(a)) {
                JOptionPane.showMessageDialog(this, "Cadastro salvo com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (dml == 2) {
            AcompanhanteBean a = new AcompanhanteBean(Integer.parseInt(txId.getText()), txAcompanhante.getText(), txRg.getText().trim().toUpperCase(), Utilidades.getDigitos(txCpf.getText()), txEndereco.getText().trim());
            if (dao.alterar(a)) {
                JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                modelo.setRowCount(0);
            }
        }

        resetarFormulario();
    }

    private void excluir() {
        AcompanhanteDao dao = new AcompanhanteDao();

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
            nomeParaPesquisar = JOptionPane.showInputDialog(this, "Informe o nome do acompanhante:");
            if (nomeParaPesquisar != null && !nomeParaPesquisar.isEmpty()) {
                pesquisarPorNome(modelo);
            } else {
                if (nomeParaPesquisar != null) {
                    JOptionPane.showMessageDialog(this, "Você precisa informar o nome do acompanhante!", "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
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
        if (txAcompanhante.getText().trim().equals("")) {
            txAcompanhante.requestFocus();
            return false;
        }
        return true;
    }
}
