package br.org.gdt.converter;

import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.IndicadorEtapa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("indicadorEtapaConverter")
@ManagedBean
public class IndicadorEtapaConverter implements Converter {

    @ManagedProperty("#{indicadorBLL}")
    private IndicadorBLL indicadorBLL;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        Indicador indicador = indicadorBLL.findById(id);
        IndicadorEtapa indicadorEtapa = new IndicadorEtapa();
        indicadorEtapa.setIndicador(indicador);
        return indicadorEtapa;
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
