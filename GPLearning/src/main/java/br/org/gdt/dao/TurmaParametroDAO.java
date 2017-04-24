package br.org.gdt.dao;

import br.org.gdt.model.TurmaParametro;
import org.springframework.stereotype.Repository;

@Repository("turmaParametroDAO")
public class TurmaParametroDAO extends DAO<TurmaParametro> {

    public TurmaParametroDAO() {
        classe = TurmaParametro.class;
    }

    public TurmaParametro getParametro(int tur_id, String chave) {
        return (TurmaParametro) entityManager.createQuery("from TurmaParametro where turma.id = :tur_id and chave = :chave")
                .setParameter("tur_id", tur_id).setParameter("chave", chave).getSingleResult();
    }
}
