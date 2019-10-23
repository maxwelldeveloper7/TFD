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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tfd.controle.Controle;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmPrincipal extends JFrame implements ActionListener{

    //Declarando componentes da tela
    
    private JMenuBar menu;
    private JMenu mnuCadastros, mnuRelatórios;
    private JMenuItem mnuCidades, mnuMotoristas, mnuEstabelecimentos, mnuEspecialidades, mnuProcedimentos, mnuClientes, mnuAcompanhantes, mnuPassagens;
    private JPanel pnFundo, pnBotoes;
    private JButton btAgenda, btLiberacao;
    
    //Método Construtor
    public FrmPrincipal(){
        setTitle("TFD - Tratamento Fora do Domicílio | Secretaria Municipal de Saúde de Nanuque-MG");
        URL url = this.getClass().getResource("/tfd/visao/favicon.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension tamanhoDaTela = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da 
        setBounds((tamanhoDaTela.width - 800) / 2, (tamanhoDaTela.height - 600) / 2, 800, 600);//define o tamanho da janela e posiciona ao centro
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
        
        mnuAcompanhantes = new JMenuItem("Acompanhantes...", new ImageIcon(getClass().getResource("/tfd/visao/acompanhantes.png")));
        mnuCadastros.add(mnuAcompanhantes);
        mnuCidades = new JMenuItem("Cidades", new ImageIcon(getClass().getResource("/tfd/visao/cidades.png")));
        mnuCadastros.add(mnuCidades);        
        mnuClientes = new JMenuItem("Clientes...", new ImageIcon(getClass().getResource("/tfd/visao/clientes.png")));
        mnuCadastros.add(mnuClientes);
        mnuEspecialidades = new JMenuItem("Especialidades", new ImageIcon(getClass().getResource("/tfd/visao/especialidades.png")));
        mnuCadastros.add(mnuEspecialidades);
        mnuEstabelecimentos = new JMenuItem("Estabelecimentos...", new ImageIcon(getClass().getResource("/tfd/visao/estabelecimentos.png")));
        mnuCadastros.add(mnuEstabelecimentos);
        mnuMotoristas = new JMenuItem("Motoristas", new ImageIcon(getClass().getResource("/tfd/visao/carro16.png")));
        mnuCadastros.add(mnuMotoristas);
        mnuPassagens = new JMenuItem("Passagens...", new ImageIcon(getClass().getResource("/tfd/visao/passagem.png")));
        mnuCadastros.add(mnuPassagens);
        mnuProcedimentos = new JMenuItem("Procedimentos...", new ImageIcon(getClass().getResource("/tfd/visao/procedimentos.png")));
        mnuCadastros.add(mnuProcedimentos);
                
        mnuRelatórios = new JMenu("Relatórios");
        menu.add(mnuRelatórios);
        

        //Inicializando Botões
        btAgenda = new JButton("Agendamento do Carro");
        btAgenda.setIcon(new ImageIcon(getClass().getResource("/tfd/visao/carro.png")));
        //btAgenda.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btLiberacao = new JButton("Liberação de Passagens");
        btLiberacao.setIcon(new ImageIcon(getClass().getResource("/tfd/visao/onibus.png")));
        
        //Definindo Paineis
        pnBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        pnBotoes.setBackground(Color.GRAY);
        pnBotoes.add(btAgenda);
        pnBotoes.add(btLiberacao);
        pnBotoes.setBounds(0, 200, 795, 75);
        
        pnFundo = new JPanel();
        pnFundo.setBackground(Color.GRAY);
        pnFundo.setLayout(null);
        pnFundo.add(pnBotoes);
        
        getContentPane().add(pnFundo);
        mnuMotoristas.addActionListener(this);
        mnuCidades.addActionListener(this);
        mnuEspecialidades.addActionListener(this);
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
    }
    
}
