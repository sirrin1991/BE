package com.example.loanttv.model;

import com.example.loanttv.service.category.ICategoriesService;
import com.example.loanttv.service.product.IProductsService;
import com.example.loanttv.service.size.ISizesService;
import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class ProductDTO implements Validator {
    @NotBlank(message = "Vui lòng không để trống mã sản phẩm")
    @Pattern(regexp = "^P-[0-9]{4}$", message = "Mã sản phẩm không đúng định dạng P-XXXX (X là các chữ số từ 0 đến 9)")
    private String productCode;
    @NotBlank(message = "Vui lòng không để trống tên sản phẩm")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Tên sản phẩm chỉ được chứa chữ cái")
    @Size(min = 4, message = "Tên sản phẩm phải lớn hơn 4 ký tự")
    @Size(max = 50, message = "Tên sản phẩm phải nhỏ hơn 50 ký tự")
    private String name;
    @NotBlank(message = "Vui lòng không để trống hình ảnh sản phẩm")
    private String productImage;
    @NotBlank(message = "Vui lòng không để trống mã QR")
    private String productQRCode;
    @NotNull(message = "Vui lòng chọn giới tính sản phẩm")
    private Boolean gender;
    @NotNull(message = "Vui lòng nhập giá sản phẩm")
    @Min(value = 100000, message = "Giá sản phẩm không thể thấp hơn 100.000 vnd")
    @Max(value = 100000000, message = "Giá sản phẩm không thể thấp hơn 100.000.000 vnd")
    private Double price;
    @NotNull(message = "Vui lòng chọn loại sản phẩm")
    private Long categoryId;
    private LocalDate createdAt;
    @NotNull(message = "Vui lòng chọn size sản phẩm")
    private Set<Long> setSizeId;
    private ICategoriesService categoryService;
    private ISizesService sizesService;
    private IProductsService productsService;

    public void setProductsService(IProductsService productsService) {
        this.productsService = productsService;
    }

    public void setCategoryService(ICategoriesService categoryService) {
        this.categoryService = categoryService;
    }

    public void setSizesService(ISizesService sizesService) {
        this.sizesService = sizesService;
    }

    public ProductDTO() {
    }

    public ProductDTO(String productCode, String name, String productImage,
                      String productQRCode, Boolean gender, Double price, Long categoryId,
                      LocalDate createdAt, Set<Long> setSizeId) {
        this.productCode = productCode;
        this.name = name;
        this.productImage = productImage;
        this.productQRCode = productQRCode;
        this.gender = gender;
        this.price = price;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.setSizeId = setSizeId;
    }

    public Set<Long> getSetSizeId() {
        return setSizeId;
    }

    public void setSetSizeId(Set<Long> setSizeId) {
        this.setSizeId = setSizeId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductQRCode() {
        return productQRCode;
    }

    public void setProductQRCode(String productQRCode) {
        this.productQRCode = productQRCode;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        Long categoryId = productDTO.getCategoryId();
        Set<Long> sizeId = productDTO.getSetSizeId();
        List<Long> sizeIdInDB = sizesService.getAllSizes().stream().map(Sizes::getId).toList();
        boolean categoryIdIsExist = categoryService.getAllCategories().stream().anyMatch(x -> Objects.equals(x.getId(), categoryId));
        if(!categoryIdIsExist){
            errors.rejectValue("categoryId","not exist" ,"Mã loại sản phẩm không tồn tại");
        }
        for(Long id : sizeId){
            if(!sizeIdInDB.contains(id)){
                errors.rejectValue("setSizeId","not exist" ,"Mã size không tồn tại");
            }
        }
        if(productsService.getProduct(productDTO.productCode) != null){
            errors.rejectValue("productCode","existed" ,"Mã sản phẩm đã tồn tại");
        }

    }
}
