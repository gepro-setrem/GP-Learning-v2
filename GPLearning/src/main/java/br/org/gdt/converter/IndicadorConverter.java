package br.org.gdt.converter;

import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.model.Indicador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("indicadorConverter")
@ManagedBean
public class IndicadorConverter implements Converter {

    @ManagedProperty("#{indicadorBLL}")
    private IndicadorBLL indicadorBLL;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        Indicador indicador = indicadorBLL.findById(id);
        return indicador;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "" + ((Indicador) value).getId();
    }

    public IndicadorBLL getIndicadorBLL() {
        return indicadorBLL;
    }

    public void setIndicadorBLL(IndicadorBLL indicadorBLL) {
        this.indicadorBLL = indicadorBLL;
    }

}
