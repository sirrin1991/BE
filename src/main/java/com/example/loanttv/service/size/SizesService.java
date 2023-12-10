package com.example.loanttv.service.size;

import com.example.loanttv.model.Sizes;
import com.example.loanttv.repository.ISizesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizesService implements ISizesService{
    @Autowired
    private ISizesRepository sizesRepository;
    @Override
    public List<Sizes> getAllSizes() {
        return sizesRepository.getAllSizes();
    }
}
