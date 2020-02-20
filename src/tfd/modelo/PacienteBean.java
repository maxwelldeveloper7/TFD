/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfd.modelo;

import java.sql.Date;
import tfd.Utilidades.Utilidades;

/**
 *
 * @author Developer
 */
public class PacienteBean {
    
    private Integer id;
    private String nome;
    private String cns;
    private String cpf;
    private String rg;
    private String telefone;
    private String nomePai;
    private String nomeMae;
    private Date nascimento;
    private String endereco;

    public PacienteBean() {
    }

    public PacienteBean(String nome, String cns, String cpf, String rg, String telefone, String nomePai, String nomeMae, Date nascimento, String endereco) {
        this.nome = nome;
        this.cns = cns;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public PacienteBean(Integer id, String nome, String cns, String cpf, String rg, String telefone, String nomePai, String nomeMae, Date nascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cns = cns;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.endereco = endereco;
        
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
        this.nome = nome;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getNascimento() {
        return nascimento;
    }
    
    public String getNascimentoStr() {
        return Utilidades.formataDataSTR(nascimento);
    }

    public void setNascimento(String nascimento) {
        this.nascimento = Utilidades.formataDataSQL(nascimento);
    }
    
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }    
}
