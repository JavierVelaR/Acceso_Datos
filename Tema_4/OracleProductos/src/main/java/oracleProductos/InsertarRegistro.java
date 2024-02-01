package oracleProductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;


/*public class InsertarRegistro {
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
*/

//Con Hibernate
public class InsertarRegistro {
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

			// Realizar una consulta para obtener los datos antes de la inserción
			String selectHqlBeforeInsert = "FROM Categorias";
			Query<Categorias> selectQueryBeforeInsert = session.createQuery(selectHqlBeforeInsert, Categorias.class);
			List<Categorias> productosBeforeInsert = selectQueryBeforeInsert.list();

			// Imprimir resultados antes de la inserción
			System.out.println("\nRegistros en la tabla categorias antes de la inserción:");
			for (Categorias f : productosBeforeInsert) {
				System.out.println(f.toString());
			}

			// Añadir un nuevo registro
			Categorias nuevoProducto = new Categorias();
			nuevoProducto.setId(3);
			nuevoProducto.setCategoria("Alpargata");
			nuevoProducto.setSubcategoria("Cosmeticos");
			session.save(nuevoProducto);

			// Realizar una nueva consulta para obtener los datos después de la inserción
			String selectHqlAfterInsert = "FROM Categorias";
			Query<Categorias> selectQueryAfterInsert = session.createQuery(selectHqlAfterInsert, Categorias.class);
			List<Categorias> productosAfterInsert = selectQueryAfterInsert.list();

			// Imprimir resultados después de la inserción
			System.out.println("\nRegistros en la tabla categorias después de la inserción:");
			for (Categorias f : productosAfterInsert) {
				System.out.println(f.toString());
			}

			// Hacer commit de la transacción
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
