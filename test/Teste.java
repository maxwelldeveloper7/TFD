/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Developer
 */
public class Teste {
    public static void main(String args[]){
        Maiuscula m = new Maiuscula();
        
        System.out.println(m.caixaAlta("MAXWELL DE OLIVEIRA CHAVES"));
        System.out.println(m.caixaAlta("MAXWELL DOS SANTOS"));
        System.out.println(m.caixaAlta("MAXWELL DO AMARAL"));
        System.out.println(m.caixaAlta("MAXWELL DAS VIRGENS"));
        System.out.println(m.caixaAlta("maxwell de o. Chaves"));
    }
}
