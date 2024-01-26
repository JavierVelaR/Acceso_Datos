package tiendaVerRegistros;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class Eliminar {

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
					
					// Realizar una consulta para obtener los datos antes de la eliminación
		            String selectHqlBeforeDelete = "FROM Fabricante";
		            Query<Fabricante> selectQueryBeforeDelete = session.createQuery(selectHqlBeforeDelete, Fabricante.class);
		            List<Fabricante> fabricantesBeforeDelete = selectQueryBeforeDelete.list();

		            // Imprimir resultados antes de la eliminación
		            System.out.println("\nRegistros en la tabla fabricante antes de la eliminación:");
		            for (Fabricante f : fabricantesBeforeDelete) {
		                System.out.println(f.toString());
		            }

		            // Eliminar el registro
		            String deleteHql = "DELETE FROM Fabricante WHERE id = 12";
		            Query<?> deleteQuery = session.createQuery(deleteHql);
		            deleteQuery.executeUpdate();

		            // Realizar una nueva consulta para obtener los datos después de la eliminación
		            String selectHqlAfterDelete = "FROM Fabricante";
		            Query<Fabricante> selectQueryAfterDelete = session.createQuery(selectHqlAfterDelete, Fabricante.class);
		            List<Fabricante> fabricantesAfterDelete = selectQueryAfterDelete.list();

		            // Imprimir resultados después de la eliminación
		            System.out.println("\nRegistros en la tabla fabricante después de la eliminación:");
		            for (Fabricante f : fabricantesAfterDelete) {
		                System.out.println(f.toString());
		            }
					
					// Hacer el commit de la transaccion
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
