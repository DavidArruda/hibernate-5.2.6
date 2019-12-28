package posjavamavenhibernate;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.DaoGenerico;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	@Ignore
	public void testeHibernateUtil() {
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setNome("Teste Lista");
		pessoa.setSobrenome("Arruda");
		pessoa.setEmail("lista_@hotmail.com");
		pessoa.setLogin("lista");
		pessoa.setSenha("123");
		pessoa.setIdade(15);

		daoGenerico.salvar(pessoa);
	}

	@Test
	@Ignore
	public void pesquisar() {

		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);
		pessoa = daoGenerico.pesquisar(pessoa);

		System.out.println(pessoa);
	}

	@Test
	@Ignore
	public void pesquisar2() {

		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGenerico.pesquisar2(1L, UsuarioPessoa.class);

		System.out.println(pessoa);
	}

	@Test
	@Ignore
	public void testUpdate() {

		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGenerico.pesquisar2(1L, UsuarioPessoa.class);
		pessoa.setIdade(22);
		pessoa.setNome("David Atualizado 3");

		pessoa = daoGenerico.updateMerge(pessoa);

		System.out.println(pessoa);
	}

	@Test
	@Ignore
	public void testeDelete() {

		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGenerico.pesquisar2(2L, UsuarioPessoa.class);

		daoGenerico.deletar(pessoa);
	}

	@Test
	@Ignore
	public void testeListar() {

		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		List<UsuarioPessoa> lista = daoGenerico.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------");
		}
	}
	
	@Test
	@Ignore
	public void testeQueryList() {
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> lista = daoGenerico.getEntityManager().createQuery("from UsuarioPessoa").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------");
		}
	}
	
	@Test
	@Ignore
	public void testeQueryListMaxResult() { // Determina o máximo de resultados
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> lista = daoGenerico.getEntityManager().
				createQuery("from UsuarioPessoa order by nome")
				.setMaxResults(3)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------");
		}
	}
	
	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void testeQueryListParameter() {
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();

		List<UsuarioPessoa> lista = daoGenerico.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "David")
				.setParameter("sobrenome", "Arruda").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("________________________");
		}

	}
	
	@Test
	@Ignore
	public void testeQuerySomaIdade() {
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGenerico.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();

		System.out.println("Soma das idades é --> " + somaIdade);
	}
	
	@Test
	@Ignore
	public void testeNamedQuery() {
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> usuarioPessoas = daoGenerico.getEntityManager().createNamedQuery("UsuarioPessoa.todos")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : usuarioPessoas) {
			System.out.println(usuarioPessoa);
			System.out.println("---------------");
		}
	}
	
	@Test
	@Ignore
	public void testeNamedQuery2() {
		DaoGenerico<UsuarioPessoa> daoGenerico = new DaoGenerico<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> usuarioPessoas = daoGenerico.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Teste Lista").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : usuarioPessoas) {
			System.out.println(usuarioPessoa);
			System.out.println("---------------");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	@Ignore
	public void testeInsertTelefone() {
		DaoGenerico daoGenerico = new DaoGenerico();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGenerico.pesquisar2(3L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Celular");
		telefoneUser.setNumero("(14) 99644-7743");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGenerico.salvar(telefoneUser);
		
	}
	
	@Test
	public void testeConsultarTelefones() {
		DaoGenerico daoGenerico = new DaoGenerico();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGenerico.pesquisar2(3L, UsuarioPessoa.class);
		
		for (TelefoneUser telefoneUser : pessoa.getTelefoneUsers()) {
			System.out.print(telefoneUser.getNumero() + " ");
			System.out.println(telefoneUser.getTipo());
			System.out.println(telefoneUser.getUsuarioPessoa().getNome());
			System.out.println("---------------");
		}
	}
	
	
}	