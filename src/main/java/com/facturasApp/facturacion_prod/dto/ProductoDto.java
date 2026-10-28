package com.facturasApp.facturacion_prod.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDto {

    private Integer id;
    private String nombre;
    private String detalle;
    private BigDecimal precio;

}
