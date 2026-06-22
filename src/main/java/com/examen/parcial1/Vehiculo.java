package com.examen.parcial1;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 3, max = 100, message = "El modelo debe tener entre 3 y 100 caracteres")
    private String modelo;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio por día es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double precioPorDia;

    @NotNull(message = "Las unidades disponibles son obligatorias")
    @Min(value = 0, message = "Las unidades no pueden ser negativas")
    private Integer unidadesDisponibles;
}
