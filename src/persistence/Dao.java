package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Login;
import model.Segurado;
import model.Seguro;

public class Dao<T> {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("segurado");
	EntityManager manager = factory.createEntityManager();
	private Login user;
	private Login key;

	public void create(Object dao) {

		try {

			verificarConexaoAberta();

			manager.getTransaction().begin();
			manager.persist(dao);
			manager.getTransaction().commit();
			System.out.println("Sucesso!");
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	public boolean buscarLogin(String usuario, String senha) {

		user = null;
		key = null;
		boolean res = true;

		verificarConexaoAberta();

		try {

			List userCheck = manager.createQuery("SELECT l from Login l WHERE usuario = :usuario AND senha = :senha")
					.setParameter("usuario", usuario).setParameter("senha", senha).getResultList();

			if (userCheck != null && userCheck.size() == 1) {
				res = true;

			} else {
				res = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			res = false;
		}
		return res;
	}

	public Segurado buscarSegurado(String cpf, String nome) {

		verificarConexaoAberta();

		List userCheck = manager.createQuery("SELECT s from Segurado s WHERE cpf = :cpf AND nome = :nome")
				.setParameter("nome", nome).setParameter("cpf", cpf).getResultList();

		if (userCheck != null && userCheck.size() == 1) {
			return (Segurado) userCheck.get(0);

		} else {
			return null;
		}

	}

	public Segurado buscarSeguradoPorCpf(String cpf) {

		verificarConexaoAberta();

		List userCheck = manager.createQuery("SELECT s from Segurado s WHERE cpf = :cpf").setParameter("cpf", cpf)
				.getResultList();

		if (userCheck != null && userCheck.size() == 1) {
			return (Segurado) userCheck.get(0);

		} else {
			return null;
		}

	}

	private void verificarConexaoAberta() {
		if (!manager.isOpen()) {
			manager = factory.createEntityManager();
		}
	}

	public List<Segurado> listaSegurado() {
		return this.manager.createQuery("SELECT s from Segurado s").getResultList();
	}

	public List<Seguro> listaSeguro() {
		return this.manager.createQuery("SELECT s from Seguro s").getResultList();
	}

	public void remove(Class<T> clazz, Integer id) {
		T t = findById(clazz, id);
		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	public void alterar(T obj) {
		try {
			manager.getTransaction().begin();
			manager.merge(obj);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	public T findById(Class<T> clazz, Integer id) {
		return manager.find(clazz, id);

	}

}
