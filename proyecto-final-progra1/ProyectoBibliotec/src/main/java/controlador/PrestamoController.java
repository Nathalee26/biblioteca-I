package controlador;

import modelo.Prestamo;
import repositorio.PrestamoDAO;

import java.sql.Date;
import java.util.List;

public class PrestamoController extends AbstractCrud<Prestamo, Integer>{

    private PrestamoDAO prestamoDAO = new PrestamoDAO();
    @Override
    public List<Prestamo> executeReadAll() {
        return prestamoDAO.readAll();
    }

    @Override
    public Prestamo executeCreate(Prestamo entidad) {
        prestamoDAO.save(entidad);
        return entidad;
    }

    @Override
    public Prestamo executeRead(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean executeUpdate(Integer integer) {
       throw new UnsupportedOperationException();
    }

    @Override
    public void executeDelete(Integer integer) {
        throw new UnsupportedOperationException();
    }
    //bOOlean verdadero o falso
    public boolean verificarPrestamosTardios(int isbn, int usuarioID) {
                //modelo el diamante 
        return Prestamo.listaDePrestamos.stream()
                //anymatch un true o false dependiento de las validaciones dentro del parentesis 
                //funcion flecha variable temporal, hereda todo lo que tiene mi clase prestamo
                .anyMatch(prestamo -> prestamo.getUsuarioID() == usuarioID && prestamo.getLibroISBN() == isbn
                        && prestamo.getFechaVencimiento().before(new Date(System.currentTimeMillis()))
                        && prestamo.getFechaDevolucion()==null);
    }           //VALIDACIONES 

    public void actualizarDevolucion(int id) {
        prestamoDAO.actualizarDevolucion(id);
    }
}
