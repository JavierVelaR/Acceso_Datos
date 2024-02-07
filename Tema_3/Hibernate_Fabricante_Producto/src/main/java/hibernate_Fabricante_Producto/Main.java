package hibernate_Fabricante_Producto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class Main {

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
			Fabricante fabricante = new Fabricante("ASUS Gaming");

			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			// Guardar objeto fabricante en la base de datos
			session.save(fabricante);

			// Hacer commit de la transacción
			session.getTransaction().commit();

			// Crear objeto producto
			// Crear objeto producto
			Producto producto = new Producto("PORTATIL gaming", 3000f, fabricante);

			// Obtener la sesión actual
			Session session1 = context.currentSession();

			// Iniciar transacción
			session1.beginTransaction();

			// Guardar objeto producto en la base de datos
			session1.save(producto);

			// Hacer commit de la transacción
			session1.getTransaction().commit();

			System.out.println(producto.toString());
			System.out.println(fabricante.toString());

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
