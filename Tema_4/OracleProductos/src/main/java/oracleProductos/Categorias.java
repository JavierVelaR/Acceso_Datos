package oracleProductos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Categorias {

	@Id
	private int id;
	private String categoria;
	private String subcategoria;

	public Categorias() {
		
	}
	
	public Categorias(int id, String categoria, String subcategoria) {
		super();
		this.id=id;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", categoria=" + categoria + ", subcategoria=" + subcategoria + "]";
	}	
	
}
