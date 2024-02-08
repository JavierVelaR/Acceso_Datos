package desafioHibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_autor", nullable = false)
	private Autor autor;
	
	@ManyToOne
	@JoinColumn(name="id_pedido", nullable = false)
	private Pedido pedido;
	
	@OneToMany(mappedBy = "libro")
    private List<Categoria> categorias = new ArrayList<Categoria>();

	public Libro() {
		super();
	}

	public Libro(String nombre, Autor autor, Pedido pedido) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.pedido = pedido;
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

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Categoria> getCategoria() {
		return categorias;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categorias = categoria;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", categoria=" + categorias + "]";
	}
	
	
}
