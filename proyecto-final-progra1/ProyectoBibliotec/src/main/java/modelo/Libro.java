package modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Libro {
    public static List<Libro> listaDeLibros = new ArrayList<>();
    private int isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private String anioPublicacion;
    private int cantidadDisponible;
    
}
