package estudo.testeunitario.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static estudo.testeunitario.utils.DataUtils.isMesmaData;
import static estudo.testeunitario.utils.DataUtils.obterDataComDiferencaDias;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import estudo.testeunitario.entidades.Filme;
import estudo.testeunitario.entidades.Locacao;
import estudo.testeunitario.entidades.Usuario;
import estudo.testeunitario.exceptions.FilmeSemEstoqueException;
import estudo.testeunitario.exceptions.LocadoraException;
import estudo.testeunitario.utils.DataUtils;

/**
 * Unit test for simple App.
 */
public class LocacaoServiceTest {

	private LocacaoService service;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {

		service = new LocacaoService();

	}

	@Test
	public void testeLocacao() throws Exception {

		// cenario
		Usuario usuario = new Usuario("Locador 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacaoFilmeSemEstoque() throws Exception {

		// cenario
		Usuario usuario = new Usuario("Locador 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		// acao
		service.alugarFilme(usuario, filmes);

	}

	@Test
	public void testeLocacaoUsuarioVazio() throws FilmeSemEstoqueException {

		// cenario
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		// acao
		try {

			service.alugarFilme(null, filmes);
			fail();

		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	@Test
	public void testeLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {

		// cenario
		Usuario usuario = new Usuario("Locador 1");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		// acao
		service.alugarFilme(usuario, null);

	}

	// @Test
	// public void testeLocacaoFilmeSemEstoqueTryCatch() {

	// // cenario
	// LocacaoService service = new LocacaoService();
	// Usuario usuario = new Usuario("Locador 1");
	// Filme filme = new Filme("Filme 1", 0, 5.0);

	// // acao
	// try {
	// service.alugarFilme(usuario, filme);
	// fail("Deveria ter lançado uma exceção");
	// } catch (Exception e) {
	// assertThat(e.getMessage(), is("Filme sem estoque"));
	// }

	// }

	// @Test
	// public void testeLocacaoFilmeSemEstoqueRule() throws Exception {

	// // cenario
	// LocacaoService service = new LocacaoService();
	// Usuario usuario = new Usuario("Locador 1");
	// Filme filme = new Filme("Filme 1", 0, 5.0);
	// exception.expect(Exception.class);
	// exception.expectMessage("Filme sem estoque");

	// // acao
	// service.alugarFilme(usuario, filme);

	// }

}
