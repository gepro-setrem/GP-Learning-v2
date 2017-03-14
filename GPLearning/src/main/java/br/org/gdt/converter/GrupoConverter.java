//package br.org.gdt.converter;
//
//import br.org.gdt.model.Grupo;
//import br.org.gdt.bll.GrupoBLL;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//@FacesConverter("grupoConverter")
//@ManagedBean
//public class GrupoConverter implements Converter {
//
//    @ManagedProperty("#{grupoBLL}")
//    private GrupoBLL bll;
//
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
//        return bll.findById(Integer.parseInt(string));
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
//        return "" + ((Grupo) o).getId();
//    }
//
//    public GrupoBLL getGrupoService() {
//        return bll;
//    }
//
//    public void setGrupoService(GrupoBLL bll) {
//        this.bll = bll;
//    }
//}
