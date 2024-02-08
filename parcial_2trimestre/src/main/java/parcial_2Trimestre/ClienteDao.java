package parcial_2Trimestre;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class ClienteDao {
	
	@SuppressWarnings("static-access")
	public static void agregarCliente(Cliente c) {
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
			
			System.out.println("Cliente añadido con éxito:\n" + c);
			
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
	public static void eliminarCliente(int id) {
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
            String deleteHql = "DELETE FROM Cliente WHERE id = " + id;
            Query<?> deleteQuery = session.createQuery(deleteHql);
            deleteQuery.executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Cliente eliminado con éxito.");
			
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
	public static void modificarCliente(int id, String nombre, String apellidos) {
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

			// Modificar el nombre por "Gandalf"
            String updateHql = "UPDATE Cliente SET nombre = '"+nombre+"', apellidos = '"+apellidos+"' WHERE id ="+id;
            Query<?> updateQuery = session.createQuery(updateHql);
            updateQuery.executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Cliente modificado con éxito.");
			
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
	public static void mostrarClientes() {
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

			// Crear consulta HQL para seleccionar todos los registros de la tabla clientes
            String hql = "FROM Cliente";
            Query<Cliente> query = session.createQuery(hql, Cliente.class);


            // Ejecutar consulta y obtener resultados
            List<Cliente> clientes = query.list();


            // Imprimir resultados
            System.out.println("Registros en la tabla clientes:");
            for (Cliente c : clientes) {
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
}
