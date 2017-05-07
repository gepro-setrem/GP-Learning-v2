package br.org.gdt.bll;

import br.org.gdt.dao.RequisitoTermoAberturaDAO;
import br.org.gdt.model.RequisitoTermoAbertura;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requisitoTermoAberturaBLL")
public class RequisitoTermoAberturaBLL extends BLL<RequisitoTermoAbertura> {
    
    @Autowired
    private RequisitoTermoAberturaDAO dao;
    
    public List<RequisitoTermoAbertura> findbyTermoAbertura(TermoAbertura termoabertura) {
        List<RequisitoTermoAbertura> lsAberturas = dao.TermoAbertura(termoabertura);
        if (lsAberturas != null) {
            for (RequisitoTermoAbertura rta : lsAberturas) {
                rta.setTermoabertura(null);
            }
        }
        return lsAberturas;
    }
}
