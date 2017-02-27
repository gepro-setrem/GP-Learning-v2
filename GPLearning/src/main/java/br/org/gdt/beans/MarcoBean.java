package br.org.gdt.beans;

import br.org.gdt.model.Marco;
import br.org.gdt.bll.MarcoBLL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class MarcoBean {

    private Marco marco = new Marco();
    @ManagedProperty("#{marcoBLL}")
    private MarcoBLL service;
    private DataModel marcos;

    public MarcoBean() {
    }

    public Marco getMarco() {
        return marco;
    }

    public void setMarco(Marco marco) {
        this.marco = marco;
    }

    public DataModel getMarcos() {
        return marcos;
    }

    public void setMarcos(DataModel marcos) {
        this.marcos = marcos;
    }

    public String salvar() {
        //Caso já tenha sido criado
        if (marco.getId() > 0) {
            //modifica-se somente a data de modificação
            service.update(marco);
            //Se for novo registro altere as datas de criação e modificação
        } else {
            //modifica-se as duas datas
            service.insert(marco);
        }
        return "MarcosTermoAbertura";
    }

    public String select() {
        marco = (Marco) marcos.getRowData();
        marco = service.findById(marco.getId());
        return "MarcosTermoAbertura";
    }

    public String excluir() {
        marco = (Marco) marcos.getRowData();
        service.delete(marco.getId());
        marcos = null;
        return "MarcosTermoAbertura";
    }

    public String novoMarco() {
        marco = new Marco();
        return "MarcosTermoAbertura";
    }

}
