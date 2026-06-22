package com.examen.parcial1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class VehiculoController {

    @Autowired
    private VehiculoRepository repository;

    @PostMapping
    public Vehiculo crear(@Valid @RequestBody Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    @GetMapping
    public List<Vehiculo> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Long id, @Valid @RequestBody Vehiculo detalles) {
        return repository.findById(id).map(v -> {
            v.setModelo(detalles.getModelo());
            v.setCategoria(detalles.getCategoria());
            v.setDescripcion(detalles.getDescripcion());
            v.setPrecioPorDia(detalles.getPrecioPorDia());
            v.setUnidadesDisponibles(detalles.getUnidadesDisponibles());
            return ResponseEntity.ok(repository.save(v));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busqueda/disponibles")
    public List<Vehiculo> buscarPorCategoriaYDisponibilidad(@RequestParam String categoria,
            @RequestParam Integer minUnidades) {
        return repository.findByCategoriaAndUnidadesDisponiblesGreaterThan(categoria, minUnidades);
    }

    @GetMapping("/busqueda/precio")
    public List<Vehiculo> buscarPorRangoPrecio(@RequestParam Double min, @RequestParam Double max) {
        return repository.findByPrecioPorDiaBetweenOrderByPrecioPorDiaAsc(min, max);
    }

    @GetMapping("/busqueda/modelo")
    public List<Vehiculo> buscarPorModelo(@RequestParam String modelo) {
        return repository.findByModeloContainingIgnoreCase(modelo);
    }
}
