/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package uni.laboratorio3.dao;

import uni.laboratorio3.entidades.Usuario;
import uni.laboratorio3.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioDAO {

    public Usuario autenticar(String correo, String clavePlana) {
        String claveHash = cifrarMD5(clavePlana);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Usuario u WHERE u.correo = :correo AND u.clave = :clave AND u.estado = 'ACTIVO'";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("clave", claveHash);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String cifrarMD5(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar clave en MD5", e);
        }
    }
}