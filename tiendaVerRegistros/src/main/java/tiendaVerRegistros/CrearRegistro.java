package tiendaVerRegistros;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class CrearRegistro {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		//Configurar la sesion del Hibernate
				SessionFactory sessionFactory = new Configuration()
						.configure() // llama al fichero hibernate.cfg.xml
						
						// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
						.buildSessionFactory(); // Construir la sesion de Hibernate
				
				// Configurar la sesion en el contexto actual
				ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
				context.bind(sessionFactory.openSession());
				
				try {
					// Obtener la sesion actual
					Session session = context.currentSession();
					
					// Iniciar transaccion
					session.beginTransaction();
					
					// Realizar una consulta para obtener los datos antes de la inserción
		            String selectHqlBeforeInsert = "FROM Fabricante";
		            Query<Fabricante> selectQueryBeforeInsert = session.createQuery(selectHqlBeforeInsert, Fabricante.class);
		            List<Fabricante> fabricantesBeforeInsert = selectQueryBeforeInsert.list();

		            // Imprimir resultados antes de la inserción
		            System.out.println("\nRegistros en la tabla fabricante antes de la inserción:");
		            for (Fabricante f : fabricantesBeforeInsert) {
		                System.out.println(f.toString());
		            }
					
		            // Añadir un nuevo registro
		            Fabricante nuevoFabricante = new Fabricante();
		            nuevoFabricante.setNombre("Apple");
		            // Configura otros atributos según la estructura de tu entidad fabricante

		            session.save(nuevoFabricante);

		            // Realizar una nueva consulta para obtener los datos después de la inserción
		            String selectHqlAfterInsert = "FROM Fabricante";
		            Query<Fabricante> selectQueryAfterInsert = session.createQuery(selectHqlAfterInsert, Fabricante.class);
		            List<Fabricante> fabricantesAfterInsert = selectQueryAfterInsert.list();

		            // Imprimir resultados después de la inserción
		            System.out.println("\nRegistros en la tabla fabricante después de la inserción:");
		            for (Fabricante f : fabricantesAfterInsert) {
		                System.out.println(f.toString());
		            }
		            
		            // Hacer commit de la transacción
		            session.getTransaction().commit();
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					// Desligar la sesion del contexto
					ThreadLocalSessionContext.unbind(sessionFactory);
					// Cerrar la sesion del Hibernate
					sessionFactory.close();
				}

	}

}
