package br.com.rchlo.store.domain;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}
