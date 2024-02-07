package hibernate_Fabricante_Producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "fabricante")

public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
	private List<Producto> productos = new ArrayList<Producto>();
	
	@Column(name="nombre")
	private String nombre;
	
	//Constreuctor por defecto requerido por Hibernate
	public Fabricante() {
		
	}
	
	//Constructor para crear un objeto de tipo Clientes con un nombre y ciudad específicos
	public Fabricante(String nombre){
		this.nombre=nombre;
	}
	
	//Getter y setter para el atributo id
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	//Getter y setter para el atributo nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Fabricante [id=" + id + ", nombre=" + nombre + "]";
	}
		
}

