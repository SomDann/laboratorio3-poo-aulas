/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.laboratorio3.dao;

import uni.laboratorio3.entidades.Aula;
import uni.laboratorio3.entidades.EstadoAula;
import uni.laboratorio3.entidades.PlantaAula;
import uni.laboratorio3.entidades.TipoAula;
import uni.laboratorio3.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AulaDAO {

    public List<PlantaAula> listarPlantas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM PlantaAula ORDER BY nivel ASC", PlantaAula.class).list();
        }
    }

    public List<TipoAula> listarTipos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM TipoAula ORDER BY nombre ASC", TipoAula.class).list();
        }
    }

    public List<EstadoAula> listarEstados() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM EstadoAula ORDER BY nombre ASC", EstadoAula.class).list();
        }
    }

    public boolean existeAula(String nombre, String edificio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT count(a) FROM Aula a WHERE lower(a.nombre) = lower(:nom) AND lower(a.edificio) = lower(:edif)";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nom", nombre.trim());
            query.setParameter("edif", edificio.trim());
            return query.uniqueResult() > 0;
        }
    }

    public boolean guardar(Aula aula) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(aula);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}