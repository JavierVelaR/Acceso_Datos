package oracleProductos;

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

//Sin hibernate
/*public class ModificarRegistro {
	
	public static void main(String[] args) {
        // Ejemplo de uso
    	 modificarRegistro(2, "Monitor", "Verduras");
    }
	
    public static void modificarRegistro(int id, String nuevaCategoria, String nuevaSubcategoria) {
        Connection conexion = CrearTabla.obtenerConexion();
        if (conexion != null) {
            PreparedStatement preparedStatement = null;


            try {
                // Actualizar el registro en la tabla "categorias"
                String actualizarRegistroSQL = "UPDATE categorias SET categoria=?, subcategoria=? WHERE id=?";
                preparedStatement = conexion.prepareStatement(actualizarRegistroSQL);
                preparedStatement.setString(1, nuevaCategoria);
                preparedStatement.setString(2, nuevaSubcategoria);
                preparedStatement.setInt(3, id);


                int filasAfectadas = preparedStatement.executeUpdate();


                if (filasAfectadas > 0) {
                    System.out.println("Registro actualizado con éxito.");
                    System.out.println(id+ " , "+nuevaCategoria+ " , "+nuevaSubcategoria);
                    
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
    
}
*/

//Con hibernate
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
		            String updateHql = "UPDATE Categorias SET categoria = 'Verduras' WHERE id =1";
		            Query<?> updateQuery = session.createQuery(updateHql);
		            updateQuery.executeUpdate();

		            // Realizar una nueva consulta para obtener los datos actualizados
		            String selectHql = "FROM Categorias";
		            Query<Categorias> selectQuery = session.createQuery(selectHql, Categorias.class);
		            List<Categorias> categorias = selectQuery.list();

		            // Imprimir resultados
		            System.out.println("\nRegistros en la tabla Categorias después de la actualización:");
		            for (Categorias f : categorias) {
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
