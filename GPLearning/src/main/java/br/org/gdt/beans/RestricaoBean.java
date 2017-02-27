package br.org.gdt.beans;

import br.org.gdt.model.Restricao;
import br.org.gdt.bll.RestricaoBLL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class RestricaoBean {

    private Restricao restricao = new Restricao();
    @ManagedProperty("#{restricaoBLL}")
    private RestricaoBLL service;
    private DataModel restricoes;

    public RestricaoBean() {
    }

    public Restricao getRestricao() {
        return restricao;
    }

    public void setRestricao(Restricao restricao) {
        this.restricao = restricao;
    }

    public DataModel getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(DataModel restricoes) {
        this.restricoes = restricoes;
    }

    public String salvar() {

        if (restricao.getId() > 0) {
            service.update(restricao);
            //Se for novo registro altere as datas de criação e modificação
        } else {
            //modifica-se as duas datas
            service.insert(restricao);
        }
        return "RestricoesTermoAbertura";
    }

    public String select() {
        restricao = (Restricao) restricoes.getRowData();
        restricao = service.findById(restricao.getId());
        return "RestricoesTermoAbertura";
    }

    public String excluir() {
        restricao = (Restricao) restricoes.getRowData();
        service.delete(restricao.getId());
        restricoes = null;
        return "RestricoesTermoAbertura";
    }

    public String MarcosTermoAbertura() {
        restricao = new Restricao();
        return "RestricoesTermoAbertura";
    }

}
