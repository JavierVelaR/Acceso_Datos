package desafioHibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
			// Obtener la sesión actual
			Session session = context.currentSession();

			// Iniciar transacción
			session.beginTransaction();
			
			Autor autor = new Autor("JK Rowling");
		    session.save(autor); // Save Autor entity

		    Categoria categoria = new Categoria("Fantasía");

		    Cliente cliente = new Cliente("Manuel Turizo", "83649274J");
		    session.save(cliente); // Save Cliente entity

		    Pedido pedido = new Pedido(Date.valueOf("2024-01-05"), cliente);
		    session.save(pedido); // Save Pedido entity

		    // Create and save Libro before Categoria
		    Libro libro = new Libro("Harry Potter", autor, pedido);
		    session.save(libro); // Save Libro entity

		    // Establish the relationship between Libro and Categoria
		    libro.getCategoria().add(categoria); // Associate Libro with Categoria
		    categoria.setLibro(libro); // Associate Categoria with Libro

		    // Add libro to pedido and establish bidirectional relationship
		    pedido.getLibros().add(libro);
		    libro.setPedido(pedido);

		    // Save other entities
		    session.save(categoria);

		    // Commit the transaction
		    session.getTransaction().commit();

			System.out.println(autor);
			System.out.println(categoria);
			System.out.println(libro);
			System.out.println(cliente);
			System.out.println(pedido);
			
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
