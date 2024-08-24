package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import posjavahibernate.HibernateUtil;

import java.util.List;

public class DaoGeneric<E>{

	private EntityManager em = HibernateUtil.getEntityManager();

	public void salvar(E entidade) {
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(entidade);
		tx.commit();
		em.close();

	}

	public E updateMerge(E entidade) { // Salvar ou atualizar
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		E entidadeSalva = em.merge(entidade);
		tx.commit();
		em.close();

		return entidadeSalva;
	}

	public E pesquisar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);

		E e = (E) em.find(entidade.getClass(), id);
		return e;
	}

	public E pesquisar(Long id, Class<E> entidade) {

		E e = (E) em.find(entidade, id);
		return e;
	}

	public void excluir(E entidade) {
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Object id = HibernateUtil.getPrimaryKey(entidade);

			if (id != null) {
				em.createQuery("delete from " + entidade.getClass().getName() + " where id = " + id).executeUpdate();

				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<E> listar(Class<E> entidade) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		List<E> lista = em.createQuery(" from " + entidade.getName()).getResultList();

		tx.commit();

		return lista;
	}

	public EntityManager getEm() {
		return em;
	}
}
