package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Category;
import br.com.rchlo.store.dto.CategoryByPositionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<CategoryByPositionDto> findAllByOrderByPosition();
}
