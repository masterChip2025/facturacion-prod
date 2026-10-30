package com.facturasApp.facturacion_prod.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RequestFacturaDto {
    private Integer id;
    private String numeroFactura;
    private Set<RequestDetalleFacturaDto> detalleFactura;
}
