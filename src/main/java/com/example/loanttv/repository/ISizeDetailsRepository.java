package com.example.loanttv.repository;

import com.example.loanttv.model.SizeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ISizeDetailsRepository extends JpaRepository<SizeDetails,Long> {
    @Modifying
    @Query(value = "INSERT INTO `size_details` ( `product_id`, `size_id`)\n" +
            "values (:productId, :sizeId) ",nativeQuery = true)
    void addNewSizeDetail( Long productId, Long sizeId);
}
