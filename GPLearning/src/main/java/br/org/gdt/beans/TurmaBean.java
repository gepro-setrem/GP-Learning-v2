package br.org.gdt.beans;

import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.TurmaParametroBLL;
import br.org.gdt.enumerated.TurmaParametroType;
import br.org.gdt.model.TurmaParametro;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    @ManagedProperty("#{turmaParametroBLL}")
    private TurmaParametroBLL turmaParametroBLL;

    private Pessoa usuario;
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private TurmaParametroType[] turmaParametroTypes;

    private List<TurmaParametro> turmaParametros;

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

    public TurmaParametroBLL getTurmaParametroBLL() {
        return turmaParametroBLL;
    }

    public void setTurmaParametroBLL(TurmaParametroBLL turmaParametroBLL) {
        this.turmaParametroBLL = turmaParametroBLL;
    }

    public String getParameter(TurmaParametro parametro) {
        String chave = parametro.toString();
        Pessoa user = getUsuario();
        TurmaParametro trp = turmaParametroBLL.getParametro(user.getTurma(), chave);
        if (trp != null) {
            return trp.getValor();
        } else {
            return "";
        }
    }

    public TurmaParametroType[] getTurmaParametroTypes() {
        return TurmaParametroType.values();
    }

    public void setTurmaParametroTypes(TurmaParametroType[] turmaParametroTypes) {
        this.turmaParametroTypes = turmaParametroTypes;
    }

    public String getTurmaParametroType(String type) {
        TurmaParametroType trType = TurmaParametroType.valueOf(type);
        return turmaParametroBLL.getTurmaParametroType(trType);
    }

    public List<TurmaParametro> getTurmaParametros() {
        if (turma == null) {
            turma = new Turma();
        }
        turmaParametros = turma.getTurmaParametros();
        if (turmaParametros == null) {
            turmaParametros = new ArrayList<>();
        }
        for (TurmaParametroType type : getTurmaParametroTypes()) {
            String chave = type.toString();
            Boolean hasKey = false;
            for (TurmaParametro trp : turmaParametros) {
                if (trp.getChave() == chave) {
                    hasKey = true;
                    break;
                }
            }
            if (!hasKey) {
                TurmaParametro turmaParametro = new TurmaParametro();
                turmaParametro.setChave(chave);
                turmaParametros.add(turmaParametro);
            }
        }
        return turmaParametros;

    }

    public void setTurmaParametros(List<TurmaParametro> turmaParametros) {
        this.turmaParametros = turmaParametros;
    }

    public TurmaParametro setTurmaParametro(TurmaParametroType type) {
        String chave = type.toString();
        if (turma == null) {
            turma = new Turma();
        }
        if (turma.getTurmaParametros() == null) {
            turma.setTurmaParametros(new ArrayList<TurmaParametro>());
        }
        TurmaParametro turmaParametro = new TurmaParametro();
        turmaParametro.setChave(chave);
        for (TurmaParametro trp : turma.getTurmaParametros()) {
            if (trp.getChave() == chave) {
                turmaParametro = trp;
            }
        }
        return turmaParametro;
    }
}
