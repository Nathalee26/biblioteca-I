/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import repositorio.LibroDAO;
import modelo.Libros;

import java.util.List;

public class LibroControlador extends AbstractCrud<Libro, Integer> {
    private LibroDAO repository = new Libro DAO();
    
    @Override
    public List<Libro> executeReadAll (){
        return repository.readAll();
    }
    @Override
    public Libro executeCreate (Libro entidad){
        repository.save(entidad),
        return entidad;    
    }
    @Override
    public Libro executeRead (Integer id){
       return Libro.listaDeLibros
               .stream()
               .filter(libro -> libro.getIsbn()==id)
               .findFirst()
               .orElse(null);
    }
    @Override
    public boolean executeUpdate (Integer isbn){
        throw new UnsupportedOperationException();
    }
    @Override
    public void executeDelete(Integer id)
            repository.delete(id);  
    }
    public boolean actualizarLibro(int isbn, String tipoSolicitud){
        return repository.update(isbn, tipoSolicitud);
    }
    public boolean cantidadMayorCero(int isbn){
        Libro libroAprestar = Libro.listaDeLibros.stream()
                .filter(libro -> libro.getIsbn()==isbn)
                .findFirst()
                .orElse(null);
        return libroAprestar != null && libroAprestar.getCantidadDisponible()> 0;

}
