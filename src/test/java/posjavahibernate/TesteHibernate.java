package posjavahibernate;

import dao.DaoGeneric;
import model.UsuarioPessoa;
import org.junit.Test;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setNome("joao ");
		pessoa.setEmail("pedroSAM_PAIO@email.com");
		pessoa.setSenha("22565596");
		pessoa.setLogin("pedrosamp");
		pessoa.setIdade(2);
		pessoa.setSobrenome("sampaio");

		daoGeneric.salvar(pessoa);
		System.out.println("Salvo com  sucesso!");
		System.out.println(pessoa);

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
