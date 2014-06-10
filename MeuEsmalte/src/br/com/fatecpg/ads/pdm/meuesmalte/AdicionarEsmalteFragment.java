package br.com.fatecpg.ads.pdm.meuesmalte;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Catalogo;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.CorEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.MarcaEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.SecaoCorEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.SecaoMarcaEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.SecaoTipoEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.TipoEsmalte;

public class AdicionarEsmalteFragment extends Fragment implements OnItemSelectedListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_adicionar_esmalte, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// Spinner1 com as CORES para o cadastro
				Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner1);
				spinner.setOnItemSelectedListener(this);
				
				Catalogo c = new Catalogo(getActivity());
				
				SecaoCorEsmalte sce = new SecaoCorEsmalte(c);

				c.abrir();

				List<CorEsmalte> cores = sce.listarItens();

				c.fechar();
							
				ArrayAdapter<CorEsmalte> adapter = new ArrayAdapter<CorEsmalte>(getActivity(), 
						android.R.layout.simple_spinner_item, cores);
				
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				//adapter.setDropDownViewResource(R.layout.spinner);
				
				spinner.setAdapter(adapter);
			// fim spinner1
				
				
			// Spinner2 com as MARCAS para o cadastro
				Spinner spinner2 = (Spinner) getActivity().findViewById(R.id.spinner2);
				spinner2.setOnItemSelectedListener(this);
						
				Catalogo c2 = new Catalogo(getActivity());
						
				SecaoMarcaEsmalte sme = new SecaoMarcaEsmalte(c2);
					
				c2.abrir();
				
				List<MarcaEsmalte> marcas = sme.listarItens();
					
				c2.fechar();
										
				ArrayAdapter<MarcaEsmalte> adapter2 = new ArrayAdapter<MarcaEsmalte>(getActivity(), 
					android.R.layout.simple_spinner_item, marcas);
							
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				//adapter2.setDropDownViewResource(R.layout.spinner);
						
				spinner2.setAdapter(adapter2);
			// fim spinner2
				
			// Spinner3 com osTIPOS para o cadastro
				Spinner spinner3 = (Spinner) getActivity().findViewById(R.id.spinner3);
				spinner3.setOnItemSelectedListener(this);
									
				Catalogo c3 = new Catalogo(getActivity());
									
				SecaoTipoEsmalte ste = new SecaoTipoEsmalte(c3);
								
				c3.abrir();
							
				List<TipoEsmalte> tipos = ste.listarItens();
								
				c3.fechar();
												
				ArrayAdapter<TipoEsmalte> adapter3 = new ArrayAdapter<TipoEsmalte>(getActivity(), 
					android.R.layout.simple_spinner_item, tipos);
										
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				//adapter3.setDropDownViewResource(R.layout.spinner);
								
				spinner3.setAdapter(adapter3);
			// fim spinner3,
				
			//mascara para o campo DATA DE VALIDADE	
			final EditText editText2 = (EditText) getActivity().findViewById(R.id.editText2);
			editText2.addTextChangedListener(Mask.insert("##/##/####", editText2));
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		TextView selectedText = (TextView) arg0.getChildAt(0);
		   if (selectedText != null) {
		      selectedText.setTextColor(Color.BLACK);
		   }
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
