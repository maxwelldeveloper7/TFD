/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfd.modelo;

import tfd.Utilidades.Utilidades;

/**
 *
 * @author Developer
 */
public class MotoristaBean {

    private Integer id;
    private String nomeMotorista;
    private String telefone;
    private boolean ativo;
    
    public MotoristaBean() {
    
    }

    public MotoristaBean(Integer id, String nomeMotorista, String telefone, boolean ativo) {
        this.id = id;
        this.nomeMotorista = Utilidades.iniciaisMaiuscula(nomeMotorista);
        this.telefone = telefone;
        this.ativo = ativo;
    }
    
    public MotoristaBean(Integer id, String nomeMotorista, String telefone, String ativo) {
        this.id = id;
        this.nomeMotorista = Utilidades.iniciaisMaiuscula(nomeMotorista);;
        this.telefone = telefone;
        this.setAtivo(ativo);
    }
    
    public MotoristaBean(String nomeMotorista, String telefone, String ativo) {
        this.nomeMotorista = nomeMotorista;
        this.telefone = telefone;
        this.setAtivo(ativo);
    }

    public int getId() {
        return id;
    }
    
    public String getIdStr(){
        return Integer.toString(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = Utilidades.iniciaisMaiuscula(nomeMotorista);;
    }

    public String getTelefone() {
        return Utilidades.getDigitos(telefone);
    }
    
    public String getTelefoneMask(){
        return Utilidades.formataStringMascara(telefone, "(##)#####-####");
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }
    
    public String getAtivo() {
        if(ativo){
            return "Ativo";
        }else{
            return "Inativo";
        }
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public final void setAtivo(String arg){
        if(arg.equals("Ativo")){
            this.ativo = true;
        }else{
            this.ativo = false;
        }
    }
}
