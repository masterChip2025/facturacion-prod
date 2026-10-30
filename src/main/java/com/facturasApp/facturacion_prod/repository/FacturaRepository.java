package com.facturasApp.facturacion_prod.repository;

import com.facturasApp.facturacion_prod.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}
