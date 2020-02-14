/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfd.controle;

import javax.swing.JFrame;
import tfd.visao.FrmAcompanhante;
import tfd.visao.FrmCidades;
import tfd.visao.FrmEspecialidades;
import tfd.visao.FrmEstabelecimentos;
import tfd.visao.FrmMotoristas;
import tfd.visao.FrmPacientes;
import tfd.visao.FrmPrincipal;
import tfd.visao.FrmProcedimentos;

/**
 *
 * @author Maxwell de Oliveira Chaves <maxwellchaves1844@gmail.com>
 */
public class Controle {
    
    public static Controle instanceSingleton = null;
    public static FrmPrincipal principal = null;
    public static FrmMotoristas motoristas = null;
    public static FrmCidades cidades = null;
    public static FrmEspecialidades especialidades = null;
    public static FrmProcedimentos procedimentos = null;
    public static FrmEstabelecimentos estabelecimentos = null;
    public static FrmAcompanhante acompanhantes = null;
    public static FrmPacientes pacientes = null;
    
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
    
    public static void abreFrmCidades(JFrame parent, boolean modal){
        if(cidades == null){
            cidades = new FrmCidades(parent,  modal);
        }
        cidades.setVisible(true);
    }
    
    public static void abreFrmEspecialidades(JFrame parent, boolean modal){
        if(especialidades == null){
            especialidades = new FrmEspecialidades(parent, modal);
        }
        especialidades.setVisible(true);
    }
    
    public static void abreFrmProcedimentos(JFrame parent, boolean modal){
        if(procedimentos == null){
            procedimentos = new FrmProcedimentos(parent, modal);
        }
        procedimentos.setVisible(true);
    }
    
    public static void abreFrmEstabelecimentos(JFrame parent, boolean modal){
        if(estabelecimentos == null){
            estabelecimentos = new FrmEstabelecimentos(parent, modal);
        }
        estabelecimentos.setVisible(true);
    }
    
    public static void abreFrmAcompanhates(JFrame parent, boolean modal){
        if(acompanhantes == null){
            acompanhantes = new FrmAcompanhante(parent, modal);
        }
        acompanhantes.setVisible(true);
    }
    
    public static void abreFrmPacientes(JFrame parent, boolean modal){
        if(pacientes == null){
            pacientes = new FrmPacientes(parent, modal);
        }
        pacientes.setVisible(true);
    }
}
