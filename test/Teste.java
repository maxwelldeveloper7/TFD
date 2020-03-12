
import tfd.Utilidades.Utilidades;

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
        if(Utilidades.cpfValido("04960780622")){
            System.out.println("CPF válido");
        }else{
            System.out.println("CPF inválido");
        }
    }
}
