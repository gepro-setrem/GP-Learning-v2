package br.org.gdt.model;

import br.org.gdt.enumerated.EtapaProjeto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Indicador implements Serializable {

    @SequenceGenerator(name = "genindicador", sequenceName = "seqindicador", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genindicador")

    @Id
    @Column(name = "ind_id")
    private int id;

    @Column(name = "ind_vnome", length = 200)
    private String nome;

    @Column(name = "ind_ivalor")
    private int valor;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    public Pessoa professor;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<EtapaProjeto> atividades;

    public Indicador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Pessoa getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    public List<EtapaProjeto> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<EtapaProjeto> atividades) {
        this.atividades = atividades;
    }

}
