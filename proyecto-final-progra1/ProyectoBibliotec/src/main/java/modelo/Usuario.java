package modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    public static List<Usuario> listaDeUsuarios = new ArrayList<>();
    private  int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String dpi;
    private String email;
    private String password;
    private int roleID;
}
