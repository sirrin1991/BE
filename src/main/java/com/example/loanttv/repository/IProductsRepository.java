package com.example.loanttv.repository;

import com.example.loanttv.model.IProductDTO;
import com.example.loanttv.model.ProductDTO;
import com.example.loanttv.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IProductsRepository extends JpaRepository<Products,Long> {
    @Query(value = "select p.product_code as productCode, p.name  as productName," +
            " p.price as productPrice, sd.quantity as productQuantity, s.name as productSize\n" +
            "from products p\n" +
            "join size_details sd on sd.product_id = p.id\n" +
            "join sizes s on sd.size_id = s.id " +
            "where p.name like %:productName% and s.name like %:sizeName%\n" +
            "and p.price between :minPrice and :maxPrice\n" +
            "order by p.created_at",nativeQuery = true)
    Page<IProductDTO> getAllProduct(Pageable pageable, String productName, String sizeName, Double minPrice, Double maxPrice);

    @Query(value = "select p.id from products p where p.product_code = :productCode ",nativeQuery = true)
    IProductDTO getProduct(String productCode);

    @Transactional
    @Modifying
    @Query(value = "Insert into products (product_code,name,gender,price,product_image,productqrcode,category_id,created_at) " +
            "values ( :#{#productDTO.productCode}, :#{#productDTO.name}, :#{#productDTO.gender}, " +
            ":#{#productDTO.price}, :#{#productDTO.productImage}, :#{#productDTO.productQRCode}, :#{#productDTO.categoryId}, " +
            ":#{#productDTO.createdAt})",nativeQuery = true)
    void addNewProduct(@Param("productDTO") ProductDTO productDTO);


}
