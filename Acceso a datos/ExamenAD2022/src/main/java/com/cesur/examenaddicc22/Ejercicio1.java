package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {

        int contadorlineas =0;
        int contadorCaracteres=0;

        String s;
        String trimeado;

        ArrayList<String> palabrasListLimpia = new ArrayList<>();

        String [] palabras = new String[100];
        /* añadir código */

        try(BufferedReader br = new BufferedReader( (new FileReader(archivo)))) {

            while (((s = br.readLine()) != null)) {

                contadorlineas++;

                trimeado=s.trim().replaceAll(" ","");
                System.out.println("Esta linea tiene "+trimeado.length());

                contadorCaracteres+=trimeado.length();



                //TODO falta contar las palabras




            }

            System.out.println("tiene "+contadorlineas+" lineas");

            System.out.println("tiene "+contadorCaracteres+" Carácteres Totales");

        } catch (IOException e) {
           //sad
        }


        System.out.println("Ejercicio no resuelto");
    }
    
}
