package tfd.visao;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class FrmPrincipal extends JFrame implements ActionListener{

    //Declarando componentes da tela
    
    private JMenuBar menu;
    
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
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
