package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class SecaoTipoEsmalte extends SecaoCatalogo<TipoEsmalte> {

	public SecaoTipoEsmalte(Catalogo catalogo) {
		super(catalogo);
	}

	@Override
	public void inserirItem(TipoEsmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("NM_TIPO_ESMALTE", item.getNome());

		item.setId(getCatalogo().getBaseDados().insert("TIPO_ESMALTE", null,
				colunasValores));
	}

	@Override
	public List<TipoEsmalte> listarItens() {
		List<TipoEsmalte> itens = new ArrayList<TipoEsmalte>();

		Cursor cursor = getCatalogo().getBaseDados().query("TIPO_ESMALTE",
				null, null, null, null, null, null);

		while (cursor.moveToNext())
			itens.add(new TipoEsmalte(cursor.getLong(0), cursor.getString(1)));

		return itens;
	}

	@Override
	public List<TipoEsmalte> listarItens(int quantidade, TipoEsmalte itemInicio) {
		String selecao = null;
		String[] argumentosSelecao = null;

		if (itemInicio != null) {
			selecao = "NM_TIPO_ESMALTE > ?";
			argumentosSelecao = new String[] { itemInicio.getNome() };
		}

		List<TipoEsmalte> itens = new ArrayList<TipoEsmalte>();

		Cursor cursor = getCatalogo().getBaseDados().query("TIPO_ESMALTE",
				null, selecao, argumentosSelecao, null, "NM_TIPO_ESMALTE",
				String.valueOf(quantidade));

		while (cursor.moveToNext())
			itens.add(new TipoEsmalte(cursor.getLong(0), cursor.getString(1)));

		return itens;
	}

	@Override
	public TipoEsmalte obterItemPorId(Long itemId) {
		Cursor cursor = getCatalogo().getBaseDados().query("TIPO_ESMALTE",
				null, "CD_TIPO_ESMALTE = ?",
				new String[] { String.valueOf(itemId) }, null, null, null);

		if (cursor.moveToNext())
			return (new TipoEsmalte(cursor.getLong(0), cursor.getString(1)));

		return null;
	}

	@Override
	public void atualizarItem(TipoEsmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("NM_TIPO_ESMALTE", item.getNome());

		getCatalogo().getBaseDados().update("TIPO_ESMALTE", colunasValores,
				"CD_TIPO_ESMALTE = ?",
				new String[] { String.valueOf(item.getId()) });
	}

	@Override
	public void excluirItem(TipoEsmalte item) {
		getCatalogo().getBaseDados().delete("TIPO_ESMALTE",
				"CD_TIPO_ESMALTE = ?",
				new String[] { String.valueOf(item.getId()) });
	}
}
