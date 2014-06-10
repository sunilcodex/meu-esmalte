package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.Calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

class GerenciadorBaseDadosCatalogo extends SQLiteOpenHelper {

	public static final int VERSAO_BASE_DADOS_CATALOGO = 1;
	public static final String NOME_BASE_DADOS_CATALOGO = "Catalogo.db";

	public GerenciadorBaseDadosCatalogo(Context contexto) {
		super(contexto, NOME_BASE_DADOS_CATALOGO, null,
				VERSAO_BASE_DADOS_CATALOGO);
	}

	@Override
	public void onCreate(SQLiteDatabase baseDados) {
		baseDados.execSQL("CREATE TABLE COR_ESMALTE (CD_COR_ESMALTE INTEGER PRIMARY KEY ASC, NM_COR_ESMALTE TEXT NOT NULL UNIQUE)");
		baseDados.execSQL("CREATE TABLE TIPO_ESMALTE (CD_TIPO_ESMALTE INTEGER PRIMARY KEY ASC, NM_TIPO_ESMALTE TEXT NOT NULL UNIQUE)");
		baseDados.execSQL("CREATE TABLE MARCA_ESMALTE (CD_MARCA_ESMALTE INTEGER PRIMARY KEY ASC, NM_MARCA_ESMALTE TEXT NOT NULL UNIQUE)");
		baseDados.execSQL(
				"CREATE TABLE ESMALTE ("
				+ "CD_ESMALTE INTEGER PRIMARY KEY ASC,"
				+ "NM_ESMALTE TEXT NOT NULL,"
				+ "CD_COR_ESMALTE INTEGER,"
				+ "CD_TIPO_ESMALTE INTEGER,"
				+ "CD_MARCA_ESMALTE INTEGER,"
				+ "DT_VENCIMENTO_ESMALTE INTEGER,"
				+ "FOREIGN KEY(CD_COR_ESMALTE) REFERENCES COR_ESMALTE(CD_COR_ESMALTE),"
				+ "FOREIGN KEY(CD_TIPO_ESMALTE) REFERENCES TIPO_ESMALTE(CD_TIPO_ESMALTE),"
				+ "FOREIGN KEY(CD_MARCA_ESMALTE) REFERENCES MARCA_ESMALTE(CD_MARCA_ESMALTE))");
		
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xff000000 + ", 'Selecione...')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xffffff00 + ", 'Amarelo')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xffffa500 + ", 'Laranja')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xffff0000 + ", 'Vermelho')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xffff00ff + ", 'Rosa')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xffa020f0 + ", 'Roxo')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xff00ff00 + ", 'Verde')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xff0000ff + ", 'Azul')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xffffffff + ", 'Branco')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xff888888 + ", 'Cinza')");
		baseDados.execSQL("INSERT INTO COR_ESMALTE VALUES (" + 0xff000001 + ", 'Preto')");
	
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1001 + ", 'Selecione...')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1002 + ", 'Cremoso')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1003 + ", 'Cintilante')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1004 + ", 'Neon')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1005 + ", 'Magnético')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1006 + ", 'MultiChrome')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1007 + ", 'Perolado')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1010 + ", 'Glitter')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1011 + ", 'Matte')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1012 + ", 'Holografico')");
		baseDados.execSQL("INSERT INTO TIPO_ESMALTE VALUES (" + 1100 + ", 'Outras')");
		
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0001 + ", 'Selecione...')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0002 + ", 'Colorama')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0003 + ", 'Risqué')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0004 + ", 'Avon')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0005 + ", 'Ana hickmann')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0006 + ", 'Beauty Color')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0007 + ", 'Dote')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0010 + ", 'Hits')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0011 + ", 'La Pogee')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0012 + ", 'Impala')");
		baseDados.execSQL("INSERT INTO MARCA_ESMALTE VALUES (" + 0100 + ", 'Outras')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase baseDados, int versaoAntiga,
			int versaoNova) {
	}
}
