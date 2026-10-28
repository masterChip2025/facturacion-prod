package com.facturasApp.facturacion_prod.service;

import com.facturasApp.facturacion_prod.dto.ProductoDto;
import com.facturasApp.facturacion_prod.model.Producto;
import com.facturasApp.facturacion_prod.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductoService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductoDto save(ProductoDto productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class);

        return modelMapper.map(productRepository.save(producto), ProductoDto.class);
    }

    public List<ProductoDto> findAll(){
        return productRepository.findAll().stream().map(producto-> modelMapper.map(producto, ProductoDto.class)
        ).collect(Collectors.toList());
    }

    public Optional<ProductoDto> findById(Integer Id){
        return productRepository.findById(Id).map(producto -> {
            return modelMapper.map(producto, ProductoDto.class);
        });
    }

    public boolean deleteById(Integer id){
        return productRepository.findById(id).map(producto -> {
            productRepository.delete(producto);
            return true;
        }).orElse(false);
    }

    public Optional<ProductoDto> update(ProductoDto productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class);
        return productRepository.findById(producto.getId()).map(productoDB->{
            return modelMapper.map(productRepository.save(producto), ProductoDto.class);
        });
    }
}
