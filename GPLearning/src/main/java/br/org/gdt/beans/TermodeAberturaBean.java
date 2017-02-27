package br.org.gdt.beans;

import br.org.gdt.model.TermoAbertura;
import br.org.gdt.bll.TermoAberturaBLL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

@ManagedBean
@SessionScoped
public class TermodeAberturaBean {

    private TermoAbertura termoabertura = new TermoAbertura();
    @ManagedProperty("#{termoAberturaBLL}")
    private TermoAberturaBLL service;
    private DataModel termosaberturas;

    public TermodeAberturaBean() {
    }

    public TermoAbertura getTermodeabertura() {
        return termoabertura;
    }

    public void setTermodeabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public DataModel getTermosdeabertura() {
        return termosaberturas;
    }

    public void setTermosdeabertura(DataModel termosaberturas) {
        this.termosaberturas = termosaberturas;
    }

    public String DescreverProjeto() {
        service.update(termoabertura);
        return "PremissasTermoAbertura";
    }

    public String select() {
        termoabertura = (TermoAbertura) termosaberturas.getRowData();
        termoabertura = service.findById(termoabertura.getId());
        return "manuttermosaberturas";
    }

}
