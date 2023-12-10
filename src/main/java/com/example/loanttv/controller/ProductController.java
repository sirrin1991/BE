package com.example.loanttv.controller;


import com.example.loanttv.model.IProductDTO;
import com.example.loanttv.model.ProductDTO;
import com.example.loanttv.service.category.ICategoriesService;
import com.example.loanttv.service.product.IProductsService;
import com.example.loanttv.service.size.ISizesService;
import com.example.loanttv.service.size_detail.ISizeDetailsService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;


@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductsService iProductsService;
    @Autowired
    private ISizeDetailsService iSizeDetailsService;
    @Autowired
    private ICategoriesService iCategoriesService;
    @Autowired
    private ISizesService iSizesService;

    @GetMapping("/list")
    public ResponseEntity<Page<IProductDTO>> showListProduct(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "4") int size,
                                                             @RequestParam(defaultValue = "", required = false) String productName,
                                                             @RequestParam(defaultValue = "", required = false) String sizeName,
                                                             @RequestParam(required = false) Double
                                                                         minPrice,
                                                             @RequestParam(required = false) Double maxPrice) {

        maxPrice = maxPrice == null ? 1000000000d : maxPrice;
        minPrice = minPrice == null ? 0 : minPrice;
        Pageable pageable = PageRequest.of(page, size);
        Page<IProductDTO> productDTOPage = iProductsService.getAllProduct(pageable, productName, sizeName, minPrice, maxPrice);
        return productDTOPage.isEmpty() ?
                new ResponseEntity<>(productDTOPage, HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(productDTOPage, HttpStatus.OK);
//        return new ResponseEntity<>(productDTOPage, HttpStatus.OK)
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
       ProductDTO productDTO1 = new ProductDTO();
       productDTO1.setSizesService(iSizesService);
       productDTO1.setCategoryService(iCategoriesService);
       productDTO1.setProductsService(iProductsService);
       productDTO1.validate(productDTO,bindingResult);
        if(bindingResult.hasErrors()){
            HashMap<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> response.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        LocalDate localDate = new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println(localDate);
        productDTO.setCreatedAt(localDate);
        iProductsService.addNewProduct(productDTO);
        Long id = iProductsService.getProduct(productDTO.getProductCode()).getId();
        for (Long value : productDTO.getSetSizeId()) {
            iSizeDetailsService.addNewSizeDetail(id, value);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
