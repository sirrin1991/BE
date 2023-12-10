package com.example.loanttv.service.product;

import com.example.loanttv.model.IProductDTO;
import com.example.loanttv.model.ProductDTO;
import com.example.loanttv.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductsService implements IProductsService {
    @Autowired
    private IProductsRepository iProductRepository;

    @Override
    public Page<IProductDTO> getAllProduct(Pageable pageable, String searchName, String sizeName, Double minPrice, Double maxPrice) {
        return iProductRepository.getAllProduct(pageable, searchName, sizeName, minPrice, maxPrice);
    }

    @Override
    public IProductDTO getProduct(String productCode) {
        return iProductRepository.getProduct(productCode);
    }

    @Override
    public void addNewProduct(ProductDTO productDTO) {
        iProductRepository.addNewProduct(productDTO);
    }
}
