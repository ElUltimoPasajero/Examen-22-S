package com.cesur.examenaddicc22.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {




    private static SessionFactory sf = null;

    static{

        try {
            //-------Configuracion hibernate--------

            Configuration cfg = new Configuration();
            cfg.configure();

            //Session factory
            sf = cfg.buildSessionFactory();


        }catch(Exception e){
            System.out.println(e.getMessage());


        }

    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }




}
