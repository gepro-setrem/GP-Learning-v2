package br.org.gdt.beans;

import br.org.gdt.bll.AvaliacaoBLL;
import br.org.gdt.bll.ComentarioBLL;
import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TermoAberturaBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.enumerated.EtapaProjeto;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Comentario;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Recurso;
import br.org.gdt.model.Tarefa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class ProjetoProfessorBean {

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;
    private DataModel projetos;

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;
    private List<Turma> turmas;

    @ManagedProperty("#{etapaBLL}")
    private EtapaBLL etapaBLL;
    private List<Etapa> etapas;

    private EtapaProjeto[] etapaProjetos;

    @ManagedProperty("#{termoAberturaBLL}")
    private TermoAberturaBLL termoAberturaBLL;
    private TermoAbertura termoabertura = new TermoAbertura();

    @ManagedProperty("#{avaliacaoBLL}")
    private AvaliacaoBLL avaliacaoBLL;

    @ManagedProperty("#{comentarioBLL}")
    private ComentarioBLL comentarioBLL;
    private List<Comentario> comentarios;

    private String htmlEAP;
    private String htmlCronograma;

    public ProjetoProfessorBean() {
    }

    public String atualizar() {
        projetos = null;
        return "projetolst";
    }

    public String avaliar() {
        etapas = null;
        htmlEAP = null;
        htmlCronograma = null;
        projeto = (Projeto) projetos.getRowData();
        projeto = projetoBLL.findProjetoCompleto(projeto.getId());
        termoabertura = termoAberturaBLL.findByProjetoCompleto(projeto);
        comentarios = new ArrayList<>();
        return "avaliacao";
    }

    public Projeto getProjeto() {
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

    public DataModel getProjetos() {
        getUsuario();
        if (projetos == null) {
            projetos = new ListDataModel(projetoBLL.findbyProfessor(usuario));
        }
        return projetos;
    }

    public void setProjetos(DataModel projetos) {
        this.projetos = projetos;
    }

    public Pessoa getUsuario() {
        if (usuario == null || usuario.getId() == 0) {
            ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) external.getRequest();
            String email = request.getRemoteUser();
            usuario = pessoaBLL.findbyEmail(email);
        }
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

    public List<Turma> getTurmas() {
        getUsuario();
        if (turmas == null) {
            turmas = turmaBLL.findbyProfessor(usuario);
        }
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public EtapaProjeto[] getEtapaProjetos() {
        this.etapaProjetos = EtapaProjeto.values();
        return this.etapaProjetos;
    }

    public void setEtapaProjetos(EtapaProjeto[] etapaProjetos) {
        this.etapaProjetos = etapaProjetos;
    }

    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }

    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }

    public List<Etapa> getEtapas() {
        if (etapas == null) {
            if (projeto != null && projeto.getTurma() != null && projeto.getTurma().getId() > 0) {
                etapas = etapaBLL.findbyTurma(projeto.getTurma());
                for (Etapa etapa : etapas) {
                    List<Avaliacao> lsAvaliacao = avaliacaoBLL.findbyProjetoEtapa(projeto, etapa);
                    etapa.setAvaliacoes(lsAvaliacao);
                    List<Comentario> lsComentario = comentarioBLL.findbyProjetoEtapa(projeto, etapa, true, false);
                    etapa.setComentarios(lsComentario);
                }
            }
        }
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public TermoAberturaBLL getTermoAberturaBLL() {
        return termoAberturaBLL;
    }

    public void setTermoAberturaBLL(TermoAberturaBLL termoAberturaBLL) {
        this.termoAberturaBLL = termoAberturaBLL;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    private String getEAPNode(EAP eap, String number, int vez) {
        String html = "";
        if (eap != null) {
            html += "<li class=\"eap_item " + (vez == 2 ? "eap_column" : "") + "\"><div class=\"eap\">";
            html += "<div class=\"eap_header\"><a class=\"btn btn-xs eapIcon eap_number\">" + number + "</a><a class=\"btn btn-xs eapIcon eapDetail\"><i class=\"fa-list-ul\"></i></a></div>";
            html += "<div class=\"eap_nome\">" + eap.getNome() + "</div>";
            html += "<div style=\"display: none;\">"
                    + "<input type=\"hidden\" name=\"pai.id\" value=\"" + (eap.getPai() != null ? eap.getPai().getId() : 0) + "\"/>"
                    + "<input type=\"hidden\" name=\"id\" value=\"" + eap.getId() + "\"/>"
                    + "<input type=\"hidden\" name=\"ordem\" value=\"" + eap.getOrdem() + "\"/>"
                    + "<input type=\"hidden\" name=\"nome\" value=\"" + eap.getNome() + "\"/>"
                    + "<input type=\"hidden\" name=\"descricao\" value=\"" + eap.getDescricao() + "\"/>"
                    + "<input type=\"hidden\" name=\"inicio\" value=\"" + FormatDate(eap.getInicio()) + "\"/>"
                    + "<input type=\"hidden\" name=\"termino\" value=\"" + FormatDate(eap.getTermino()) + "\"/>"
                    + "<input type=\"hidden\" name=\"valor\" value=\"" + eap.getValor() + "\"/>"
                    + "</div>";
            html += "</div><ul class=\"eap_pai\">";
            vez++;
            if (eap.getEaps() != null) {
                int i = 1;
                for (EAP child : eap.getEaps()) {
                    html += getEAPNode(child, number + "." + i, vez);
                    i++;
                }
            }
            html += "</ul></li>";
        }
        return html;
    }

    public String getHtmlEAP() {
        if (htmlEAP == null || htmlEAP.isEmpty()) {
            if (projeto != null && projeto.getEaps() != null && projeto.getEaps().size() > 0) {
                EAP eap = projeto.getEaps().get(0);
                String html = getEAPNode(eap, "1", 1);
                if (html == null || html.isEmpty()) {
                    html = "Nenhum registro encontrado!";
                }
                html = "<ul class=\"eap_pai\">" + html + "</ul>";
                htmlEAP = html;
            }
        }
        return htmlEAP;
    }

    public String getHtmlCronograma() {
        if (htmlCronograma == null || htmlCronograma.isEmpty()) {
            if (projeto != null && projeto.getEaps() != null && projeto.getEaps().size() > 0) {
                EAP eap = projeto.getEaps().get(0);
                String html = getTarefaNode(eap, null, "1", "");
                if (html == null || html.isEmpty()) {
                    html = "<tr><td colspan=\"5\">Nenhum registro encontrado!</td></tr>";
                }
                htmlCronograma = html;
            }
        }
        return htmlCronograma;
    }

    private String FormatDate(Date date) {
        String d = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            d = sdf.format(date);
        }
        return d;
    }

    private String getTarefaNode(EAP eap, Tarefa tarefa, String number, String html) {
        String marco = "<i class=\"fa-check\"></i>";
        if (eap != null) {
            html += "<tr class=\"tarefa eap\"><td class=\"marco\"></td>";
            html += "<td><span class=\"indice\">" + number + "</span><span class=\"nome\">" + eap.getNome() + "</span></td>";
            html += "<td class=\"inicio text-center\">" + FormatDate(eap.getInicio()) + "</td>";
            html += "<td class=\"termino text-center\">" + FormatDate(eap.getTermino()) + "</td>";
            html += "<td class=\"recursos\"></td>";
            html += "</tr>";
            if (eap.getEaps() != null) {
                int i = 1;
                for (EAP ea : eap.getEaps()) {
                    html = getTarefaNode(ea, null, number + "." + i, html);
                    i++;
                }
            }
            if (eap.getTarefas() != null) {
                int i = 1;
                for (Tarefa tar : eap.getTarefas()) {
                    html = getTarefaNode(null, tar, number + "." + i, html);
                    i++;
                }
            }
        }
        if (tarefa != null) {
            html += "<tr class=\"tarefa\"><td class=\"marco\">" + (tarefa.getMarco() ? marco : "") + "</td>";
            html += "<td><span class=\"indice\">" + number + "</span><span class=\"nome\">" + tarefa.getNome() + "</span></td>";
            html += "<td class=\"inicio text-center\">" + FormatDate(tarefa.getInicio()) + "</td>";
            html += "<td class=\"termino text-center\">" + FormatDate(tarefa.getTermino()) + "</td>";
            String recursos = "";
            if (tarefa.getRecursos() != null) {
                for (Recurso rec : tarefa.getRecursos()) {
                    recursos += rec.getNome() + ", ";
                }
            }
            html += "<td class=\"recursos\">" + recursos + "</td>";
            html += "</tr>";
            if (tarefa.getTarefas() != null) {
                int i = 1;
                for (Tarefa tar : tarefa.getTarefas()) {
                    html = getTarefaNode(null, tar, number + "." + i, html);
                    i++;
                }
            }
        }
        return html;
    }

    public AvaliacaoBLL getAvaliacaoBLL() {
        return avaliacaoBLL;
    }

    public void setAvaliacaoBLL(AvaliacaoBLL avaliacaoBLL) {
        this.avaliacaoBLL = avaliacaoBLL;
    }

    public Avaliacao getAvaliacao(Etapa etapa, Indicador indicador) {
        Avaliacao ava = new Avaliacao();
        ava.setEtapa(etapa);
        ava.setIndicador(indicador);
        ava.setProjeto(projeto);
        ava.setValor(0);
        if (etapa != null && indicador != null && etapa.getAvaliacoes() != null) {
            for (Avaliacao avalia : etapa.getAvaliacoes()) {
                if (avalia.getIndicador() != null && avalia.getIndicador().getId() == indicador.getId()) {
                    ava = avalia;
                    break;
                }
            }
        }
        if (ava.getId() == 0 && etapa != null) {
            List<Avaliacao> lsAvaliacao = etapa.getAvaliacoes();
            lsAvaliacao.add(ava);
            etapa.setAvaliacoes(lsAvaliacao);
        }
        return ava;
    }

    public void onrate(Avaliacao avaliacao) {
        if (avaliacao != null && avaliacao.getEtapa() != null && avaliacao.getIndicador() != null) {
            Avaliacao ava = avaliacaoBLL.findbyProjetoEtapaIndicador(projeto, avaliacao.getEtapa(), avaliacao.getIndicador());
            if (ava != null) {
                ava.setValor(avaliacao.getValor());
                avaliacaoBLL.update(ava);
            } else {
                avaliacao.setProjeto(projeto);
                avaliacao.setCriacao(new Date());
                avaliacao.setId(0);
                avaliacaoBLL.insert(avaliacao);
            }
        }
    }

    public void oncancel(Avaliacao avaliacao) {
        if (avaliacao != null && avaliacao.getId() > 0) {
            avaliacaoBLL.delete(avaliacao.getId());
        }
    }

    public ComentarioBLL getComentarioBLL() {
        return comentarioBLL;
    }

    public void setComentarioBLL(ComentarioBLL comentarioBLL) {
        this.comentarioBLL = comentarioBLL;
    }

    public void salvarComentario(Etapa etapa) {
        if (comentarios != null) {
            for (Comentario comentario : comentarios) {
                if (comentario.getEtapa().equals(etapa)) {
                    if (comentario.getDescricao() != null && !comentario.getDescricao().isEmpty()) {
                        comentario.setRemetente(getUsuario());
                        comentario.setProjeto(projeto);
                        comentario.setCriacao(new Date());
                        comentarioBLL.insert(comentario);
                        atualizarComentarios(etapa);

                        comentario.setDescricao("");
                        comentario.setId(0);
                    }
                }
            }
        }
    }

    public void excluirComentario(Comentario comentario, Etapa etapa) {
        if (comentario.getId() > 0) {
            comentarioBLL.delete(comentario.getId());
        }
        atualizarComentarios(etapa);
    }

    public void atualizarComentarios(Etapa etapa) {
        if (etapa != null) {
            List<Comentario> lsComentario = comentarioBLL.findbyProjetoEtapa(projeto, etapa, true, false);
            etapa.setComentarios(lsComentario);
        }
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario addComentario(Etapa etapa) {
        if (comentarios == null) {
            comentarios = new ArrayList<>();
        }
        Comentario comentario = new Comentario();
        boolean exist = false;
        for (Comentario coment : comentarios) {
            if (coment.getEtapa().equals(etapa)) {
                comentario = coment;
                exist = true;
                break;
            }
        }
        if (!exist) {
            comentario.setEtapa(etapa);
            comentario.setProjeto(projeto);
            comentarios.add(comentario);
        }
        return comentario;
    }
}
