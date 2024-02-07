package hibernategiftland;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class EliminarRegistro {
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
		            String selectHqlBeforeDelete = "FROM Producto";
		            Query<Producto> selectQueryBeforeDelete = session.createQuery(selectHqlBeforeDelete, Producto.class);
		            List<Producto> productosBeforeDelete = selectQueryBeforeDelete.list();

		            // Imprimir resultados antes de la eliminación
		            System.out.println("\nRegistros en la tabla producto antes de la eliminación:");
		            for (Producto a : productosBeforeDelete) {
		                System.out.println(a.toString());
		            }

		            // Eliminar el registro
		            String deleteHql = "DELETE FROM Producto WHERE id = 5";
		            Query<?> deleteQuery = session.createQuery(deleteHql);
		            deleteQuery.executeUpdate();

		            // Realizar una nueva consulta para obtener los datos después de la eliminación
		            String selectHqlAfterDelete = "FROM Producto";
		            Query<Producto> selectQueryAfterDelete = session.createQuery(selectHqlAfterDelete, Producto.class);
		            List<Producto> productosAfterDelete = selectQueryAfterDelete.list();

		            // Imprimir resultados después de la eliminación
		            System.out.println("\nRegistros en la tabla alumnos después de la eliminación:");
		            for (Producto p : productosAfterDelete) {
		                System.out.println(p.toString());
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
