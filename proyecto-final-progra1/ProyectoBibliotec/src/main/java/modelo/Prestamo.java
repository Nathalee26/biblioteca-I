package modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Prestamo {
    public static List<Prestamo> listaDePrestamos = new ArrayList<>();
    private int id;
    private int usuarioID;
    private int libroISBN;
    private Date fechaPrestamo;
    private Date fechaVencimiento;
    private Date fechaDevolucion;
    private Double multa;

    public Prestamo(){
        this.multa = 0.0;
    }
}
