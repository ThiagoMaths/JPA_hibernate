package posjavahibernate;

import dao.DaoGeneric;
import model.UsuarioPessoa;
import org.junit.Test;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setNome("Pessoa");
		pessoa.setEmail("pessoa@email.com");
		pessoa.setSenha("123456");
		pessoa.setLogin("teste");
		pessoa.setIdade(40);
		pessoa.setSobrenome("Silva");

		daoGeneric.salvar(pessoa);
	}

}
