package com.spring.task.feature.purchase;

import com.spring.task.feature.purchase.dto.PurchaseDTO;

import java.util.Collection;
import java.util.List;



public interface PurchaseService {
    PurchaseDTO savePurchase(Purchase purchase);
    Collection<PurchaseDTO> list(int limit);
    PurchaseDTO getPurchaseById(Long id);
    PurchaseDTO getPurchaseByCode(String code);

}

