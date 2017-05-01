package br.org.gdt.converter;

import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.model.Turma;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("turmaConverter")
@ManagedBean
public class TurmaConverter implements Converter {

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        Turma turma = turmaBLL.findById(id);
        return turma;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "" + ((Turma) value).getId();
    }

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

}
