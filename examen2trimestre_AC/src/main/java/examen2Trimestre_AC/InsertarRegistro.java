package examen2Trimestre_AC;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class InsertarRegistro {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		try {
			// Crea el objeto rofesor
			Asignatura asignatura = new Asignatura("Administración de Sistemas Informáticos en Red", "ASIR01",
					"Informática", 180,
					"Esta asignatura se enfoca en la administración y configuración de sistemas informáticos en red, abarcando desde la instalación y mantenimiento de servidores hasta la gestión de servicios y seguridad de redes.");

			// Obtener la sesion actual
			Session session = context.currentSession();

			// Iniciar transaccion
			session.beginTransaction();

			// Guardar objeto en la base de datos
			session.save(asignatura);

			// Hacer el commit de la transaccion
			session.getTransaction().commit();

			// Imprimir alumno guardado en la base datatos
			System.out.println("Asignatura: " + asignatura);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}

	}
}
