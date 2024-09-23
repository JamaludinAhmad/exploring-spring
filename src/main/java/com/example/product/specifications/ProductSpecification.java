package com.example.product.specifications;

import com.example.product.entities.Category;
import com.example.product.entities.Product;
import com.example.product.entities.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasName(String name){
        return ((root, query, criteriaBuilder) -> name == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

    public static  Specification<Product> hasGreaterThan(int stock){
        return (root, query, criteriaBuilder) -> stock == 0 ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stock);
    }

    public static Specification<Product> hasCreateByUsername(String name){
        return (root, query, criteriaBuilder) -> {

            Join<Product, User> users = root.join("createdBy");

            return (name == null) ? null : criteriaBuilder.equal(users.get("username"), name);
        };
    }

    public static Specification<Product> hasCategoryName(Long id){
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> categories = root.join("category");

            return (id == null) ? null : criteriaBuilder.equal(categories.get("id"), id);
        };
    }

}
