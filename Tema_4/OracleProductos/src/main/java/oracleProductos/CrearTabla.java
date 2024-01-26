package oracleProductos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CrearTabla {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // URL de la base de datos
    private static final String USUARIO = "SYSTEM";
    private static final String CONTRASENA = "1234";

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


                // Crear la tabla "empresa"
                String crearTablaSQL = "CREATE TABLE categorias ("
                        + "id INT PRIMARY KEY,"
                        + "categoria VARCHAR(30),"
                        + "subcategoria VARCHAR(30)"
                        + ")";
                statement.executeUpdate(crearTablaSQL);


                System.out.println("Tabla 'categorias' creada con éxito.");
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
        }
    }
}

