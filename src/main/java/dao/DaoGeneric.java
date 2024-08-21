package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import posjavahibernate.HibernateUtil;

public class DaoGeneric<E>{

    private EntityManager em = HibernateUtil.getEntityManager();

    public void salvar(E entidade){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(entidade);
        tx.commit();
    }

    public E pesquisar(E entidade){
        Object id = HibernateUtil.getPrimaryKey(entidade);

        E e = (E) em.find(entidade.getClass(), id);
        return e;
    }
}
