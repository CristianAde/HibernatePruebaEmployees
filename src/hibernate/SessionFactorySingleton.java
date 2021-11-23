package hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian
 */
public class SessionFactorySingleton {
    
    public static final SessionFactory sessionFactory;
    
    static{
        sessionFactory = new Configuration().configure().buildSessionFactory();       
    }
 
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
}
