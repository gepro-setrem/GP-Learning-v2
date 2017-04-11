package br.org.gdt.converter;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.Pessoa;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("pessoaConverter")
public class PessoaConverter implements Converter {

    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        Pessoa pessoa = pessoaBLL.findById(id);
        return pessoa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "" + ((Pessoa) value).getId();
    }
}
