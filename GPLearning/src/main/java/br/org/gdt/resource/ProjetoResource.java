package br.org.gdt.resource;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/projeto")
public class ProjetoResource {

    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private PessoaBLL pessoaBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pro_id}")
    public Projeto getProjeto(@PathParam("pro_id") int pro_id) throws IOException {
        if (pro_id > 0) {
            System.out.println("CHEGOU NO METODO");
            return projetoBLL.findProjetoCompleto(pro_id);
        }
        return null; //new Projeto();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/professor/{pes_id}")
    public List<Projeto> getAllbyProfessor(@PathParam("pes_id") int pes_id) throws IOException {
        if (pes_id > 0) {
            Pessoa professor = pessoaBLL.findById(pes_id);
            if (professor != null) {
                return projetoBLL.findbyProfessor(professor, true);
            }
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/aluno/{pes_id}")
    public List<Projeto> getAllbyAluno(@PathParam("pes_id") int pes_id) throws IOException {
        if (pes_id > 0) {
            Pessoa aluno = pessoaBLL.findById(pes_id);
            if (aluno != null) {
                return projetoBLL.findbyAluno(aluno);
            }
        }
        return null;
    }

    // @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/date")
    // @Consumes(MediaType.APPLICATION_JSON)//
//    @RequestMapping(value = "date", method = RequestMethod.POST)
//    public @ResponseBody
//    List<Projeto> getProjetoDate(@RequestBody LinkedMultiValueMap<String, String> map) throws ParseException, IOException {//@RequestParam("id") int id, @RequestParam("data") String data) throws ParseException {//@RequestParam("pes_id") String id, @RequestParam("data") Date data) {
//        //public List<Projeto> getProjetoDate (Projeto projeto) {
//        // if (filter.Access(servletRequest)) {
//        String data = map.getFirst("data");
//        if (data != null) {
//            Pessoa aluno = pessoaBLL.findById(Integer.parseInt(map.getFirst("pes_id")));
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            return projetoBLL.findProjetoDate(df.parse(data), aluno);
//        }
//        //  }
//        return null;
//    }
}
