/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
package uni.laboratorio3.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "planta_aula")
public class PlantaAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer nivel;

    public PlantaAula() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }

    @Override
    public String toString() {
        return "Nivel " + nivel;
    }
}