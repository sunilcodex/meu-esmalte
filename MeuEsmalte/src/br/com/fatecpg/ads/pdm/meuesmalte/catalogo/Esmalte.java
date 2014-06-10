package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

import java.util.Calendar;
import java.util.Date;

public class Esmalte extends ItemCatalogo {

	private CorEsmalte cor;
	private TipoEsmalte tipo;
	private MarcaEsmalte marca;
	private Calendar dataVencimento;

	public CorEsmalte getCor() {
		return cor;
	}

	public TipoEsmalte getTipo() {
		return tipo;
	}

	public MarcaEsmalte getMarca() {
		return marca;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setCor(CorEsmalte cor) {
		this.cor = cor;
	}

	public void setTipo(TipoEsmalte tipo) {
		this.tipo = tipo;
	}

	public void setMarca(MarcaEsmalte marca) {
		this.marca = marca;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Esmalte() {
		super();
		cor = null;
		tipo = null;
		marca = null;
		dataVencimento = null;
	}

	public Esmalte(String nome, CorEsmalte cor, TipoEsmalte tipo,
			MarcaEsmalte marca, Calendar dataVencimento) {
		super(nome);
		this.cor = cor;
		this.tipo = tipo;
		this.marca = marca;
		this.dataVencimento = dataVencimento;
	}

	public Esmalte(Long id, String nome, CorEsmalte cor, TipoEsmalte tipo,
			MarcaEsmalte marca, Calendar dataVencimento) {
		super(id, nome);
		this.cor = cor;
		this.tipo = tipo;
		this.marca = marca;
		this.dataVencimento = dataVencimento;
	}
}
