package br.com.rchlo.store.repository;

import Factory.ProductFactory;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/schema.sql")
@ActiveProfiles("test")
class ProductRepositoryTest {

    private ProductFactory factory = new ProductFactory();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldListAllProductsOrderedByName() {
        List<Product> products = productRepository.findAllByOrderByName();

        assertEquals(2, products.size());

        Product firstProduct = products.get(0);
        assertEquals(7L, firstProduct.getCode());
        assertEquals("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco", firstProduct.getName());

        Product secondProduct = products.get(1);
        assertEquals(1L, secondProduct.getCode());
        assertEquals("Regata Infantil Mario Bros Branco", secondProduct.getName());

        entityManager.clear();
    }

    @Test
    public void shouldReturnReportOfProductsByColor() {
        List<ProductByColorDto> report = productRepository.productsByColor();

        assertEquals("Azul", report.get(0).getColor());
        assertEquals("Branca", report.get(1).getColor());
        assertEquals(2, report.size());

        entityManager.clear();
    }


    @BeforeEach
    void setup() {
        entityManager.persist(factory.knitJacket());
        entityManager.persist(factory.knitTankTop());
    }
}