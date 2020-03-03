/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Developer
 */
public class Maiuscula {

    public String caixaAlta(String nome) {
        
        String nomeCompleto = "";

        //converte toda a string em minúsculo
        nome = nome.toLowerCase();

        
        //laço de repetição para percorrer toda cadeia de caracteres do nome
        for (int i = 0; i < nome.length(); i++) {
                        
            //armazena o caractere para realizar verificações
            String caractere = "";

            //verifica se é o primeiro caractere
            if (i == 0) {
                //armazena o caractere na variável
                caractere += nome.charAt(i);
                
                //concatena o primeiro caractere transformado em maiúscolo
                nomeCompleto += caractere.toUpperCase();
            
            //caso contrário
            } else {

                //armazena caractere anterior ao indicando pelo ídice
                String anterior = "";
                
                //atribui o caractere anterior
                anterior += nome.charAt(i - 1);

                //verifica se o caractere anterior é um espaço
                if (anterior.equals(" ")) {
                    
                    //inicializa a variável de preposicão
                    String pre = "";

                    //verifica se é o ultimo caractere para não ultrapassar a faixa da string
                    if (i < nome.length() - 3) {
                        //se não ultrapassa atribui à variável pre os 2 próximos caracteres
                        pre += nome.charAt(i);
                        pre += nome.charAt(i+1);
                    }
                    
                    //armazena o caractere na variável
                    caractere += nome.charAt(i);
                    
                    //verifica se os dois próximos caracteres são preposição para mantélos em minúsculo
                    if(pre.equals("de") || pre.equals("da") || pre.equals("dos") || pre.equals("do") || pre.equals("das")){
                        //concatena o caractere minúsculo ao nome completo
                        nomeCompleto += caractere.toLowerCase();
                    
                    //caso contrário converte o caractere para maiúsculo
                    }else{
                        //concatena o caractere maiúsculo ao nome completo
                        nomeCompleto += caractere.toUpperCase();
                    }
                //caso contrário apenas concatena o caractere ao nome completo
                } else {
                    nomeCompleto += nome.charAt(i);
                }
            }
        }
        return nomeCompleto;
    }
}
