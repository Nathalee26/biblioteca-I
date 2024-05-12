package modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Role {
    public static List<Role> listaDeRoles = new ArrayList<>();
    private int id;
    private String nombre;
}
