package com.example.loanttv.service.size_detail;

import com.example.loanttv.model.SizeDetails;
import com.example.loanttv.repository.ISizeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeDetailsService implements ISizeDetailsService{
    @Autowired
    private ISizeDetailsRepository iSizeDetails;
    @Override
    public void addNewSizeDetail( Long productId, Long sizeId) {
        iSizeDetails.addNewSizeDetail(productId,sizeId);
    }
}
