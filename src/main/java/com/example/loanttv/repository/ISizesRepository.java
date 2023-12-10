package com.example.loanttv.repository;

import com.example.loanttv.model.Categories;
import com.example.loanttv.model.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISizesRepository extends JpaRepository<Sizes,Long> {
    @Query(value = "select * from sizes",nativeQuery = true)
    List<Sizes> getAllSizes();
}
