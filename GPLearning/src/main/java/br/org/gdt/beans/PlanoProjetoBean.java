package br.org.gdt.beans;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TermoAberturaBLL;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Comentario;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Recurso;
import br.org.gdt.model.Tarefa;
import br.org.gdt.model.TermoAbertura;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class PlanoProjetoBean {

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    @ManagedProperty("#{termoAberturaBLL}")
    private TermoAberturaBLL termoAberturaBLL;
    private TermoAbertura termoabertura = new TermoAbertura();

    @ManagedProperty("#{etapaBLL}")
    private EtapaBLL etapaBLL;
    private List<Etapa> etapas;

    private String htmlEAP;
    private String htmlCronograma;

    public PlanoProjetoBean() {
    }

    public void criarPlanoGerenciamentoProjeto() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        List<Projeto> projetoslist = new ArrayList<>();
        projeto = projetoBLL.findbyRelatorio(projeto);
        projetoslist.add(projeto);
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(projetoslist, false);
        projeto = (Projeto) projetoBLL.findById(projeto.getId());

//        InputStream is = null;
//        if (this.projeto.getEAP() != null) {
//            is = new ByteArrayInputStream(this.projeto.getEAP());
//        }
        Map parametros = new HashMap();
        //parametros.put("EAP", is);
        try {
            JasperPrint jpr = JasperFillManager.fillReport(context.getExternalContext().getRealPath("") + "/resources/PlanoGerenciamentoProjeto.jasper", parametros, jrs);

//            JasperExportManager.exportReportToPdfFile(jpr,
//                    context.getExternalContext().getRealPath("") + "/resources/TermoAbertura" + projeto.getNomeProjeto() + ".pdf");
//            System.out.println("Passou pela criação do PDF");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jpr);
//            exporter.setParameters(JRExporterParameter.OUTPUT_STREAM, baos);
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");//inline;attachment;
            response.setHeader("Content-disposition", "inline; filename=\"PlanoGerenciamentoProjeto.pdf\"");
            //response.setContentLength(bytes.length);

            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jpr, servletOutputStream);
            //servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(PlanoProjetoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String criarPlano() {
        htmlEAP = null;
        htmlCronograma = null;
        projeto = projetoBLL.findProjetoCompleto(projeto.getId());
        termoabertura = termoAberturaBLL.findByProjetoCompleto(projeto);
        return "plano";
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        htmlEAP = null;
        htmlCronograma = null;
        this.projeto = projetoBLL.findProjetoCompleto(projeto.getId());
        termoabertura = termoAberturaBLL.findByProjetoCompleto(this.projeto);
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
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

    public void setHtmlEAP(String htmlEAP) {
        this.htmlEAP = htmlEAP;
    }

    public void setHtmlCronograma(String htmlCronograma) {
        this.htmlCronograma = htmlCronograma;
    }

    private String getEAPNode(EAP eap, String number, int vez) {
        String html = "";
        if (eap != null) {
            html += "<li class=\"eap_item " + (vez == 2 ? "eap_column" : "") + "\"><div class=\"eap\">";
            html += "<div class=\"eap_header\"><a class=\"btn btn-xs eapIcon eap_number\">" + number + "</a></div>";//<a class=\"btn btn-xs eapIcon eapDetail\"><i class=\"fa-list-ul\"></i></a>
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

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<Etapa> getEtapas() {
        if (etapas == null) {
            if (projeto != null && projeto.getTurma() != null && projeto.getTurma().getId() > 0) {
                etapas = etapaBLL.findbyTurma(projeto.getTurma());
            }
        }
        return etapas;
    }

    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }

    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }

}
