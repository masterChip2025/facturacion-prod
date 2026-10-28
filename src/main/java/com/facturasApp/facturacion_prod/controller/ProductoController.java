package com.facturasApp.facturacion_prod.controller;

import com.facturasApp.facturacion_prod.dto.ProductoDto;
import com.facturasApp.facturacion_prod.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDto> save(@RequestBody ProductoDto productoDto){
        return new ResponseEntity<ProductoDto>
                (productoService.save(productoDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> findAll(){
        List<ProductoDto> productosDtos = productoService.findAll();
        if(productosDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productosDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable Integer id){
        return productoService.findById(id).map(
                ResponseEntity::ok
        ).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        if(productoService.deleteById(id)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<ProductoDto> update(@RequestBody ProductoDto producto){
        return productoService.update(producto).map(
                ResponseEntity::ok
        ).orElseGet(()->ResponseEntity.notFound().build());
    }
}
