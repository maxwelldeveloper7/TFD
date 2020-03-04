package tfd.modelo;

import tfd.Utilidades.Utilidades;

/**
 *
 * @author Maxwell
 */
public class CidadeBean {

    private Integer id;
    private String nomeCidade;
    private String uf;
    
    public CidadeBean() {
    }

    public CidadeBean(Integer id, String nomeCidade, String uf) {
        this.id = id;
        this.nomeCidade = Utilidades.iniciaisMaiuscula(nomeCidade);
        this.uf = uf;
    }
    
    public CidadeBean(String nomeCidade, String uf) {
        this.nomeCidade = Utilidades.iniciaisMaiuscula(nomeCidade);
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = Utilidades.iniciaisMaiuscula(nomeCidade);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
