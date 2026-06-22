package com.examen.parcial1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByCategoriaAndUnidadesDisponiblesGreaterThan(String categoria, Integer unidades);

    List<Vehiculo> findByPrecioPorDiaBetweenOrderByPrecioPorDiaAsc(Double min, Double max);

    List<Vehiculo> findByModeloContainingIgnoreCase(String modelo);
}
