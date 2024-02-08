package parcial_2Trimestre;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class MedicoDao {
	@SuppressWarnings("static-access")
	public static void agregarMedico(Medico m) {
		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		try {

			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			session.save(m);

			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Médico añadido con éxito:\n" + m);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}
	}
	
	@SuppressWarnings("static-access")
	public static void eliminarMedico(int id) {
		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		try {

			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			// Eliminar el registro
            String deleteHql = "DELETE FROM Medico WHERE id = " + id;
            Query<?> deleteQuery = session.createQuery(deleteHql);
            deleteQuery.executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Médico eliminado con éxito.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}
	}

	
	@SuppressWarnings("static-access")
	public static void modificarMedico(int id, String nombre, String apellidos) {
		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		try {

			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			// Modificar el nombre y apellidos
            String updateHql = "UPDATE Medico SET nombre = '"+nombre+"', apellidos = '"+apellidos+"' WHERE id ="+id;
            Query<?> updateQuery = session.createQuery(updateHql);
            updateQuery.executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Médico modificado con éxito.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}
	}
	
	@SuppressWarnings("static-access")
	public static void mostrarMedicos() {
		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		try {

			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			// Crear consulta HQL para seleccionar todos los registros de la tabla medicos
            String hql = "FROM Medico";
            Query<Medico> query = session.createQuery(hql, Medico.class);


            // Ejecutar consulta y obtener resultados
            List<Medico> medicos = query.list();


            // Imprimir resultados
            System.out.println("Registros en la tabla medicos:");
            for (Medico m : medicos) {
                System.out.println(m.toString());
            }
            
            session.getTransaction().commit();
			
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
