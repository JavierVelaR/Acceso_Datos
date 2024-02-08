package parcial_2Trimestre;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class CitaDao {
	@SuppressWarnings("static-access")
	public static void agregarCita(Cita c) {
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

			session.save(c);

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("Cita añadida con éxito:\n" + c);

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
	public static void eliminarCita(int id) {
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
			String deleteHql = "DELETE FROM Cita WHERE id = " + id;
			Query<?> deleteQuery = session.createQuery(deleteHql);
			deleteQuery.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("Cita eliminada con éxito.");

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
	public static void modificarCita(int id, Date fecha, Time hora) {
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

			// Modificar la fecha y la hora
			String updateHql = "UPDATE Cita SET fecha = '" + fecha + "', hora = '" + hora + "' WHERE id =" + id;
			Query<?> updateQuery = session.createQuery(updateHql);
			updateQuery.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("Cita modificada con éxito.");

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
	public static void mostrarCitas() {
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

			// Crear consulta HQL para seleccionar todos los registros de la tabla citas
			String hql = "FROM Cita";
			Query<Cita> query = session.createQuery(hql, Cita.class);

			// Ejecutar consulta y obtener resultados
			List<Cita> citas = query.list();

			// Imprimir resultados
			System.out.println("Registros en la tabla citas:");
			for (Cita c : citas) {
				System.out.println(c.toString());
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

	public static Cliente selectCliente(int id) {
		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			// Realizar una nueva consulta para obtener los datos actualizados
			String selectHql = "FROM Cliente WHERE id = " + id;
			Query<Cliente> selectQuery = session.createQuery(selectHql, Cliente.class);
			clientes = selectQuery.list();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}

		return clientes.get(0);
	}
	
	public static Medico selectMedico(int id) {
		// Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration().configure() // llama al fichero hibernate.cfg.xml

				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate

		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());

		List<Medico> medicos = new ArrayList<Medico>();
		
		try {
			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			String selectHql = "FROM Medico WHERE id = " + id;
			Query<Medico> selectQuery = session.createQuery(selectHql, Medico.class);
			medicos = selectQuery.list();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}

		return medicos.get(0);
	}
}
