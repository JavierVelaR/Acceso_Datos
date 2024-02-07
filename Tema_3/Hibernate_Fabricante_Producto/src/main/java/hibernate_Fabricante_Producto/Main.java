package hibernate_Fabricante_Producto;

import java.sql.Date;

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
			Fabricante fabricante = new Fabricante("Lenovo");

			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();

			// Guardar objeto fabricante en la base de datos
			session.save(fabricante);

			// Hacer commit de la transacción
			session.getTransaction().commit();

			// Crear objeto producto
			Producto producto = new Producto("Auriculares", 80f, fabricante);

			// Obtener la sesión actual
			Session session1 = context.currentSession();

			// Iniciar transacción
			session1.beginTransaction();

			// Guardar objeto producto en la base de datos
			session1.save(producto);

			// Hacer commit de la transacción
			session1.getTransaction().commit();

			// Crear objeto compra
			Compra compra = new Compra(Date.valueOf("2023-06-12"), producto, 2);

			// Obtener la sesión actual
			Session session2 = context.currentSession();

			// Iniciar transacción
			session2.beginTransaction();

			// Guardar objeto compra en la base de datos
			session2.save(compra);

			// Hacer commit de la transacción
			session2.getTransaction().commit();

			System.out.println(producto.toString());
			System.out.println(fabricante.toString());
			System.out.println(compra);

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
