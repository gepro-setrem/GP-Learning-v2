package br.org.gdt.beans;

import br.org.gdt.model.Premissa;
import br.org.gdt.bll.PremissaBLL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class PremissaBean {

    private Premissa premissa = new Premissa();
    @ManagedProperty("#{premissaBLL}")
    private PremissaBLL service;
    private DataModel premissas;

    public Premissa getPremissa() {
        return premissa;
    }

    public void setPremissa(Premissa premissa) {
        this.premissa = premissa;
    }

    public DataModel getPremissas() {
        return premissas;
    }

    public void setPremissas(DataModel premissas) {
        this.premissas = premissas;
    }

    public String salvar() {
        //Caso já tenha sido criado
        if (premissa.getId() > 0) {
            //modifica-se somente a data de modificação
            service.update(premissa);
            //Se for novo registro altere as datas de criação e modificação
        } else {
            //modifica-se as duas datas
            service.insert(premissa);
        }
        return "PremissasTermoAbertura";
    }

    public String select() {
        premissa = (Premissa) premissas.getRowData();
        premissa = service.findById(premissa.getId());
        System.out.println("Teste premissa é: " + premissa);
        return "PremissasTermoAbertura";
    }

    public String excluir() {
        premissa = (Premissa) premissas.getRowData();
        service.delete(premissa.getId());
        premissas = null;
        return "PremissasTermoAbertura";
    }

    public String novaPremissa() {
        premissa = new Premissa();
        return "PremissasTermoAbertura";
    }

}
