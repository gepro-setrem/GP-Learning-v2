package br.org.gdt.dao;

import br.org.gdt.model.Turma;
import br.org.gdt.model.TurmaParametro;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("turmaParametroDAO")
public class TurmaParametroDAO extends DAO<TurmaParametro> {

    public TurmaParametroDAO() {
        classe = TurmaParametro.class;
    }

    public TurmaParametro getParametro(int tur_id, String chave) {
        List<TurmaParametro> lsTurmaParametro = entityManager.createQuery("from TurmaParametro where turma.id = :tur_id and chave = :chave")
                .setParameter("tur_id", tur_id).setParameter("chave", chave).getResultList();
        TurmaParametro trp = new TurmaParametro();
        if (lsTurmaParametro.size() > 0) {
            trp = lsTurmaParametro.get(0);
        }
        return trp;
    }

    public List<TurmaParametro> findbyTurma(Turma turma) {
        return entityManager.createQuery("from TurmaParametro where turma = :t")
                .setParameter("t", turma).getResultList();
    }
}
