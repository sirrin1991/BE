package com.example.loanttv.repository;

import com.example.loanttv.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoriesRepository extends JpaRepository<Categories,Long> {
    @Query(value = "select * from categories",nativeQuery = true)
    List<Categories> getAllCategories();
}
