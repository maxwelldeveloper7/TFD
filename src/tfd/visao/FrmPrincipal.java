package tfd.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import tfd.controle.Controle;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmPrincipal extends JFrame implements ActionListener{

    //Declarando componentes da tela
    
    private JMenuBar menu;
    private JMenu mnuCadastros, mnuRelatórios;
    private JMenuItem mnuCidades, mnuMotoristas, mnuEstabelecimentos, mnuEspecialidades, mnuProcedimentos, mnuClientes, mnuAcompanhantes, mnuAgendamento;
    private JPanel pnFundo, pnBarraStatus;
    private JLabel lbImagem, lbUsuario, lbNomeUsuario;
    
    //Método Construtor
    public FrmPrincipal(){
        setTitle("Prefeitura Municipal de Nanuque-MG | Secretaria de Saúde");
        URL url = this.getClass().getResource("/tfd/visao/favicon.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension tamanhoDaTela = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da 
        setBounds((tamanhoDaTela.width - 625) / 2, (tamanhoDaTela.height - 500) / 2, 625, 500);//define o tamanho da janela e posiciona ao centro
        setResizable(false);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirComponentes();
        JOptionPane.showMessageDialog(this, "implementar login");
    }
    
    /**
     * Inicializa componentes da tela e os posiciona
    */
    private void construirComponentes(){
        //Inicializando e setando menus
        menu = new JMenuBar();
        setJMenuBar(menu);
        
        mnuCadastros = new JMenu("Cadastros");
        menu.add(mnuCadastros);
        
        mnuAcompanhantes = new JMenuItem("Acompanhantes", new ImageIcon(getClass().getResource("/tfd/visao/acompanhantes.png")));
        mnuCadastros.add(mnuAcompanhantes);
        mnuAgendamento = new JMenuItem("Agendamento", new ImageIcon(getClass().getResource("/tfd/visao/agendamento.png")));
        mnuCadastros.add(mnuAgendamento);
        mnuCidades = new JMenuItem("Cidades", new ImageIcon(getClass().getResource("/tfd/visao/cidades.png")));
        mnuCadastros.add(mnuCidades);        
        mnuClientes = new JMenuItem("Clientes...", new ImageIcon(getClass().getResource("/tfd/visao/clientes.png")));
        mnuCadastros.add(mnuClientes);
        mnuEspecialidades = new JMenuItem("Especialidades", new ImageIcon(getClass().getResource("/tfd/visao/especialidades.png")));
        mnuCadastros.add(mnuEspecialidades);
        mnuEstabelecimentos = new JMenuItem("Estabelecimentos", new ImageIcon(getClass().getResource("/tfd/visao/estabelecimentos.png")));
        mnuCadastros.add(mnuEstabelecimentos);
        mnuMotoristas = new JMenuItem("Motoristas", new ImageIcon(getClass().getResource("/tfd/visao/carro16.png")));
        mnuCadastros.add(mnuMotoristas);
        mnuProcedimentos = new JMenuItem("Procedimentos", new ImageIcon(getClass().getResource("/tfd/visao/procedimentos.png")));
        mnuCadastros.add(mnuProcedimentos);
                
        mnuRelatórios = new JMenu("Relatórios");
        menu.add(mnuRelatórios);
        
        //definindo imagem de fundo
        lbImagem = new JLabel(new ImageIcon(getClass().getResource("/tfd/visao/tfdfundo.jpg")));
        lbImagem.setBounds(10, 10, 600, 400);
        
       
        //definindo labels para barra de status
        lbUsuario = new JLabel("Usuário: ");
        lbUsuario.setBounds(10, 5, 63, 20);
        
        lbNomeUsuario = new JLabel();
        lbNomeUsuario.setText("Nome do Usuário");
        lbNomeUsuario.setBounds(62, 5, 200, 20);
        
        
        
        pnBarraStatus = new JPanel();
        pnBarraStatus.setLayout(null);
        pnBarraStatus.setBounds(1, 419, 618, 30);
        pnBarraStatus.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        pnBarraStatus.setBackground(Color.decode("#ADD8E6"));
        pnBarraStatus.add(lbUsuario);
        pnBarraStatus.add(lbNomeUsuario);
        
        pnFundo = new JPanel();
        pnFundo.setBackground(Color.decode("#ADD8E6"));
        pnFundo.setLayout(null);
        
        pnFundo.add(lbImagem);        
        
        pnFundo.add(pnBarraStatus);
        
        getContentPane().add(pnFundo);
        mnuMotoristas.addActionListener(this);
        mnuCidades.addActionListener(this);
        mnuEspecialidades.addActionListener(this);
        mnuProcedimentos.addActionListener(this);
        mnuEstabelecimentos.addActionListener(this);
        mnuAcompanhantes.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mnuMotoristas){
            Controle.abreFrmMotoristas(this, true);
        }
        
        if(e.getSource() == mnuCidades){
            Controle.abreFrmCidades(this, true);
        }
        
        if(e.getSource() == mnuEspecialidades){
            Controle.abreFrmEspecialidades(this, true);
        }
        if(e.getSource() == mnuProcedimentos){
            Controle.abreFrmProcedimentos(this, true);
        }
        if(e.getSource() == mnuEstabelecimentos){
            Controle.abreFrmEstabelecimentos(this, true);
        }
        if(e.getSource() == mnuAcompanhantes){
            Controle.abreFrmAcompanhates(this, true);
        }
    }
    
}
