/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfd.modelo;

import tfd.Utilidades.Utilidades;


public class AcompanhanteBean {
    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private String endereco;

    public AcompanhanteBean() {
    }

    public AcompanhanteBean(Integer id, String nome, String rg, String cpf, String endereco) {
        this.id = id;
        this.nome = Utilidades.iniciaisMaiuscula(nome);
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = Utilidades.iniciaisMaiuscula(endereco);
    }

    public AcompanhanteBean(String nome, String rg, String cpf, String endereco) {
        this.nome = Utilidades.iniciaisMaiuscula(nome);
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = Utilidades.iniciaisMaiuscula(endereco);
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Utilidades.iniciaisMaiuscula(nome);
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg.toUpperCase();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = Utilidades.iniciaisMaiuscula(endereco);
    }
    
    
}
