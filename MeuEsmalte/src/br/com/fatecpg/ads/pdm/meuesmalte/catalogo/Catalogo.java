package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Catalogo {

	private GerenciadorBaseDadosCatalogo gerenciadorBaseDados;  
	private SQLiteDatabase baseDados;
	
	public Catalogo(Context contexto) {
		gerenciadorBaseDados = new GerenciadorBaseDadosCatalogo(contexto);
		baseDados = null;
	}

	SQLiteDatabase getBaseDados() {
		return baseDados;
	}
	
	public void abrir() {
		abrir(false);
	}

	public void abrir(boolean alteracoesPermitidas) {
		if (alteracoesPermitidas)
			baseDados = gerenciadorBaseDados.getWritableDatabase();
		else
			baseDados = gerenciadorBaseDados.getReadableDatabase();
	}
	
	public void fechar() {
		baseDados.close();
	}
	
	public void alterar() {
		baseDados.beginTransaction();
	}
	
	public void confirmarAlteracoes() {
		baseDados.setTransactionSuccessful();
		baseDados.endTransaction();
	}
	
	public void cancelarAlteracoes() {
		baseDados.endTransaction();
	}
}
