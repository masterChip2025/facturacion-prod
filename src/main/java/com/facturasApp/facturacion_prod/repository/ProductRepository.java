package com.facturasApp.facturacion_prod.repository;

import com.facturasApp.facturacion_prod.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Producto, Integer> {
}
