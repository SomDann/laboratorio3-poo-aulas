/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.laboratorio3.util;

/**
 *
 * @author Usuario
 */


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author tono2
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure() 
                        .buildSessionFactory();
            } catch (Exception ex) {
                System.err.println("Error al crear la SessionFactory.");
                throw new IllegalStateException("No se pudo conectar con la base de datos", ex);
            }
        }
        return sessionFactory;
    }

    
    public static synchronized void cerrar() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}
