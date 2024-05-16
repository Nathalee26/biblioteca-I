package repositorio;

import conexion.Conexion;
import java.sql.Date;
import modelo.Prestamo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrestamoDAO {

    private PreparedStatement preparedStatement = null;
    private final String QUERY_SELECT_FROM_PRESTAMO = "SELECT * FROM prestamos";
    private final String QUERY_INSERT_INTO_PRESTAMO = "INSERT INTO prestamos (usuario_id, libro_isbn, fecha_prestamo, fecha_vencimiento) VALUES (?, ?, ?, ?)";
    private final String QUERY_UPDATE_PRESTAMO = "UPDATE prestamos SET fecha_devolucion = ?, multa = ? WHERE id = ?";

    public List<Prestamo> readAll() {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            preparedStatement = Conexion.getConnection().prepareStatement(QUERY_SELECT_FROM_PRESTAMO);
            preparedStatement.execute();
            preparedStatement.getResultSet();
            while (preparedStatement.getResultSet().next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(preparedStatement.getResultSet().getInt("id"));
                prestamo.setUsuarioID(preparedStatement.getResultSet().getInt("usuario_id"));
                prestamo.setLibroISBN(preparedStatement.getResultSet().getInt("libro_isbn"));
                prestamo.setFechaPrestamo(preparedStatement.getResultSet().getDate("fecha_prestamo"));
                prestamo.setFechaVencimiento(preparedStatement.getResultSet().getDate("fecha_vencimiento"));
                prestamo.setFechaDevolucion(preparedStatement.getResultSet().getDate("fecha_devolucion"));
                prestamo.setMulta(preparedStatement.getResultSet().getDouble("multa"));
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
        }
        return prestamos;
    }

    public void save(Prestamo prestamo) {
        Date fechaPrestamo = new Date(System.currentTimeMillis());
        Date fechaVencimiento = new Date(System.currentTimeMillis() + 604800000);

        try {
            preparedStatement = Conexion.getConnection().prepareStatement(QUERY_INSERT_INTO_PRESTAMO);
            preparedStatement.setInt(1, prestamo.getUsuarioID());
            preparedStatement.setInt(2, prestamo.getLibroISBN());
            preparedStatement.setDate(3, fechaPrestamo);
            preparedStatement.setDate(4, fechaVencimiento);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void actualizarDevolucion(int prestamoID) {
        Date fechaDevolucion = new Date(System.currentTimeMillis());
        Prestamo prestamo = Prestamo.listaDePrestamos.stream()
                .filter(p -> p.getId() == prestamoID)
                .findFirst()
                .orElse(null);

        if (fechaDevolucion.compareTo(prestamo.getFechaVencimiento()) > 0) {
            long msDiff = fechaDevolucion.getTime() - prestamo.getFechaVencimiento().getTime();
            long daysLate = TimeUnit.MILLISECONDS.toDays(msDiff);

            if (daysLate > 0) {
                double multa = daysLate * 10.0;
                prestamo.setMulta(multa);
            }
        }

        try {

            preparedStatement = Conexion.getConnection().prepareStatement(QUERY_UPDATE_PRESTAMO);
            preparedStatement.setDate(1, fechaDevolucion);
            preparedStatement.setDouble(2, prestamo.getMulta());
            preparedStatement.setInt(3, prestamoID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

}
