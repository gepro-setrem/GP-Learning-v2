package br.org.gdt.model;

import br.org.gdt.enumerated.EtapaProjeto;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "pes_id")
    public Pessoa professor;

    @ManyToMany(mappedBy = "indicadores", cascade = CascadeType.REMOVE)
    private List<Etapa> etapas;

    @OneToMany(mappedBy = "indicador", cascade = CascadeType.REMOVE)
    private List<Avaliacao> avaliacoes;

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

    public Pessoa getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Indicador other = (Indicador) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
}
