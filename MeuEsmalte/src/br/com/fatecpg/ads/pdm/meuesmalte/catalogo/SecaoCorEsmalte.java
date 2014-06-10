package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class SecaoCorEsmalte extends SecaoCatalogo<CorEsmalte> {

	public SecaoCorEsmalte(Catalogo catalogo) {
		super(catalogo);
	}

	@Override
	public void inserirItem(CorEsmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("CD_COR_ESMALTE", item.getId());
		colunasValores.put("NM_COR_ESMALTE", item.getNome());

		item.setId(getCatalogo().getBaseDados()
				.insert("COR_ESMALTE", null, colunasValores));
	}

	@Override
	public List<CorEsmalte> listarItens() {
		List<CorEsmalte> itens = new ArrayList<CorEsmalte>();

		Cursor cursor = getCatalogo().getBaseDados().query("COR_ESMALTE", null,
				null, null, null, null, null);

		while (cursor.moveToNext())
			itens.add(new CorEsmalte(cursor.getLong(0), cursor.getString(1)));

		return itens;
	}

	@Override
	public List<CorEsmalte> listarItens(int quantidade, CorEsmalte itemInicio) {
		String selecao = null;
		String[] argumentosSelecao = null;

		if (itemInicio != null) {
			selecao = "NM_COR_ESMALTE > ?";
			argumentosSelecao = new String[] { itemInicio.getNome() };
		}

		List<CorEsmalte> itens = new ArrayList<CorEsmalte>();

		Cursor cursor = getCatalogo().getBaseDados().query("COR_ESMALTE", null,
				selecao, argumentosSelecao, null, "NM_COR_ESMALTE",
				String.valueOf(quantidade));

		while (cursor.moveToNext())
			itens.add(new CorEsmalte(cursor.getLong(0), cursor.getString(1)));

		return itens;
	}

	@Override
	public CorEsmalte obterItemPorId(Long itemId) {
		Cursor cursor = getCatalogo().getBaseDados().query("COR_ESMALTE", null,
				"CD_COR_ESMALTE = ?", new String[] { String.valueOf(itemId) },
				null, null, null);

		if (cursor.moveToNext())
			return (new CorEsmalte(cursor.getLong(0), cursor.getString(1)));

		return null;
	}

	@Override
	public void atualizarItem(CorEsmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("NM_COR_ESMALTE", item.getNome());

		getCatalogo().getBaseDados().update("COR_ESMALTE", colunasValores,
				"CD_COR_ESMALTE = ?",
				new String[] { String.valueOf(item.getId()) });
	}

	@Override
	public void excluirItem(CorEsmalte item) {
		getCatalogo().getBaseDados().delete("COR_ESMALTE",
				"CD_COR_ESMALTE = ?",
				new String[] { String.valueOf(item.getId()) });
	}
}
