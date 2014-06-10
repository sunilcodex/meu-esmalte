package br.com.fatecpg.ads.pdm.meuesmalte;

import java.util.List;

import br.com.fatecpg.ads.pdm.meuesmalte.R;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Catalogo;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Esmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.SecaoEsmalte;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class EsmaltesVencidosFragment extends Fragment implements OnItemLongClickListener {

	private EsmalteListaAdaptador esmalteListaAdaptador;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_esmaltes_vencidos, container, false);
	}
	
	public void carregarListaEsmaltes() {
		ListView lista = (ListView) getActivity().findViewById(R.id.esmaltes_vencidos);
		List<Esmalte> listaEsmaltes;
		
		Catalogo catalogo = ((PrincipalActivity) getActivity()).getCatalogo();
		
		catalogo.abrir();
		listaEsmaltes = (new SecaoEsmalte(catalogo)).listarItensVencidos();
		catalogo.fechar();

		esmalteListaAdaptador = new EsmalteListaAdaptador(getActivity(), listaEsmaltes);
		
		lista.setAdapter(esmalteListaAdaptador);
		
		lista.setOnItemLongClickListener(this);
	}
	
	public void atualizarListaEsmaltes() {
		if (esmalteListaAdaptador == null)
			return;
		
		List<Esmalte> listaEsmaltes;
		Catalogo catalogo = ((PrincipalActivity) getActivity()).getCatalogo();
		
		catalogo.abrir();
		listaEsmaltes = (new SecaoEsmalte(catalogo)).listarItensVencidos();
		catalogo.fechar();

		esmalteListaAdaptador.clear();
		esmalteListaAdaptador.addAll(listaEsmaltes);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		carregarListaEsmaltes();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Catalogo catalogo = ((PrincipalActivity) getActivity()).getCatalogo();
		
		catalogo.abrir();
		catalogo.alterar();
		(new SecaoEsmalte(catalogo)).excluirItem(esmalteListaAdaptador.getItem(arg2));
		catalogo.confirmarAlteracoes();
		catalogo.fechar();
		
		esmalteListaAdaptador.remove(esmalteListaAdaptador.getItem(arg2));
		
		((PrincipalActivity) getActivity()).atualizarListasEsmaltes();
		
		return true;
	}
}
