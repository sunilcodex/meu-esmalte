package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;


public class SecaoMarcaEsmalte extends SecaoCatalogo<MarcaEsmalte> {

	public SecaoMarcaEsmalte(Catalogo catalogo) {
		super(catalogo);
	}

	@Override
	public void inserirItem(MarcaEsmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("NM_MARCA_ESMALTE", item.getNome());
		
		item.setId(getCatalogo().getBaseDados().insert("MARCA_ESMALTE", null, colunasValores));
	}

	@Override
	public List<MarcaEsmalte> listarItens() {
		List<MarcaEsmalte> itens = new ArrayList<MarcaEsmalte>();

		Cursor cursor = getCatalogo().getBaseDados().query("MARCA_ESMALTE", null, null, null, null, null, null);
		
		while (cursor.moveToNext())
			itens.add(new MarcaEsmalte(cursor.getLong(0), cursor.getString(1)));
		
		return itens;
	}

	@Override
	public List<MarcaEsmalte> listarItens(int quantidade, MarcaEsmalte itemInicio) {
		String selecao = null;
		String[] argumentosSelecao = null;

		if (itemInicio != null) {
			selecao = "NM_MARCA_ESMALTE > ?";
			argumentosSelecao = new String[] { itemInicio.getNome() };
		}

		List<MarcaEsmalte> itens = new ArrayList<MarcaEsmalte>();

		Cursor cursor = getCatalogo().getBaseDados().query("MARCA_ESMALTE", null,
				selecao, argumentosSelecao, null, "NM_MARCA_ESMALTE",
				String.valueOf(quantidade));

		while (cursor.moveToNext())
			itens.add(new MarcaEsmalte(cursor.getLong(0), cursor.getString(1)));

		return itens;
	}

	@Override
	public MarcaEsmalte obterItemPorId(Long itemId) {
		Cursor cursor = getCatalogo().getBaseDados().query("MARCA_ESMALTE", null, "CD_MARCA_ESMALTE = ?", new String[] { String.valueOf(itemId) }, null, null, null);
		
		if (cursor.moveToNext())
			return (new MarcaEsmalte(cursor.getLong(0), cursor.getString(1)));
		
		return null;
	}

	@Override
	public void atualizarItem(MarcaEsmalte item) {
		ContentValues colunasValores = new ContentValues();
		
		colunasValores.put("NM_MARCA_ESMALTE", item.getNome());
		
		getCatalogo().getBaseDados().update("MARCA_ESMALTE", colunasValores, "CD_MARCA_ESMALTE = ?", new String[] { String.valueOf(item.getId()) });
	}

	@Override
	public void excluirItem(MarcaEsmalte item) {
		getCatalogo().getBaseDados().delete("MARCA_ESMALTE", "CD_MARCA_ESMALTE = ?", new String[] { String.valueOf(item.getId()) });
	}
}
