package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.List;

public abstract class SecaoCatalogo<T extends ItemCatalogo> {

	private Catalogo catalogo;

	protected SecaoCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public abstract void inserirItem(T item);

	public abstract List<T> listarItens();

	public abstract List<T> listarItens(int quantidade, T itemInicio);

	public abstract T obterItemPorId(Long itemId);

	public abstract void atualizarItem(T item);

	public abstract void excluirItem(T item);
}
