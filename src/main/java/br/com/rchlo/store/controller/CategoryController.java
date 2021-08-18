package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.CategoryByPositionDto;
import br.com.rchlo.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public List<CategoryByPositionDto> categories() {
        return categoryRepository.findAllByOrderByPosition();
    };

}
