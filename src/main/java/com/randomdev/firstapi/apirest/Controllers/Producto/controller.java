package com.randomdev.firstapi.apirest.Controllers.Producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.randomdev.firstapi.apirest.Repositories.ProductosRepository;
import com.randomdev.firstapi.apirest.Entities.Productos;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
public class controller {
    @Autowired
    private ProductosRepository productoRepository; // esto viene del repositorio

    @GetMapping
    public List<Productos> getAllProducts() { // getAll devuelve una lista de productos. Este producto viene de entidad
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Productos getProductsById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el id en base de datos"));
    }

    @PostMapping
    public Productos createProducts(@RequestBody Productos producto) {
        return productoRepository.save(producto); // producto es el body request
    }

    @PutMapping("/{id}")
    public Productos updateProducts(@PathVariable Long id, @RequestBody Productos producto) {
        Productos productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el id en base de datos"));

        productoEncontrado.setNombre(producto.getNombre());// obtenemos el nombre y el precio del body y actualiza la
                                                           // lista
        productoEncontrado.setPrecio(producto.getPrecio());

        return productoRepository.save(productoEncontrado);
    }

    @DeleteMapping("/{id}")
    public String deleteProductsById(@PathVariable Long id) {

        Productos productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el id en base de datos"));
        productoRepository.delete(productoEncontrado);

        return "El producto ha sido eliminado exitosamente";
    }
}
