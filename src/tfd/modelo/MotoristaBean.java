/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfd.modelo;

/**
 *
 * @author Developer
 */
public class MotoristaBean {

    private int id;
    private String nomeMotorista;
    private String telefone;
    private boolean ativo;
    
    public MotoristaBean() {
    
    }

    public MotoristaBean(int id, String nomeMotorista, String telefone, boolean ativo) {
        this.id = id;
        this.nomeMotorista = nomeMotorista;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
    
    
}
