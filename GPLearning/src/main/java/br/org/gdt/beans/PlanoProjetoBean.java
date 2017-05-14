package br.org.gdt.beans;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Projeto;
import java.io.IOException;
import java.util.ArrayList;
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

}
