
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {
    private  String password;
    private  String user;
    private  String url;
    public PersonaDao(String url, String user, String password) {
        this.url = url;
        this.user =user;
        this.password = password;

    }

    private Connection obtenerConexion() throws SQLException {
        Connection conexion = null;
            return conexion = DriverManager.getConnection(url, user, password);
    }

        public Persona personaPorId(int id) {
        String sql =  "SELECT nombre FROM personas WHERE id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonosProxy = new SetTelefonosProxy(id, this);
            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString(1);
            }
            return new Persona(id, nombrePersona, telefonosProxy);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Set<Telefono> obtenerTelefonosPorPersonaId(int id) {
        String sql = "SELECT numero FROM telefonos WHERE idpersona = ?";
        Set<Telefono> telefonos = new HashSet<>();
        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                telefonos.add(new Telefono(result.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return telefonos;
    }
}
