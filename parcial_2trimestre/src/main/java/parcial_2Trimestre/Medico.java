package parcial_2Trimestre;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="medico")
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<Cita> citas = new ArrayList<Cita>();
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;

	@Column(name="especialidad")
	private String especialidad;

	public Medico() {
		super();
	}

	public Medico(String nombre, String apellidos, String especialidad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", especialidad=" + especialidad
				+ "]";
	}
	
}
