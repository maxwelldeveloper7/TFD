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
public class EstabelecimentoBean {
    private Integer id;
    private String nomeEstabelecimento;

    public EstabelecimentoBean() {
    }

    public EstabelecimentoBean(Integer id, String nomeEstabelecimento) {
        this.id = id;
        this.nomeEstabelecimento = Utilidades.iniciaisMaiuscula(nomeEstabelecimento);
    }

    public EstabelecimentoBean(String nomeEstabelecimento) {
        this.nomeEstabelecimento = Utilidades.iniciaisMaiuscula(nomeEstabelecimento);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = Utilidades.iniciaisMaiuscula(nomeEstabelecimento);
    }
}
