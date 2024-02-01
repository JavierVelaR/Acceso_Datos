package oracleEmpresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

/* Ejercicio sin usar Hibernate
 * 
public class CrearTabla {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // URL de la base de datos
    private static final String USUARIO = "SYSTEM";
    private static final String CONTRASENA = "1234";
	 
    @SuppressWarnings("static-access")
	public static Connection obtenerConexion() {
        Connection conexion = null;
        
        try {
            // Cargar el controlador JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Obtener la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión establecida con éxito.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
	
    public static void main(String[] args) {
    	 Connection conexion = CrearTabla.obtenerConexion(); // Corregir aquí
        if (conexion != null) {
            Statement statement = null;


            try {
                statement = conexion.createStatement();


                // Crear la tabla "empresas"
                String crearTablaSQL = "CREATE TABLE empresas ("
                        + "id INT PRIMARY KEY,"
                        + "nombre VARCHAR(50),"
                        + "pais VARCHAR(50)"
                        + ")";
                statement.executeUpdate(crearTablaSQL);


                System.out.println("Tabla 'empresas' creada con éxito.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
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
        }*/


// Ejercicio usando Hibernate
public class CrearTabla {

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
			
			//Crear la tabla
			String crearTablaHQL = "CREATE TABLE empresas2 ("
                    + "id INT PRIMARY KEY,"
                    + "nombre VARCHAR(50),"
                    + "pais VARCHAR(50)"
                    + ")";
			
			Query<?> createQuery = session.createQuery(crearTablaHQL);
            //TODO
            
			
			// Hacer el commit de la transaccion
			session.getTransaction().commit();

			// Imprimir producto guardado en la base datatos
			System.out.println("Tabla empresas2 creada");

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
