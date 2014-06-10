package br.com.fatecpg.ads.pdm.meuesmalte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.com.fatecpg.ads.pdm.meuesmalte.R;

public class ApresentacaoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_apresentacao);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {
			Handler agendador = new Handler();
			Runnable tarefa = new Runnable() {
				
				@Override
				public void run() {
					abrirActivityPrincipal();
				}
			};
			
			agendador.postDelayed(tarefa, 3000);
		}
	}

	private void abrirActivityPrincipal() {
		Intent intent = new Intent(this, PrincipalActivity.class);

		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

		finish();
	}
}
