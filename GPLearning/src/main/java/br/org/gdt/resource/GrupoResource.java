//package br.org.gdt.resource;
//
//import br.org.gdt.model.Grupo;
//import br.org.gdt.service.GrupoService;
//import java.util.List;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Path("/grupo")
//public class GrupoResource {
//
//    @Autowired
//    private GrupoService service;
//
//    @GET
//    @Produces("application/json")
//    public List<Grupo> getAll() {
//
//        return service.findAll();
//    }
//}
