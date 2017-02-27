package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    @SequenceGenerator(name = "genturma", sequenceName = "seqturma", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genturma")
    @Id
    @Column(name = "tur_id")
    private int id;

    @Column(name = "tur_vnome", length = 200)
    private String nome;

    @Column(name = "tur_tdescricao", length = 2500)
    private String descricao;

    @Column(name = "tur_iano")
    private int ano;

    @Column(name = "tur_dcriacao")
    private Date criacao;

    @Column(name = "tur_dalteracao")
    private Date alteracao;

//    @Column(length = 10000)
//    private String descricaoTelaInicialGerenciamentoProjetos;
//
//    @Column(length = 10000)
//    private String descricaoTelaGrupoProcessosIniciacao;
//
//    @Column(length = 10000)
//    private String descricaoTelaTermoAberturaDescricao;
//
//    @Column(length = 10000)
//    private String descricaoTelaTermoAberturaJustificativa;
//
//    @Column(length = 10000)
//    private String descricaoTelaTermoAberturaPremissas;
//
//    @Column(length = 10000)
//    private String descricaoTelaTermoAberturaRestricoes;
//
//    @Column(length = 10000)
//    private String descricaoTelaTermoAberturaCronogramaMarcos;
//
//    @Column(length = 10000)
//    private String descricaoTelaTermoAberturaRequisitos;
//
//    @Column(length = 10000)
//    private String descricaoTelaPartesInteressadas;
//
//    @Column(length = 10000)
//    private String descricaoTelaGrupoProcessosPlanejamento;
//
//    @Column(length = 10000)
//    private String descricaoTelaCriarPlanoGerenciamentoProjeto;
//
//    @Column(length = 10000)
//    private String descricaoTelaCriarPlanoGerenciamentoEscopo;
//
//    @Column(length = 10000)
//    private String descricaoTelaColetarRequisitos;
//
//    @Column(length = 10000)
//    private String descricaoTelaDefinirEscopo;
//
//    @Column(length = 10000)
//    private String descricaoTelaCriarEAP;
    @OneToMany(mappedBy = "turma")
    private List<Usuario> academicos;

    @ManyToOne
    public Usuario professor;

    @OneToMany(mappedBy = "turmadoprojeto")
    private List<Projeto> projeto;

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
