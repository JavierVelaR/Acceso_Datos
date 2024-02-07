package hibernateAlumnosCenec;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

import hibernateAlumnosCenec.Alumno;

public class ModificarRegistro {
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
					
					// Modificar el nombre por "Gandalf"
		            String updateHql = "UPDATE Alumno SET nombre = 'Gandalf' WHERE id = 4";
		            Query<?> updateQuery = session.createQuery(updateHql);
		            updateQuery.executeUpdate();

		            // Realizar una nueva consulta para obtener los datos actualizados
		            String selectHql = "FROM Alumno WHERE id = 4";
		            Query<Alumno> selectQuery = session.createQuery(selectHql, Alumno.class);
		            List<Alumno> alumnos = selectQuery.list();

		            // Imprimir resultados
		            System.out.println("\nRegistros en la tabla alumnos después de la actualización:");
		            for (Alumno a : alumnos) {
		                System.out.println(a.toString());
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
