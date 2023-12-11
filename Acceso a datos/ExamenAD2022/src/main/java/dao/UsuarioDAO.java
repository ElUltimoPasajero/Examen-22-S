package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cesur.examenaddicc22.database.Database;
import models.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    
    /* Completar consultas */
    static final String INSERT_QUERY ="INSERT INTO usuario(nombre,apellidos,dni) values (?,?,?)";
    static final String LIST_QUERY="SELECT * FROM usuario";
    static final String GET_BY_DNI="SELECT * FROM usuario where dni=?";
    
    
    public void connect(){
        try {
            
            this.connection= Database.getConnection();
            
            System.out.println("Base de datos conectada");

        }catch(Exception ex){

            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }     
    }
    
    public void close(){
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }



    public void save(Usuario user) {

        /**
         * Completar código para guardar un usuario 
         * Este método no debe generar el id del usuario, ya que la base
         * de datos es la que lo genera.
         * Una vez obtenido el id del usuario tras la consulta sql,
         * hay que modificarlo en el objeto que se pasa como parámetro 
         * de forma que pase a tener el id correcto.
         */


        try (var pst = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());

            int result = pst.executeUpdate();
            if (result == 1) {

                ResultSet rs = pst.getGeneratedKeys();

                System.out.println(rs);

                rs.next();

                user.setId(rs.getLong(1));

                System.out.println("Método save completado");

            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }







        public ArrayList<Usuario> list(){

        var salida = new ArrayList<Usuario>(0);
        
        /* Completar código para devolver un arraylist con todos los usuarios */

            try (Statement st = connection.createStatement()) {

                ResultSet rs = st.executeQuery(LIST_QUERY);


                while (rs.next()) {

                    Usuario t = new Usuario();

                    t.setId(rs.getLong("id"));
                    t.setDni(rs.getString("dni"));
                    t.setApellidos(rs.getString("apellidos"));
                    t.setNombre(rs.getString("nombre"));

                    //Se lo añadimos al arraylist
                    salida.add(t);


                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        
        System.out.println("Método list completado");
        
        return salida;
    }    
    
    public Usuario getByDNI(String dni){
        
        Usuario salida = new Usuario();
        
        /**
         * Completar código para devolver el usuario que tenga ese dni.
         * Si no existe, se debe devolver null
         */

        try(var pst = connection.prepareStatement(GET_BY_DNI)) {


            pst.setString(1, dni);

            //Ejecutamos la query
            ResultSet rs = pst.executeQuery();

            if(rs.next()){

                salida.setId(rs.getLong("id"));
                salida.setDni(rs.getString("dni"));
                salida.setApellidos(rs.getString("apellidos"));
                salida.setNombre(rs.getString("nombre"));

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        
        System.out.println("Método getByDNI completado");

        return salida;
    }    
}
