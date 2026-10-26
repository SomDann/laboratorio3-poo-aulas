/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.laboratorio3.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "aulas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre", "edificio"})
})
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String edificio;

    @Column(nullable = false)
    private Integer planta;

    @Column(nullable = false)
    private Integer capacidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoAula tipoAula;

    @Column(nullable = false, length = 100)
    private String responsable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoAula estado;

    public Aula() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }
    public Integer getPlanta() { return planta; }
    public void setPlanta(Integer planta) { this.planta = planta; }
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public TipoAula getTipoAula() { return tipoAula; }
    public void setTipoAula(TipoAula tipoAula) { this.tipoAula = tipoAula; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public EstadoAula getEstado() { return estado; }
    public void setEstado(EstadoAula estado) { this.estado = estado; }
}