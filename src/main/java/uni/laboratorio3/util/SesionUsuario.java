/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.laboratorio3.util;

import uni.laboratorio3.entidades.Usuario;

/**
 * @author tono2
 */
public class SesionUsuario {

    private static Usuario usuarioActual;

    public static void iniciar(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrar() {
        usuarioActual = null;
    }
}
