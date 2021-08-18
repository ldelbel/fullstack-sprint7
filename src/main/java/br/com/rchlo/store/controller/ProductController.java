package br.com.rchlo.store.controller;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.dto.ProductDto;
import br.com.rchlo.store.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @Cacheable(value = "productDtoList")
    public List<ProductDto> products(
            @PageableDefault(sort="name", direction = Sort.Direction.ASC, page = 0, size = 3) Pageable pagination
    ) {
        Page<Product> productList = productRepository.findAll(pagination);
        return ProductDto.convertToDto(productList);
    }

    @GetMapping("/clear-cache")
    @CacheEvict(value = "productDtoList", allEntries = true)
    public String clearProductsCache() {
        return "Products cache was cleaned successfully";
    };

    @GetMapping("/reports/by-color")
    public List<ProductByColorDto> productByColorReport() {
        return productRepository.productsByColor();
    }

}
