package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    private Long id;
    private String name;
    private String slug;
    private Integer position;


    public CategoryDto(Long id, String name, String slug, Integer position) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getPosition() {
        return position;
    }


}
