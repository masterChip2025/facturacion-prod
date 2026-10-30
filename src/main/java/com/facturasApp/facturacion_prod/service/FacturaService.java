package com.facturasApp.facturacion_prod.service;

import com.facturasApp.facturacion_prod.dto.RequestDetalleFacturaDto;
import com.facturasApp.facturacion_prod.dto.RequestFacturaDto;
import com.facturasApp.facturacion_prod.dto.ResponseFacturaDto;
import com.facturasApp.facturacion_prod.model.DetalleFactura;
import com.facturasApp.facturacion_prod.model.Factura;
import com.facturasApp.facturacion_prod.model.Producto;
import com.facturasApp.facturacion_prod.repository.FacturaRepository;
import com.facturasApp.facturacion_prod.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public FacturaService(FacturaRepository _facturaRepository, ProductRepository _productRepository, ModelMapper _modelMapper) {
        this.facturaRepository = _facturaRepository;
        this.productRepository = _productRepository;
        this.modelMapper = _modelMapper;
    }


    public List<ResponseFacturaDto> findAll(){
        return facturaRepository.findAll().stream().map(factura ->
             modelMapper.map(factura, ResponseFacturaDto.class)
        ).collect(Collectors.toList());
    }

    public Optional<ResponseFacturaDto> findById(Integer id){
        return facturaRepository.findById(id).map(factura ->
            modelMapper.map(factura, ResponseFacturaDto.class)
        );
    }

    public void deleteById(Integer id){
        facturaRepository.deleteById(id);
    }

    @Transactional
    public ResponseFacturaDto save(RequestFacturaDto requestFacturaDto){
        Factura factura = new Factura();
        BigDecimal subTotalFactura = BigDecimal.ZERO;
        Set<DetalleFactura>  detalles = new HashSet<>();

        factura.setNumeroFactura(requestFacturaDto.getNumeroFactura());


        for(RequestDetalleFacturaDto detalleFacturaDto: requestFacturaDto.getDetalleFactura())
        {
            Producto producto = productRepository.findById(detalleFacturaDto.getIdProducto()).orElseThrow(
                    ()->new RuntimeException("Producto no encontrado.")
                    );
            BigDecimal totalProducto = producto.getPrecio().multiply(BigDecimal.valueOf(detalleFacturaDto.getCantidad()));
            subTotalFactura = subTotalFactura.add(totalProducto);

            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setIdProducto(detalleFacturaDto.getIdProducto());
            detalleFactura.setPrecio(producto.getPrecio());
            detalleFactura.setCantidad(detalleFacturaDto.getCantidad());
            detalleFactura.setTotal(totalProducto);
            detalleFactura.setFactura(factura);

            detalles.add(detalleFactura);
            System.out.println(detalleFactura);
        }

        factura.setDetalleFactura(detalles);
        factura.setSubTotal(subTotalFactura);
        factura.setTotal(subTotalFactura.add(subTotalFactura.multiply(BigDecimal.valueOf(factura.getIVA()))));

        Factura saveFactura = facturaRepository.save(factura);

        return modelMapper.map(saveFactura, ResponseFacturaDto.class);
    }

}
