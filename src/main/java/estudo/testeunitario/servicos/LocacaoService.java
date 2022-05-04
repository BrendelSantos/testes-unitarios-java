package estudo.testeunitario.servicos;

import static estudo.testeunitario.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import estudo.testeunitario.entidades.*;
import estudo.testeunitario.exceptions.FilmeSemEstoqueException;
import estudo.testeunitario.exceptions.LocadoraException;
import estudo.testeunitario.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		
		for (Filme filme : filmes) {

			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
			
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		Double valorTotal = 0d;

		for (Filme filme : filmes) {

			valorTotal += filme.getPrecoLocacao();
		}

		locacao.setValor(valorTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

}