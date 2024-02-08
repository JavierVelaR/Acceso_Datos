package desafioHibernate;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="id_cliente",nullable = false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<Libro> libros = new ArrayList<Libro>();

	public Pedido() {
		super();
	}

	public Pedido(Date fecha, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", libros=" + libros + "]";
	}
	
}
