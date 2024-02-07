package hibernate_Fabricante_Producto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "codigo_fabricante", nullable = false)
	private Fabricante fabricante;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "precio")
	private float precio;

	public Producto() {
		
	}
	
	public Producto(String nombre, float precio, Fabricante fabricante) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.fabricante = fabricante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", fabricante=" + fabricante + "]";
	}
	
}