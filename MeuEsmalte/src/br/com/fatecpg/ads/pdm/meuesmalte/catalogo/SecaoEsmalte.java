package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.ArrayAdapter;

public class SecaoEsmalte extends SecaoCatalogo<Esmalte> {

	public SecaoEsmalte(Catalogo catalogo) {
		super(catalogo);
	}

	@Override
	public void inserirItem(Esmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("CD_ESMALTE", item.getId());
		colunasValores.put("NM_ESMALTE", item.getNome());
		colunasValores.put("CD_COR_ESMALTE", item.getCor().getId());
		colunasValores.put("CD_TIPO_ESMALTE", item.getTipo().getId());
		colunasValores.put("CD_MARCA_ESMALTE", item.getMarca().getId());
		colunasValores.put("DT_VENCIMENTO_ESMALTE", item.getDataVencimento()
				.getTimeInMillis());

		item.setId(getCatalogo().getBaseDados().insert("ESMALTE", null,
				colunasValores));
	}

	@Override
	public List<Esmalte> listarItens() {
		List<Esmalte> itens = new ArrayList<Esmalte>();

		SecaoCorEsmalte secaoCorEsmalte = new SecaoCorEsmalte(getCatalogo());
		SecaoTipoEsmalte secaoTipoEsmalte = new SecaoTipoEsmalte(getCatalogo());
		SecaoMarcaEsmalte secaoMarcaEsmalte = new SecaoMarcaEsmalte(
				getCatalogo());

		Cursor cursor = getCatalogo().getBaseDados().query("ESMALTE", null,
				null, null, null, null, null);

		while (cursor.moveToNext()) {
			Esmalte item = new Esmalte();

			item.setId(cursor.getLong(0));
			item.setNome(cursor.getString(1));
			item.setCor(secaoCorEsmalte.obterItemPorId(cursor.getLong(2)));
			item.setTipo(secaoTipoEsmalte.obterItemPorId(cursor.getLong(3)));
			item.setMarca(secaoMarcaEsmalte.obterItemPorId(cursor.getLong(4)));

			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTimeInMillis(cursor.getLong(5));

			item.setDataVencimento(dataVencimento);

			itens.add(item);
		}

		return itens;
	}

	@Override
	public List<Esmalte> listarItens(int quantidade, Esmalte itemInicio) {
		String selecao = null;
		String[] argumentosSelecao = null;

		if (itemInicio != null) {
			selecao = "NM_ESMALTE > ?";
			argumentosSelecao = new String[] { itemInicio.getNome() };
		}

		List<Esmalte> itens = new ArrayList<Esmalte>();

		SecaoCorEsmalte secaoCorEsmalte = new SecaoCorEsmalte(getCatalogo());
		SecaoTipoEsmalte secaoTipoEsmalte = new SecaoTipoEsmalte(getCatalogo());
		SecaoMarcaEsmalte secaoMarcaEsmalte = new SecaoMarcaEsmalte(
				getCatalogo());

		Cursor cursor = getCatalogo().getBaseDados().query("ESMALTE", null,
				selecao, argumentosSelecao, null, "NM_ESMALTE",
				String.valueOf(quantidade));

		while (cursor.moveToNext()) {
			Esmalte item = new Esmalte();

			item.setId(cursor.getLong(0));
			item.setNome(cursor.getString(1));
			item.setCor(secaoCorEsmalte.obterItemPorId(cursor.getLong(2)));
			item.setTipo(secaoTipoEsmalte.obterItemPorId(cursor.getLong(3)));
			item.setMarca(secaoMarcaEsmalte.obterItemPorId(cursor.getLong(4)));

			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTimeInMillis(cursor.getLong(5));

			item.setDataVencimento(dataVencimento);

			itens.add(item);
		}

		return itens;
	}

