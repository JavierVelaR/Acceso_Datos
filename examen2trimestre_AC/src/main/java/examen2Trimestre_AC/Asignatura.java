package examen2Trimestre_AC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Asignaturas")
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="departamento")
	private String departamento;
	
	@Column(name="creditos")
	private int creditos;
	
	@Column(name="descripcion")
	private String descripcion;
	
	//Constreuctor por defecto requerido por Hibernate
	public Asignatura() {
		
	}

	public Asignatura(String nombre, String codigo, String departamento, int creditos, String descripcion) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.departamento = departamento;
		this.creditos = creditos;
		this.descripcion = descripcion;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", departamento=" + departamento
				+ ", creditos=" + creditos + ", descripcion=" + descripcion + "]";
	}

	
}
