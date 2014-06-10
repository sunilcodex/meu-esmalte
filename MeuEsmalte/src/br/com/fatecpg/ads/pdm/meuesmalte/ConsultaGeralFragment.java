package br.com.fatecpg.ads.pdm.meuesmalte;

import java.util.List;

import android.app.Activity;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import br.com.fatecpg.ads.pdm.meuesmalte.R;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Catalogo;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Esmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.SecaoEsmalte;

public class ConsultaGeralFragment extends Fragment implements OnItemLongClickListener {

	private EsmalteListaAdaptador esmalteListaAdaptador;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_consulta_geral, container, false);
	}
	
	public void carregarListaEsmaltes() {
		ListView lista = (ListView) getActivity().findViewById(R.id.lista_esmaltes);
		List<Esmalte> listaEsmaltes;
		
		Catalogo catalogo = ((PrincipalActivity) getActivity()).getCatalogo();
		
		catalogo.abrir();
		listaEsmaltes = (new SecaoEsmalte(catalogo)).listarItens();
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
		listaEsmaltes = (new SecaoEsmalte(catalogo)).listarItens();
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
