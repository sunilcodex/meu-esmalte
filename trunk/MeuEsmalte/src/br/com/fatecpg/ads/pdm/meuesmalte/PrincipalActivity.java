package br.com.fatecpg.ads.pdm.meuesmalte;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import br.com.fatecpg.ads.pdm.meuesmalte.R;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Catalogo;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.CorEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.Esmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.MarcaEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.SecaoEsmalte;
import br.com.fatecpg.ads.pdm.meuesmalte.catalogo.TipoEsmalte;

public class PrincipalActivity extends FragmentActivity {

	private Catalogo catalogo;
	
	private ConsultaGeralFragment consultaGeralFragment;
	private EsmaltesVencidosFragment esmaltesVencidosFragment;
	
	public Catalogo getCatalogo() {
		return catalogo;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_principal);

		PagerAdapter adaptadorPagina = new MeuEsmalteFragmentStatePagerAdapter(
				getSupportFragmentManager());

		ViewPager paginador = (ViewPager) findViewById(R.id.paginador);
		paginador.setAdapter(adaptadorPagina);
		
		catalogo = new Catalogo(this);
	}

	private class MeuEsmalteFragmentStatePagerAdapter extends
			FragmentPagerAdapter {

		public MeuEsmalteFragmentStatePagerAdapter(
				FragmentManager gerenciadorFragmentos) {
			super(gerenciadorFragmentos);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;

			switch (position) {
			case 0:
				fragment = new AdicionarEsmalteFragment();
				break;
			case 1:
				consultaGeralFragment = new ConsultaGeralFragment();
				fragment = consultaGeralFragment;
				break;
			case 2:
				esmaltesVencidosFragment = new EsmaltesVencidosFragment();
				fragment = esmaltesVencidosFragment;
				break;
			}

			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			CharSequence titulo = null;

			switch (position) {
			case 0:
				titulo = "Adicionar";
				break;
			case 1:
				titulo = "Todos";
				break;
			case 2:
				titulo = "Vencidos";
				break;
			}

			return titulo;
		}

		@Override
		public int getCount() {
			return 3;
		}
	}
	
	public void quandoCadastrarClicado(View view) {
		EditText nomeEsmalte = (EditText) findViewById(R.id.editText1);
		Spinner corEsmalte = (Spinner) findViewById(R.id.spinner1);
		Spinner marcaEsmalte = (Spinner) findViewById(R.id.spinner2);
		Spinner tipoEsmalte = (Spinner) findViewById(R.id.spinner3);
		EditText dataVencimentoEsmalte = (EditText) findViewById(R.id.editText2);
		
		if (nomeEsmalte.getText().toString().replace(" ", "").length() == 0) {
			Toast.makeText(this, "Qual é o nome?", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (corEsmalte.getSelectedItemPosition() == 0) {
			Toast.makeText(this, "Qual é a cor?", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (marcaEsmalte.getSelectedItemPosition() == 0) {
			Toast.makeText(this, "Qual é a marca?", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (tipoEsmalte.getSelectedItemPosition() == 0) {
			Toast.makeText(this, "Qual é o tipo?", Toast.LENGTH_SHORT).show();
			return;
		}
		
		String temp = dataVencimentoEsmalte.getText().toString().replace("/", "");
		
		if (!temp.matches("\\d\\d\\d\\d\\d\\d\\d\\d")) {
			Toast.makeText(this, "Qual é a data de vencimento?", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.set(Integer.valueOf(temp.substring(4, 8)), Integer.valueOf(temp.substring(2, 4)) - 1, Integer.valueOf(temp.substring(0, 2)));
		
		Esmalte esmalte = new Esmalte(nomeEsmalte.getText().toString().replace(" ", ""), (CorEsmalte) corEsmalte.getSelectedItem(), (TipoEsmalte) tipoEsmalte.getSelectedItem(), (MarcaEsmalte) marcaEsmalte.getSelectedItem(), dataVencimento);
		
		Catalogo c = new Catalogo(this);
		SecaoEsmalte se = new SecaoEsmalte(c);
		
		c.abrir();
		c.alterar();
		se.inserirItem(esmalte);
		c.confirmarAlteracoes();
		c.fechar();
		
		Toast.makeText(this, "Cadastrado!", Toast.LENGTH_SHORT).show();
		
		nomeEsmalte.setText("");
		corEsmalte.setSelection(0);
		marcaEsmalte.setSelection(0);
		tipoEsmalte.setSelection(0);
		dataVencimentoEsmalte.setText("");
		
		nomeEsmalte.requestFocus();
		
		atualizarListasEsmaltes();
	}
	
	public void atualizarListasEsmaltes() {
		if (consultaGeralFragment != null)
			consultaGeralFragment.atualizarListaEsmaltes();
		
		if (esmaltesVencidosFragment != null)
			esmaltesVencidosFragment.atualizarListaEsmaltes();
	}
}
