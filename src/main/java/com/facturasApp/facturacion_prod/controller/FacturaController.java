package com.facturasApp.facturacion_prod.controller;

import com.facturasApp.facturacion_prod.dto.RequestFacturaDto;
import com.facturasApp.facturacion_prod.dto.ResponseFacturaDto;
import com.facturasApp.facturacion_prod.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    private final FacturaService facturaService;
    public FacturaController(FacturaService _facturaService) {
        this.facturaService = _facturaService;
    }

    @PostMapping
    public ResponseFacturaDto save(@RequestBody RequestFacturaDto requestFacturaDto){
        System.out.println(requestFacturaDto);
        return facturaService.save(requestFacturaDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseFacturaDto>> findAll(){
        List<ResponseFacturaDto> facturaDto = facturaService.findAll();
        if(facturaDto.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(facturaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFacturaDto> findById(@PathVariable Integer id){
        Optional<ResponseFacturaDto> factura = facturaService.findById(id);

        return factura.map(
                ResponseEntity::ok
        ).orElseGet(
                ()-> ResponseEntity.notFound().build()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        facturaService.deleteById(id);
    }





}
