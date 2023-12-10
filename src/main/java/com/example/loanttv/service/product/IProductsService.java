package com.example.loanttv.service.product;

import com.example.loanttv.model.IProductDTO;
import com.example.loanttv.model.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductsService {
    Page<IProductDTO> getAllProduct(Pageable pageable, String searchName, String sizeName, Double minPrice, Double maxPrice);
    IProductDTO getProduct(String productCode);
    void addNewProduct(ProductDTO productDTO);
}
