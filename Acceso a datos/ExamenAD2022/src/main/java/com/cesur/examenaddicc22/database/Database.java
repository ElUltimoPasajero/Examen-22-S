package com.cesur.examenaddicc22.database;
import lombok.Getter;
import models.Ejemplar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    //Logger para trazar la aplicacion, estatico para llamar siempre al mismo logger


    /**
     * -- GETTER --
     *  Metodo para devolver la conexion
     *
     * @return
     */
    @Getter
    private static final Connection connection;

    static{


        //String que almacena la url donde nos conectamos
        String url;
        //Variable para el usuario que por defecto es root
        String user;
        //Variable para la contraseña
        String password;

        //Nos traemos el archivo properties
        var config = new Properties();

        try {
            //Cargamos el fichero properties que esta en la carpeta resource
            config.load(Ejemplar.class.getClassLoader().getResourceAsStream("ddbb.properties"));

            url="jdbc:mysql://"+config.getProperty("host")+":"+config.getProperty("port")+"/"+config.getProperty("dbname");
            //depuramos
            //pasamos el user
            user=config.getProperty("user");
            //pasamos el pass
            password=config.getProperty("pass");



        } catch (IOException e) {
            throw new RuntimeException(e);

        }



        try {

            //Con el driver pasamos la conexion a la variable
            connection = DriverManager.getConnection(url, user, password);

            //Trazamos con el logger

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}


