package com.galarza.gestorlogistica.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MovimientoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    private int cantidad;
    private LocalDateTime fechaHora = LocalDateTime.now();

    public enum TipoMovimiento { ingreso, egreso }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoMovimiento getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}