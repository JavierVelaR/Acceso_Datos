package parcial_2Trimestre;

import java.sql.Time;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="cita")
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_medico", nullable = false)
	private Medico medico;

	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="hora")
	private Time hora;

	public Cita() {
		super();
	}

	public Cita(Cliente cliente, Medico medico, Date fecha, Time hora) {
		super();
		this.cliente = cliente;
		this.medico = medico;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", cliente=" + cliente + ", medico=" + medico + ", fecha=" + fecha + ", hora=" + hora
				+ "]";
	}
	
}
