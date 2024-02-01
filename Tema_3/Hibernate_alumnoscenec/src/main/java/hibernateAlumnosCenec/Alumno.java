package hibernateAlumnosCenec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "alumnoscenec")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido1")
	private String apellido1;
	
	@Column(name="apellido2")
	private String apellido2;
	
	@Column(name="es_repetidor")
	private boolean es_repetidor;
	
	@Column(name="fecha_nacimiento")
	private String fecha_nacimiento;
	
	@Column(name="telefono")
	private int telefono;
	
	//Constreuctor por defecto requerido por Hibernate
	public Alumno() {
		
	}

	public Alumno(String nombre, String apellido1, String apellido2, boolean es_repetidor,
			String fecha_nacimiento, int telefono) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.es_repetidor = es_repetidor;
		this.fecha_nacimiento = fecha_nacimiento;
		this.telefono = telefono;
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public boolean isEs_repetidor() {
		return es_repetidor;
	}

	public void setEs_repetidor(boolean es_repetidor) {
		this.es_repetidor = es_repetidor;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", es_repetidor=" + es_repetidor + ", fecha_nacimiento=" + fecha_nacimiento + ", telefono=" + telefono
				+ "]";
	}
	
}
