package oracleProductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


public class InsertarRegistro {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            // Registrar el driver JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");


            // Establecer la conexión con la base de datos
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String usuario = "SYSTEM";
            String contrasena = "1234";
            conn = DriverManager.getConnection(url, usuario, contrasena);


            // Iniciar la transacción
            conn.setAutoCommit(false);


            // Crear el objeto categoria con los datos a insertar
            int id = 2;
            String categoria = "Alimentacion";
            String subcategoria = "Monitor";


            // Insertar el objeto en la tabla categoria
            String sql = "INSERT INTO categorias (id, categoria, subcategoria) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, categoria);
            stmt.setString(3, subcategoria);
            stmt.executeUpdate();


            // Mostrar todos los datos de la tabla categorias
            sql = "SELECT * FROM categorias";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_producto = rs.getInt("id");
                String categoria_producto = rs.getString("categoria");
                String subcategoria_producto = rs.getString("subcategoria");
                System.out.println("ID: " + id_producto + " | Categoria: " + categoria_producto + " | Subcategoria: " + subcategoria_producto);
            }


            // Hacer commit de la transacción
            conn.commit();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // Si ocurre algún error, hacer rollback de la transacción
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y los recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
