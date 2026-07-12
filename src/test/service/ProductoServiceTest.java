package com.galarza.gestorlogistica.service;

import com.galarza.gestorlogistica.model.Producto;
import com.galarza.gestorlogistica.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {

    @Test
    void testGuardarProducto() {
        ProductoRepository repo = Mockito.mock(ProductoRepository.class);
        ProductoService service = new ProductoService(repo);

        Producto producto = new Producto();
        producto.setNombre("Test");
        Mockito.when(repo.save(producto)).thenReturn(producto);

        Producto guardado = service.guardar(producto);
        assertEquals("Test", guardado.getNombre());
    }
}