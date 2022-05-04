package estudo.testeunitario.servicos;

import static estudo.testeunitario.utils.DataUtils.adicionarDias;

import java.util.Date;

import estudo.testeunitario.entidades.*;
import estudo.testeunitario.exceptions.FilmeSemEstoqueException;
import estudo.testeunitario.exceptions.LocadoraException;
import estudo.testeunitario.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {
		
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if (filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		
		if (filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException();
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

}