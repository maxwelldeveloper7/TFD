/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfd.controle;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tfd.visao.FrmMotoristas;
import tfd.visao.FrmPrincipal;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class Controle {
    
    public static Controle instanceSingleton = null;
    public static FrmPrincipal principal = null;
    public static FrmMotoristas motoristas = null;
    
    private Controle(){
    }
    
    public static Controle getInstance(){
        if(instanceSingleton == null){
            instanceSingleton = new Controle();
        }
        return instanceSingleton;
    }
    
    public static void inicializar(){
        /*
        WindowsLookAndFeel laf = new WindowsClassicLookAndFeel();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao setoar LookAndFell."+
                    "/n"+ex.getMessage()+"/nA aplicação será encerrada.");
            System.exit(0);
        }*/
        abreFrmPrincipal();
    }
    
    private static void abreFrmPrincipal(){
        if(principal == null){
            principal = new FrmPrincipal();
        }
        principal.setVisible(true);
    }
    
    public static void abreFrmMotoristas(JFrame parent, boolean modal){
        if(motoristas == null){
            motoristas = new FrmMotoristas( parent,  modal);
        }
        motoristas.setVisible(true);
    }
}
