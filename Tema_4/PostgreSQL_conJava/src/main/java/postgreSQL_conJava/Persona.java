package postgreSQL_conJava;

import javax.persistence.*;


@Entity
@Table(name = "persona")
public class Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "nombre")
    private String nombre;


    @Column(name = "edad")
    private int edad;


    // Constructores, getters y setters


    // ...


    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
