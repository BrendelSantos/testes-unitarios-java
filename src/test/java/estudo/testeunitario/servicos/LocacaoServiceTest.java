package estudo.testeunitario.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static estudo.testeunitario.utils.DataUtils.isMesmaData;
import static estudo.testeunitario.utils.DataUtils.obterDataComDiferencaDias;

import org.junit.Test;

import estudo.testeunitario.entidades.Filme;
import estudo.testeunitario.entidades.Locacao;
import estudo.testeunitario.entidades.Usuario;
import estudo.testeunitario.utils.DataUtils;

/**
 * Unit test for simple App.
 */
public class LocacaoServiceTest 
{

    @Test
	public void validacoes() {

		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Locador 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		assertEquals(5.0, locacao.getValor(), 0.01);
        assertThat(locacao.getValor(), is(equalTo(5.0)));
        assertThat(locacao.getValor(), is(not(6.0)));

		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

	}

}