	public List<Esmalte> listarItens(int quantidade, Esmalte itemInicio,
			Esmalte itemParametros) {
		StringBuilder selecao = new StringBuilder();
		List<String> blocosSelecao = new ArrayList<String>();
		List<String> argumentosSelecao = new ArrayList<String>();

		if (itemInicio != null) {
			blocosSelecao.add("NM_ESMALTE > ?");
			argumentosSelecao.add(itemInicio.getNome());
		}

		if (itemParametros != null) {
			if (itemParametros.getNome() != null) {
				blocosSelecao.add("UPPER(NM_ESMALTE) LIKE %UPPER(?)%");
				argumentosSelecao.add(itemInicio.getNome());
			}

			if (itemParametros.getCor() != null) {
				blocosSelecao.add("CD_COR_ESMALTE = ?");
				argumentosSelecao.add(String.valueOf(itemInicio.getCor()
						.getId()));
			}

			if (itemParametros.getTipo() != null) {
				blocosSelecao.add("CD_TIPO_ESMALTE = ?");
				argumentosSelecao.add(String.valueOf(itemInicio.getTipo()
						.getId()));
			}

			if (itemParametros.getMarca() != null) {
				blocosSelecao.add("CD_MARCA_ESMALTE = ?");
				argumentosSelecao.add(String.valueOf(itemInicio.getMarca()
						.getId()));
			}
		}

		if (blocosSelecao.size() > 0) {
			Iterator<String> blocoSelecao = blocosSelecao.iterator();

			selecao.append(blocoSelecao);

			while (blocoSelecao.hasNext())
				selecao.append(" AND ").append(blocoSelecao.next());
		}

		List<Esmalte> itens = new ArrayList<Esmalte>();

		SecaoCorEsmalte secaoCorEsmalte = new SecaoCorEsmalte(getCatalogo());
		SecaoTipoEsmalte secaoTipoEsmalte = new SecaoTipoEsmalte(getCatalogo());
		SecaoMarcaEsmalte secaoMarcaEsmalte = new SecaoMarcaEsmalte(
				getCatalogo());

		Cursor cursor = getCatalogo().getBaseDados().query("ESMALTE", null,
				selecao.toString(), (String[]) argumentosSelecao.toArray(),
				null, "NM_ESMALTE", String.valueOf(quantidade));

		while (cursor.moveToNext()) {
			Esmalte item = new Esmalte();

			item.setId(cursor.getLong(0));
			item.setNome(cursor.getString(1));
			item.setCor(secaoCorEsmalte.obterItemPorId(cursor.getLong(2)));
			item.setTipo(secaoTipoEsmalte.obterItemPorId(cursor.getLong(3)));
			item.setMarca(secaoMarcaEsmalte.obterItemPorId(cursor.getLong(4)));

			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTimeInMillis(cursor.getLong(5));

			item.setDataVencimento(dataVencimento);

			itens.add(item);
		}

		return itens;
	}

	public List<Esmalte> listarItensVencidos() {
		List<Esmalte> itens = new ArrayList<Esmalte>();

		SecaoCorEsmalte secaoCorEsmalte = new SecaoCorEsmalte(getCatalogo());
		SecaoTipoEsmalte secaoTipoEsmalte = new SecaoTipoEsmalte(getCatalogo());
		SecaoMarcaEsmalte secaoMarcaEsmalte = new SecaoMarcaEsmalte(
				getCatalogo());

		Cursor cursor = getCatalogo().getBaseDados().query("ESMALTE", null,
				"DT_VENCIMENTO_ESMALTE <= (strftime('%s', 'now') * 1000)",
				null, null, null, "NM_ESMALTE");

		while (cursor.moveToNext()) {
			Esmalte item = new Esmalte();

			item.setId(cursor.getLong(0));
			item.setNome(cursor.getString(1));
			item.setCor(secaoCorEsmalte.obterItemPorId(cursor.getLong(2)));
			item.setTipo(secaoTipoEsmalte.obterItemPorId(cursor.getLong(3)));
			item.setMarca(secaoMarcaEsmalte.obterItemPorId(cursor.getLong(4)));

			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTimeInMillis(cursor.getLong(5));

			item.setDataVencimento(dataVencimento);

			itens.add(item);
		}

		return itens;
	}

	@Override
	public Esmalte obterItemPorId(Long itemId) {
		SecaoCorEsmalte secaoCorEsmalte = new SecaoCorEsmalte(getCatalogo());
		SecaoTipoEsmalte secaoTipoEsmalte = new SecaoTipoEsmalte(getCatalogo());
		SecaoMarcaEsmalte secaoMarcaEsmalte = new SecaoMarcaEsmalte(
				getCatalogo());

		Cursor cursor = getCatalogo().getBaseDados().query("ESMALTE", null,
				"CD_ESMALTE = ?", new String[] { String.valueOf(itemId) },
				null, null, null);

		if (cursor.moveToNext()) {
			Esmalte item = new Esmalte();

			item.setId(cursor.getLong(0));
			item.setNome(cursor.getString(1));
			item.setCor(secaoCorEsmalte.obterItemPorId(cursor.getLong(2)));
			item.setTipo(secaoTipoEsmalte.obterItemPorId(cursor.getLong(3)));
			item.setMarca(secaoMarcaEsmalte.obterItemPorId(cursor.getLong(4)));

			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTimeInMillis(cursor.getLong(5));

			item.setDataVencimento(dataVencimento);

			return item;
		}

		return null;
	}

	@Override
	public void atualizarItem(Esmalte item) {
		ContentValues colunasValores = new ContentValues();

		colunasValores.put("NM_ESMALTE", item.getNome());
		colunasValores.put("CD_COR_ESMALTE", item.getCor().getId());
		colunasValores.put("CD_TIPO_ESMALTE", item.getTipo().getId());
		colunasValores.put("CD_MARCA_ESMALTE", item.getMarca().getId());
		colunasValores.put("DT_VENCIMENTO_ESMALTE", item.getDataVencimento()
				.getTimeInMillis());

		getCatalogo().getBaseDados()
				.update("ESMALTE", colunasValores, "CD_ESMALTE = ?",
						new String[] { String.valueOf(item.getId()) });
	}

	@Override
	public void excluirItem(Esmalte item) {
		getCatalogo().getBaseDados().delete("ESMALTE", "CD_ESMALTE = ?",
				new String[] { String.valueOf(item.getId()) });
	}
}
