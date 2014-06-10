package br.com.fatecpg.ads.pdm.meuesmalte;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.fatecpg.ads.pdm.meuesmalte.R;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Esmalte;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EsmalteListaAdaptador extends ArrayAdapter<Esmalte> {

	private static SimpleDateFormat formatoDataVencimento = new SimpleDateFormat(
			"dd/MM/yyyy");

	public EsmalteListaAdaptador(Context contexto, List<Esmalte> listaEsmaltes) {
		super(contexto, R.layout.list_item_esmalte, listaEsmaltes);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Esmalte esmalte = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.list_item_esmalte, parent, false);
		}

		((SurfaceView) convertView.findViewById(R.id.cor_esmalte))
				.setBackgroundColor(esmalte.getCor().getId().intValue());
		((TextView) convertView.findViewById(R.id.nome_esmalte))
				.setText(esmalte.getNome());
		((TextView) convertView.findViewById(R.id.tipo_esmalte))
				.setText(esmalte.getTipo().getNome());
		((TextView) convertView.findViewById(R.id.marca_esmalte))
				.setText(esmalte.getMarca().getNome());
		((TextView) convertView.findViewById(R.id.data_vencimento_esmalte))
				.setText(formatoDataVencimento.format(esmalte
						.getDataVencimento().getTime()));

		return convertView;
	}
}
