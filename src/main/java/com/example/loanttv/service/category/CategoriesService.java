package com.example.loanttv.service.category;

import com.example.loanttv.model.Categories;
import com.example.loanttv.repository.ICategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesService {
    @Autowired
    private ICategoriesRepository iCategoryRepository;
    @Override
    public List<Categories> getAllCategories() {
        return iCategoryRepository.getAllCategories();
    }
}
