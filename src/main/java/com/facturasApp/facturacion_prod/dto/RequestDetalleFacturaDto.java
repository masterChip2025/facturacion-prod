package com.facturasApp.facturacion_prod.dto;

import lombok.Data;

@Data
public class RequestDetalleFacturaDto {
    private Integer idProducto;
    private Integer cantidad;
}
