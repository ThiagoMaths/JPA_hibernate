package posjavahibernate;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;
import org.junit.Test;

import java.util.List;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setNome("Joao 2 ");
		pessoa.setSobrenome("Pedro");
		pessoa.setIdade(22);
		pessoa.setEmail("teste@email.com");
		pessoa.setLogin("Joao_pedro");
		pessoa.setSenha("1234");

		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setId(152L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);
	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		pessoa.setNome("Pedro");
		pessoa.setIdade(99);

		pessoa = daoGeneric.updateMerge(pessoa);
		System.out.println(pessoa);

	}

	@Test
	public void testeDelete() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(152L, UsuarioPessoa.class);

		daoGeneric.excluir(pessoa);
	}

	@Test
	public void testeConsulta() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {

			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------");
		}
	}

	@Test
	public void testQueryList() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEm().createQuery(" from UsuarioPessoa  where nome= 'Henrique' ")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {

			System.out.println(usuarioPessoa);

		}

	}

	@Test
	public void testQueryListMaxResult() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEm().createQuery(" from UsuarioPessoa  order by sobrenome ")
				.setMaxResults(4).getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {

			System.out.println(usuarioPessoa);

		}
	}

	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEm().createQuery(" from UsuarioPessoa where nome = :nome")
				.setParameter("nome", "Renato").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {

			System.out.println(usuarioPessoa);

		}
	}

	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		Long somaIdade = (Long) daoGeneric.getEm().createQuery(" select sum(u.idade) from UsuarioPessoa u ")
				.getSingleResult();

		System.out.println("Soma de todas as idades é --> " + somaIdade);

	}

	
	@Test
	public void testeNamedQuery() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEm().createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			
		}
	}
	
	@Test
	public void testeNamedQueryBuscaNome() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEm().createNamedQuery("UsuarioPessoa.buscaPorNome").setParameter("nome", "Henrique").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			
		}
	}
	
	@Test
	public void TesteGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(52L, UsuarioPessoa.class);

		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("2133908705");
		telefoneUser.setUsuarioPessoa(pessoa);

		daoGeneric.salvar(telefoneUser);

	}

	@Test
	public void TesteConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(52L, UsuarioPessoa.class);

		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {

			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("------------------------------------------------");

		}
	}
}
