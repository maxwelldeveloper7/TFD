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
public class ProcedimentoBean {
    private Integer id;
    private String nomeProcedimento;   
    private EspecialidadeBean especialidade;

    public ProcedimentoBean() {
    }

    
    public ProcedimentoBean(Integer id, String nomeProcedimento, EspecialidadeBean especialidade) {
        this.id = id;
        this.nomeProcedimento = Utilidades.iniciaisMaiuscula(nomeProcedimento);
        this.especialidade = especialidade;
    }

    public ProcedimentoBean(String nomeProcedimento, EspecialidadeBean especialidade) {
        this.nomeProcedimento = Utilidades.iniciaisMaiuscula(nomeProcedimento);
        this.especialidade = especialidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = Utilidades.iniciaisMaiuscula(nomeProcedimento);
    }

    public EspecialidadeBean getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeBean especialidade) {
        this.especialidade = especialidade;
    }
}
