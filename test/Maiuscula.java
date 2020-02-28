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

    public String iniciaisMaiuscula(String nome) {
        String nomeCompleto = "";

        nome = nome.toLowerCase();

        for (int i = 0; i < nome.length(); i++) {
            String nomeParcial = "";
            String maiuscula = "";

            if (i == 0) {//verifica se é o primeiro
                maiuscula += nome.charAt(i);//armazena o caractere na variável
                nomeCompleto += maiuscula.toUpperCase();//concatena o primeiro caractere transformado em maiúscolo
                maiuscula = "";//reinicia a variável
            } else {

                String anterior = "";
                anterior += nome.charAt(i - 1);

                if (anterior.equals(" ")) {//verifica se o caractere anterior é um espaço
                    String pre = "";//inicializa a variável de preposicão

                    if (i < nome.length() - 3) {//verifica se é o ultimo caractere para não ultrapassar a faixa da string
                        pre += nome.charAt(i);
                        pre += nome.charAt(i+1);
                    } else {

                    }
                    maiuscula += nome.charAt(i);//armazena o caractere na variável
                    if(pre.equals("de") || pre.equals("da") || pre.equals("dos") || pre.equals("do") || pre.equals("das")){
                        nomeCompleto += maiuscula.toLowerCase();
                    }else{
                        nomeCompleto += maiuscula.toUpperCase();
                    }
                    //nomeCompleto += maiuscula.toUpperCase();//concatena o caractere transformado em maiúscolo
                    maiuscula = "";//reinicia a variável
                } else {
                    nomeParcial += nome.charAt(i);
                    nomeCompleto += nomeParcial;
                }

            }

        }
        return nomeCompleto;
    }
}
