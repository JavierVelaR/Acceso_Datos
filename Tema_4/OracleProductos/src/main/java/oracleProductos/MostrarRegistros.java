package oracleProductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MostrarRegistros {
	
	public static void main(String[] args) {
        // Ejemplo de uso
        mostrarRegistros();
    }
	
    public static void mostrarRegistros() {
    	Connection conexion = CrearTabla.obtenerConexion();


        if (conexion != null) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;


            try {
                // Consultar todos los registros de la tabla "categorias"
                String consultarRegistrosSQL = "SELECT * FROM categorias";
                preparedStatement = conexion.prepareStatement(consultarRegistrosSQL);
                resultSet = preparedStatement.executeQuery();


                // Imprimir los resultados
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String categoria = resultSet.getString("categoria");
                    String subcategoria = resultSet.getString("subcategoria");


                    System.out.println("ID: " + id + ", Categoria: " + categoria + ", Subcategoria: " + subcategoria);
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


    
}
