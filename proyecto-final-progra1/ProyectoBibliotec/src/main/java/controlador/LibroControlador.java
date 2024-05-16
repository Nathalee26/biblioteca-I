package controlador;

import repositorio.LibroDAO;
import modelo.Libro;


import java.util.List;
public class LibroControlador extends AbstractCrud<Libro, Integer> {

    private LibroDAO repository = new LibroDAO();

    @Override
    public List<Libro> executeReadAll() {
        return repository.readAll();
    }

    @Override
    public Libro executeCreate(Libro entidad) {
        repository.save(entidad);
        return entidad;
    }

    @Override
    public Libro executeRead(Integer id) {

        return Libro.listaDeLibros
                .stream()
                .filter(libro -> libro.getIsbn() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean executeUpdate(Integer isbn) {
         throw new UnsupportedOperationException();
    }

    @Override
    public void executeDelete(Integer id) {
          repository.delete(id);
    }

    public boolean actualizarLibro(int isbn, String tipoSolicitud) {
        return repository.update(isbn, tipoSolicitud);
    }

    public boolean cantidadMayorCero(int isbn) {

        Libro libroAPrestar = Libro.listaDeLibros.stream()
                        .filter(libro -> libro.getIsbn() == isbn)
                        .findFirst()
                        .orElse(null);

        return libroAPrestar != null && libroAPrestar.getCantidadDisponible() > 0;

    }

    public void agregarExistencia(int isbn, int cantidad) {
        repository.agregarExistencia(isbn, cantidad);
    }
}
