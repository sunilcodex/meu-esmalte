package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

public abstract class ItemCatalogo {

	private Long id;
	private String nome;
	
	protected ItemCatalogo() {
		id = null;
		nome = null;
	}
	
	protected ItemCatalogo(String nome) {
		this.id = null;
		this.nome = nome;
	}
	
	protected ItemCatalogo(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
