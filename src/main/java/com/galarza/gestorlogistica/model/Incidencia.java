package com.galarza.gestorlogistica.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Usuario usuario;

    private String descripcion;
    private LocalDateTime fecha = LocalDateTime.now();

    private String gravedad; // Puede ser null
    private String estado;   // Puede ser null

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getGravedad() { return gravedad; }
    public void setGravedad(String gravedad) { this.gravedad = gravedad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}