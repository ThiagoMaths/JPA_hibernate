package posjavahibernate;

import dao.DaoGeneric;
import model.UsuarioPessoa;
import org.junit.Test;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setNome("fulano");
		pessoa.setEmail("fulano@email.com");
		pessoa.setSenha("123456");
		pessoa.setLogin("teste");
		pessoa.setIdade(35);
		pessoa.setSobrenome("Pereira");

		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar(){
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);
	}


}
