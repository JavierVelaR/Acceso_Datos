package oracleEmpresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

/*public class EliminarRegistro {
    public static void eliminarRegistro(int id) {
        Connection conexion = CrearTabla.obtenerConexion();


        if (conexion != null) {
            PreparedStatement preparedStatement = null;


            try {
                // Eliminar el registro de la tabla "empresa"
                String eliminarRegistroSQL = "DELETE FROM empresa WHERE id=?";
                preparedStatement = conexion.prepareStatement(eliminarRegistroSQL);
                preparedStatement.setInt(1, id);


                int filasAfectadas = preparedStatement.executeUpdate();


                if (filasAfectadas > 0) {
                    System.out.println("Registro con ID " + id + " eliminado con éxito.");
                } else {
                    System.out.println("No se encontró el registro con ID: " + id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }


    public static void main(String[] args) {
        // Ejemplo de uso
        eliminarRegistro(5);
    }
}*/

public class EliminarRegistro {

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
			// Obtener la sesion actual
			Session session = context.currentSession();

			// Iniciar transaccion
			session.beginTransaction();

			// Realizar una consulta para obtener los datos antes de la eliminación
			String selectHqlBeforeDelete = "FROM Empresa";
			Query<Empresa> selectQueryBeforeDelete = session.createQuery(selectHqlBeforeDelete, Empresa.class);
			List<Empresa> empresasBeforeDelete = selectQueryBeforeDelete.list();

			// Imprimir resultados antes de la eliminación
			System.out.println("\nRegistros en la tabla empresa antes de la eliminación:");
			for (Empresa f : empresasBeforeDelete) {
				System.out.println(f.toString());
			}

			// Eliminar el registro
			String deleteHql = "DELETE FROM Empresa WHERE id = 0";
			Query<?> deleteQuery = session.createQuery(deleteHql);
			deleteQuery.executeUpdate();

			// Realizar una nueva consulta para obtener los datos después de la eliminación
			String selectHqlAfterDelete = "FROM Empresa";
			Query<Empresa> selectQueryAfterDelete = session.createQuery(selectHqlAfterDelete, Empresa.class);
			List<Empresa> empresasAfterDelete = selectQueryAfterDelete.list();

			// Imprimir resultados después de la eliminación
			System.out.println("\nRegistros en la tabla fabricante después de la eliminación:");
			for (Empresa f : empresasAfterDelete) {
				System.out.println(f.toString());
			}

			// Hacer el commit de la transaccion
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