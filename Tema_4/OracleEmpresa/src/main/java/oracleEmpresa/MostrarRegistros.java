package oracleEmpresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

//Sin hibernate
/*public class MostrarRegistros {
    public static void mostrarRegistros() {
        Connection conexion = CrearTabla.obtenerConexion();


        if (conexion != null) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;


            try {
                // Consultar todos los registros de la tabla "empresa"
                String consultarRegistrosSQL = "SELECT * FROM empresa";
                preparedStatement = conexion.prepareStatement(consultarRegistrosSQL);
                resultSet = preparedStatement.executeQuery();


                // Imprimir los resultados
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String pais = resultSet.getString("pais");


                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", País: " + pais);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
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
        mostrarRegistros();
    }
}
*/

//Con hibernate
public class MostrarRegistros {

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
					
					// Crear consulta HQL para seleccionar todos los registros de la tabla Empresa
		            String hql = "FROM Empresa";
		            Query<Empresa> query = session.createQuery(hql, Empresa.class);


		            // Ejecutar consulta y obtener resultados
		            List<Empresa> empresas = query.list();


		            // Imprimir resultados
		            System.out.println("Registros en la tabla Empresa:");
		            for (Empresa f : empresas) {
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
