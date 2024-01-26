package gestionClientes.javierVela;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")

public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	//Constreuctor por defecto requerido por Hibernate
	public Cliente() {
		
	}
	
	//Constructor para crear un objeto de tipo Clientes con un nombre y ciudad específicos
	public Cliente(String nombre) {
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
	
	//Método para imprimir la información del objeto Fabricante
	@Override
	public String toString() {
		return "Cliente{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				'}';
	}
}
