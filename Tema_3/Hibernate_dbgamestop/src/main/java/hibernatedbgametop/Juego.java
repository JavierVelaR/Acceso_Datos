package hibernatedbgametop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tvideojuegos")
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idJuego")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="año")
	private int año;
	
	@Column(name="compañia")
	private String compañia;
	
	@Column(name="precio")
	private float precio;
	
	@Column(name="sinopsis")
	private String sinopsis;
	
	@Column(name="plataforma")
	private String plataforma;

	public Juego() {
		
	}
	
	public Juego(String nombre, int año, String compañia, float precio, String sinopsis, String plataforma) {
		super();
		this.nombre = nombre;
		this.año = año;
		this.compañia = compañia;
		this.precio = precio;
		this.sinopsis = sinopsis;
		this.plataforma = plataforma;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public String toString() {
		return "Juego [id=" + id + ", nombre=" + nombre + ", año=" + año + ", compañia=" + compañia + ", precio="
				+ precio + ", sinopsis=" + sinopsis + ", plataforma=" + plataforma + "]";
	}
	
}
