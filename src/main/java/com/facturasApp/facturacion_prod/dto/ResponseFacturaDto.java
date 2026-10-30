package com.facturasApp.facturacion_prod.dto;

import com.facturasApp.facturacion_prod.model.DetalleFactura;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ResponseFacturaDto {
    private Integer id;
    private String numeroFactura;
    private BigDecimal subTotal;
    private BigDecimal total;
    private LocalDateTime fechaCreado;
    private Set<ResponseDetalleFacturaDto> detalleFactura;
}
