package repositorio;

import modelo.Role;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static conexion.Conexion.getConnection;
import java.sql.SQLException;

public class RoleDAO {
    private PreparedStatement preparedStatement = null;
    private final String QUERY_SELECT_FROM_ROLES = "SELECT * FROM roles";

    public List<Role> readAll() {
        List<Role> listaDeRoles = new ArrayList<>();
        try {
            preparedStatement = getConnection().prepareStatement(QUERY_SELECT_FROM_ROLES);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setNombre(resultSet.getString("nombre"));
                listaDeRoles.add(role);
            }
        } catch (SQLException e) {
        }
        return listaDeRoles;
    }
}
