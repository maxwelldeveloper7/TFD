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
public class EspecialidadeBean {
    
    private Integer id;
    private String nomeEspecialidade;
    
    
    public EspecialidadeBean() {
    }

    public EspecialidadeBean(Integer id, String nomeEspecialidade) {
        this.id = id;
        this.nomeEspecialidade = Utilidades.iniciaisMaiuscula(nomeEspecialidade);
    }

    public EspecialidadeBean(String nomeEspecialidade) {
        this.nomeEspecialidade = Utilidades.iniciaisMaiuscula(nomeEspecialidade);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = Utilidades.iniciaisMaiuscula(nomeEspecialidade);;
    }
}
