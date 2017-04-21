package br.org.gdt.beans;

import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.PessoaBLL;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class TurmaBean {

    private Turma turma;
    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;
    private DataModel turmas;

    private Pessoa usuario;
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    public TurmaBean() {
    }

    public Pessoa getUsuario() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        usuario = pessoaBLL.findByEmail(email);
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public DataModel getTurmas() {
        usuario = getUsuario();
        turmas = new ListDataModel(turmaBLL.findbyProfessor(usuario));
        return turmas;
    }

    public void setTurmas(DataModel turmas) {
        this.turmas = turmas;
    }

    public String salvar() {
        usuario = getUsuario();
        if (!turma.getNome().equals("") && turma.getAno() > 0) {
            turma.setProfessor(usuario);
            if (turma.getId() > 0) {
                turmaBLL.update(turma);
            } else {
                turmaBLL.insert(turma);
            }
            return "turmalst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher o nome e ano da turma!"));
            return "turmafrm";
        }
    }

    public String editar() {
        turma = (Turma) turmas.getRowData();
        turma = turmaBLL.findById(turma.getId());
        return "turmafrm";
    }

    public String duplicar() {
        Turma copia = new Turma();
        turma = (Turma) turmas.getRowData();
        copia.setNome(turma.getNome() + " CÓPIA");
        copia.setAno(turma.getAno());
//        copia.setDescricaoTelaColetarRequisitos(turma.getDescricaoTelaColetarRequisitos());
//        copia.setDescricaoTelaCriarEAP(turma.getDescricaoTelaCriarEAP());
//        copia.setDescricaoTelaCriarPlanoGerenciamentoEscopo(turma.getDescricaoTelaCriarPlanoGerenciamentoEscopo());
//        copia.setDescricaoTelaCriarPlanoGerenciamentoProjeto(turma.getDescricaoTelaCriarPlanoGerenciamentoProjeto());
//        copia.setDescricaoTelaDefinirEscopo(turma.getDescricaoTelaDefinirEscopo());
//        copia.setDescricaoTelaGrupoProcessosIniciacao(turma.getDescricaoTelaGrupoProcessosIniciacao());
//        copia.setDescricaoTelaGrupoProcessosPlanejamento(turma.getDescricaoTelaGrupoProcessosPlanejamento());
//        copia.setDescricaoTelaInicialGerenciamentoProjetos(turma.getDescricaoTelaInicialGerenciamentoProjetos());
//        copia.setDescricaoTelaPartesInteressadas(turma.getDescricaoTelaPartesInteressadas());
//        copia.setDescricaoTelaTermoAberturaCronogramaMarcos(turma.getDescricaoTelaTermoAberturaCronogramaMarcos());
//        copia.setDescricaoTelaTermoAberturaDescricao(turma.getDescricaoTelaTermoAberturaDescricao());
//        copia.setDescricaoTelaTermoAberturaJustificativa(turma.getDescricaoTelaTermoAberturaJustificativa());
//        copia.setDescricaoTelaTermoAberturaPremissas(turma.getDescricaoTelaTermoAberturaPremissas());
//        copia.setDescricaoTelaTermoAberturaRequisitos(turma.getDescricaoTelaTermoAberturaRequisitos());
//        copia.setDescricaoTelaTermoAberturaRestricoes(turma.getDescricaoTelaTermoAberturaRestricoes());
//        copia.setProfessor(turma.getProfessor());
        turmaBLL.insert(copia);
        return "turmalst";
    }

    public String excluir() throws Exception {
        try {
            turma = (Turma) turmas.getRowData();
            turmaBLL.delete(turma.getId());
            turmas = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "turmalst";
    }

    public String novo() {
        turma = new Turma();
        return "turmafrm";
    }

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

}
