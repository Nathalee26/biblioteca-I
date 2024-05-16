package repositorio;

import conexion.Conexion;
import modelo.Libro;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private PreparedStatement preparedStatement = null;
    private final String QUERY_OBTENER_LIBROS = "SELECT * FROM libros";
    private final String QUERY_INSERTAR_NUEVO_LIBRO = "INSERT INTO libros (isbn, titulo, autor, editorial, anio_publicacion, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?)";
    private final String QUERY_ACTUALIZAR_LIBRO_PRESTAMO = "UPDATE libros SET cantidad_disponible = cantidad_disponible - 1  WHERE isbn = ?";
    private final String QUERY_ACTUALIZAR_LIBRO_DEVOLUCION = "UPDATE libros SET cantidad_disponible = cantidad_disponible + 1  WHERE isbn = ?";
    private final String DELETE_FROM_LIBROS = "DELETE FROM libros WHERE isbn = ?";

    public List<Libro> readAll() {
        List<Libro> libros = new ArrayList<>();
        try {
            preparedStatement = Conexion.getConnection().prepareStatement(QUERY_OBTENER_LIBROS);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setIsbn(resultSet.getInt("isbn"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setAutor(resultSet.getString("autor"));
                libro.setEditorial(resultSet.getString("editorial"));
                libro.setAnioPublicacion(resultSet.getString("anio_publicacion"));
                libro.setCantidadDisponible(resultSet.getInt("cantidad_disponible"));
                libros.add(libro);
            }
        } catch (SQLException e) {
            return libros;
        }

        return libros;
    }

    public void save(Libro libro) {
        try {
            preparedStatement = Conexion.getConnection().prepareStatement(QUERY_INSERTAR_NUEVO_LIBRO);
            preparedStatement.setInt(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getTitulo());
            preparedStatement.setString(3, libro.getAutor());
            preparedStatement.setString(4, libro.getEditorial());
            preparedStatement.setString(5, libro.getAnioPublicacion());
            preparedStatement.setInt(6, libro.getCantidadDisponible());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public boolean update(int isbn, String tipoSolicitud) {
        try {
            switch (tipoSolicitud) {
                case "prestamo" -> preparedStatement = Conexion.getConnection().prepareStatement(QUERY_ACTUALIZAR_LIBRO_PRESTAMO);
                case "devolucion" -> preparedStatement = Conexion.getConnection().prepareStatement(QUERY_ACTUALIZAR_LIBRO_DEVOLUCION);
                default -> {
                }
            }
            preparedStatement.setInt(1, isbn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean delete(int isbn) {
        try {
            preparedStatement = Conexion.getConnection().prepareStatement(DELETE_FROM_LIBROS);
            preparedStatement.setInt(1, isbn);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void agregarExistencia(int isbn, int cantidadDisponible)  {
        try {
            preparedStatement = Conexion.getConnection().prepareStatement("UPDATE libros SET cantidad_disponible = cantidad_disponible + ? WHERE isbn = ?");
            preparedStatement.setInt(1, cantidadDisponible);
            preparedStatement.setInt(2, isbn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
