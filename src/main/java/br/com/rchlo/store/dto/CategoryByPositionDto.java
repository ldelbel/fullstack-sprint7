package br.com.rchlo.store.dto;

public class CategoryByPositionDto {
    private final String name;
    private final String slug;

    public CategoryByPositionDto(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
