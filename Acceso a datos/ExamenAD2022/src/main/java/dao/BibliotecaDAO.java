package dao;

import java.util.ArrayList;
import java.util.HashSet;

import models.Ejemplar;
import models.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        try{

            /* Completar conexión con hibernate */
            try {
                //-------Configuracion hibernate--------

                Configuration cfg = new Configuration();
                cfg.configure();

                //Session factory
                sessionFactory = cfg.buildSessionFactory();


            }catch(Exception e){
                System.out.println(e.getMessage());

            }
            
            System.out.println("Conexión realizada");
        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public void saveLibro( Libro e ){
        
        /* Guarda un libro con todos sus ejemplares en la base de datos */


        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            s.persist(e);
            System.out.println(e);


            t.commit();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Método saveLibro implementado");
        
    }
  
    public HashSet<Libro> findByEstado(String estado){
        
        HashSet<Libro> salida = new HashSet<Libro>();

        ArrayList<Ejemplar> lista ;


        /* 
         Devuelve el conjunto de libros que tenga el estado indicado      
        */

        try (Session s = sessionFactory.openSession()) {

            Query<Ejemplar> q = s.createQuery("FROM Ejemplar where estado=:n", Ejemplar.class);

            q.setParameter("n", estado);

                lista = (ArrayList<Ejemplar>) q.getResultList();

            for (Ejemplar ejemplar : lista) {
                salida.add(ejemplar.getLibro());
            }



        } catch (Exception e) {

            System.out.println(e.getMessage());

        }


        System.out.println("Método findByEstado implementado");
        
        return salida;
        
    }
    
    public void printInfo(){


        System.out.println();
        
        /* 
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Debe imprimirlo de esta manera:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */

        ArrayList<Libro> lista ;


        /*
         Devuelve el conjunto de libros que tenga el estado indicado
        */

        try (Session s = sessionFactory.openSession()) {

            Query<Libro> q = s.createQuery("FROM Libro", Libro.class);

            lista = (ArrayList<Libro>) q.getResultList();

            System.out.println("Biblioteca");
            System.out.println("----------");
            for (Libro libro : lista) {
                System.out.println(libro.getTitulo() +" ("+libro.getEjemplarList().size()+")");
            }



        } catch (Exception e) {

            System.out.println(e.getMessage());

        }






        System.out.println("Método printInfo implementado");
        
    }
    
}
