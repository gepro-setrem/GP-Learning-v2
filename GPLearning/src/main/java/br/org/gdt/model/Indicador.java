package br.org.gdt.model;

import br.org.gdt.enumerated.Etapa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @Column(name = "ind_vtipo")
    @Enumerated(EnumType.STRING)
    private Etapa tipo;

    @ManyToMany
    private List<Atividade> atividades;

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

    public Etapa getTipo() {
        return tipo;
    }

    public void setTipo(Etapa tipo) {
        this.tipo = tipo;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

}