package postgreSQL_conJava;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
        // Configurar la sesión de Hibernate
        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            // Insertar un registro
            insertarRegistro(sessionFactory, "Juan", 25);

            // Mostrar registros
            mostrarRegistros(sessionFactory);
        }
    }

    private static void insertarRegistro(SessionFactory sessionFactory, String nombre, int edad) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Crear una instancia de Persona
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setEdad(edad);

            // Guardar la instancia en la base de datos
            session.save(persona);

            session.getTransaction().commit();
            System.out.println("Registro insertado con éxito.");
        }
    }

    private static void mostrarRegistros(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Obtener todos los registros de Persona
            List<Persona> personas = session.createQuery("FROM Persona", Persona.class).list();

            for (Persona persona : personas) {
                System.out.println(persona);
            }

            session.getTransaction().commit();
        }
    }

}
