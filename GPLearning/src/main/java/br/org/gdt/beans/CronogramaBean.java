package br.org.gdt.beans;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TarefaBLL;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Tarefa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

@ManagedBean
@SessionScoped
public class CronogramaBean {

    private Tarefa tarefa;
    @ManagedProperty("#{tarefaBLL}")
    private TarefaBLL tarefaBLL;
    private DataModel tarefas;

    private Projeto projeto;
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    public CronogramaBean() {
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public TarefaBLL getTarefaBLL() {
        return tarefaBLL;
    }

    public void setTarefaBLL(TarefaBLL tarefaBLL) {
        this.tarefaBLL = tarefaBLL;
    }

    public Projeto getProjeto() {
        if (projeto == null) {
            projeto = projetoBLL.findAll().get(0);
        }
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }
}
