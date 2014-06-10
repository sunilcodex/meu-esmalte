package br.com.fatecpg.ads.pdm.meuesmalte.catalogo;

public class CorEsmalte extends ItemCatalogo {
	
	public String getRgb() {
		return Long.toHexString(getId());
	}
	
	public void setRgb(Long rgb) {
		setId(rgb);
	}
	
	public void setRgb(String rgb) {
		setId(Long.parseLong(rgb, 16));
	}

	public CorEsmalte() {
		super();
	}
	
	public CorEsmalte(String rgb, String nome) {
		setRgb(rgb);
		setNome(nome);
	}
	
	public CorEsmalte(Long rgb, String nome) {
		setRgb(rgb);
		setNome(nome);
	}
}
