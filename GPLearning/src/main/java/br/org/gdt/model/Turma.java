/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.model;

import java.io.Serializable;
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

@Entity
public class Turma implements Serializable {

    @SequenceGenerator(name = "genturma", sequenceName = "seqturma", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genturma")
    @Id
    private int idTurma;
    
    private int ano;
    
    private String nomeTurma;
    @Column(length = 10000)
    private String descricaoTelaInicialGerenciamentoProjetos;

    @Column(length = 10000)
    private String descricaoTelaGrupoProcessosIniciacao;
    
    @Column(length = 10000)
    private String descricaoTelaTermoAberturaDescricao;
    
    @Column(length = 10000)
    private String descricaoTelaTermoAberturaJustificativa;
    
    @Column(length = 10000)
    private String descricaoTelaTermoAberturaPremissas;
    
    @Column(length = 10000)
    private String descricaoTelaTermoAberturaRestricoes;
    
    @Column(length = 10000)
    private String descricaoTelaTermoAberturaCronogramaMarcos;
    
    @Column(length = 10000)
    private String descricaoTelaTermoAberturaRequisitos;
    
    @Column(length = 10000)
    private String descricaoTelaPartesInteressadas;
    
    @Column(length = 10000)
    private String descricaoTelaGrupoProcessosPlanejamento;
    
    @Column(length = 10000)
    private String descricaoTelaCriarPlanoGerenciamentoProjeto;
    
    @Column(length = 10000)
    private String descricaoTelaCriarPlanoGerenciamentoEscopo;
    
    @Column(length = 10000)
    private String descricaoTelaColetarRequisitos;
    
    @Column(length = 10000)
    private String descricaoTelaDefinirEscopo;
    
    @Column(length = 10000)
    private String descricaoTelaCriarEAP;
    
    
    @OneToMany(mappedBy = "turma")
    private List<Usuario> academicos;

    @ManyToOne
    public Usuario professor;
    
    @OneToMany(mappedBy = "turmadoprojeto")
    private List<Projeto> projeto;
    

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getDescricaoTelaInicialGerenciamentoProjetos() {
        return descricaoTelaInicialGerenciamentoProjetos;
    }

    public void setDescricaoTelaInicialGerenciamentoProjetos(String descricaoTelaInicialGerenciamentoProjetos) {
        this.descricaoTelaInicialGerenciamentoProjetos = descricaoTelaInicialGerenciamentoProjetos;
    }

    public String getDescricaoTelaGrupoProcessosIniciacao() {
        return descricaoTelaGrupoProcessosIniciacao;
    }

    public void setDescricaoTelaGrupoProcessosIniciacao(String descricaoTelaGrupoProcessosIniciacao) {
        this.descricaoTelaGrupoProcessosIniciacao = descricaoTelaGrupoProcessosIniciacao;
    }

    public String getDescricaoTelaTermoAberturaDescricao() {
        return descricaoTelaTermoAberturaDescricao;
    }

    public void setDescricaoTelaTermoAberturaDescricao(String descricaoTelaTermoAberturaDescricao) {
        this.descricaoTelaTermoAberturaDescricao = descricaoTelaTermoAberturaDescricao;
    }

    public String getDescricaoTelaTermoAberturaJustificativa() {
        return descricaoTelaTermoAberturaJustificativa;
    }

    public void setDescricaoTelaTermoAberturaJustificativa(String descricaoTelaTermoAberturaJustificativa) {
        this.descricaoTelaTermoAberturaJustificativa = descricaoTelaTermoAberturaJustificativa;
    }

    public String getDescricaoTelaTermoAberturaPremissas() {
        return descricaoTelaTermoAberturaPremissas;
    }

    public void setDescricaoTelaTermoAberturaPremissas(String descricaoTelaTermoAberturaPremissas) {
        this.descricaoTelaTermoAberturaPremissas = descricaoTelaTermoAberturaPremissas;
    }

    public String getDescricaoTelaTermoAberturaRestricoes() {
        return descricaoTelaTermoAberturaRestricoes;
    }

    public void setDescricaoTelaTermoAberturaRestricoes(String descricaoTelaTermoAberturaRestricoes) {
        this.descricaoTelaTermoAberturaRestricoes = descricaoTelaTermoAberturaRestricoes;
    }

    public String getDescricaoTelaTermoAberturaCronogramaMarcos() {
        return descricaoTelaTermoAberturaCronogramaMarcos;
    }

    public void setDescricaoTelaTermoAberturaCronogramaMarcos(String descricaoTelaTermoAberturaCronogramaMarcos) {
        this.descricaoTelaTermoAberturaCronogramaMarcos = descricaoTelaTermoAberturaCronogramaMarcos;
    }

    public String getDescricaoTelaTermoAberturaRequisitos() {
        return descricaoTelaTermoAberturaRequisitos;
    }

    public void setDescricaoTelaTermoAberturaRequisitos(String descricaoTelaTermoAberturaRequisitos) {
        this.descricaoTelaTermoAberturaRequisitos = descricaoTelaTermoAberturaRequisitos;
    }

    public String getDescricaoTelaPartesInteressadas() {
        return descricaoTelaPartesInteressadas;
    }

    public void setDescricaoTelaPartesInteressadas(String descricaoTelaPartesInteressadas) {
        this.descricaoTelaPartesInteressadas = descricaoTelaPartesInteressadas;
    }

    public String getDescricaoTelaGrupoProcessosPlanejamento() {
        return descricaoTelaGrupoProcessosPlanejamento;
    }

    public void setDescricaoTelaGrupoProcessosPlanejamento(String descricaoTelaGrupoProcessosPlanejamento) {
        this.descricaoTelaGrupoProcessosPlanejamento = descricaoTelaGrupoProcessosPlanejamento;
    }

    public String getDescricaoTelaCriarPlanoGerenciamentoProjeto() {
        return descricaoTelaCriarPlanoGerenciamentoProjeto;
    }

    public void setDescricaoTelaCriarPlanoGerenciamentoProjeto(String descricaoTelaCriarPlanoGerenciamentoProjeto) {
        this.descricaoTelaCriarPlanoGerenciamentoProjeto = descricaoTelaCriarPlanoGerenciamentoProjeto;
    }

    public String getDescricaoTelaCriarPlanoGerenciamentoEscopo() {
        return descricaoTelaCriarPlanoGerenciamentoEscopo;
    }

    public void setDescricaoTelaCriarPlanoGerenciamentoEscopo(String descricaoTelaCriarPlanoGerenciamentoEscopo) {
        this.descricaoTelaCriarPlanoGerenciamentoEscopo = descricaoTelaCriarPlanoGerenciamentoEscopo;
    }

    public String getDescricaoTelaColetarRequisitos() {
        return descricaoTelaColetarRequisitos;
    }

    public void setDescricaoTelaColetarRequisitos(String descricaoTelaColetarRequisitos) {
        this.descricaoTelaColetarRequisitos = descricaoTelaColetarRequisitos;
    }

    public String getDescricaoTelaDefinirEscopo() {
        return descricaoTelaDefinirEscopo;
    }

    public void setDescricaoTelaDefinirEscopo(String descricaoTelaDefinirEscopo) {
        this.descricaoTelaDefinirEscopo = descricaoTelaDefinirEscopo;
    }

    public String getDescricaoTelaCriarEAP() {
        return descricaoTelaCriarEAP;
    }

    public void setDescricaoTelaCriarEAP(String descricaoTelaCriarEAP) {
        this.descricaoTelaCriarEAP = descricaoTelaCriarEAP;
    }

    @Override
    public String toString() {
        return nomeTurma;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idTurma;
        hash = 89 * hash + Objects.hashCode(this.nomeTurma);
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
        if (this.idTurma != other.idTurma) {
            return false;
        }
        if (!Objects.equals(this.nomeTurma, other.nomeTurma)) {
            return false;
        }
        return true;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public List<Usuario> getAcademicos() {
        return academicos;
    }

    public void setAcademicos(List<Usuario> academicos) {
        this.academicos = academicos;
    }

    public List<Projeto> getProjeto() {
        return projeto;
    }

    public void setProjeto(List<Projeto> projeto) {
        this.projeto = projeto;
    }



}
