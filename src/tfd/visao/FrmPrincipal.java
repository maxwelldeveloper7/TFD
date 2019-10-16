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
    private JMenuItem mnuCidades, mnuMotoristas, mnuEstabelecimentos, mnuEspecialidades, mnuProcedimentos, mnuClientes;
    private JPanel pnFundo, pnBotoes;
    private JButton btAgenda, btLiberacao;
    
    //Método Construtor
    public FrmPrincipal(){
        setTitle("TFD - Tramento Fora do Domicílio");
        URL url = this.getClass().getResource("/tfd/visao/favicon.png");//caminho para arquivo
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);//objeto imagem
        setIconImage(iconeTitulo);//define uma imagem para o icone
        Dimension tamanhoDaTela = Toolkit.getDefaultToolkit().getScreenSize();//pega a dimensão da 
        setBounds((tamanhoDaTela.width - 800) / 2, (tamanhoDaTela.height - 600) / 2, 800, 600);//define o tamanho da janela e posiciona ao centro
        setResizable(false);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirComponentes();
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
        
        mnuCidades = new JMenuItem("Cidades");
        mnuCadastros.add(mnuCidades);        
        mnuClientes = new JMenuItem("Clientes");
        mnuCadastros.add(mnuClientes);
        mnuEspecialidades = new JMenuItem("Especialidades");
        mnuCadastros.add(mnuEspecialidades);
        mnuEstabelecimentos = new JMenuItem("Estabelecimentos");
        mnuCadastros.add(mnuEstabelecimentos);
        mnuMotoristas = new JMenuItem("Motoristas");
        mnuCadastros.add(mnuMotoristas);
        mnuProcedimentos = new JMenuItem("Procedimentos");
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
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mnuMotoristas){
            Controle.abreFrmMotoristas(this, true);
        }
    }
    
}
