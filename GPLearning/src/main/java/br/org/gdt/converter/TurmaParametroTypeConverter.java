package br.org.gdt.converter;

import br.org.gdt.enumerated.TurmaParametroType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("turmaParametroTypeConverter")
public class TurmaParametroTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return TurmaParametroType.valueOf(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof TurmaParametroType) {
            return ((TurmaParametroType) value).name();
        }
        return null;
    }
}
