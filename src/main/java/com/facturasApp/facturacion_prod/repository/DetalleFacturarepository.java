package com.facturasApp.facturacion_prod.repository;

import com.facturasApp.facturacion_prod.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturarepository extends JpaRepository<DetalleFactura, Integer> {
}
