package com.spring.task.feature.purchase;

import com.spring.task.exception.ResourceNotFoundException;
import com.spring.task.feature.product.Product;
import com.spring.task.feature.purchase.dto.PurchaseDTO;
import com.spring.task.feature.purchase.mapper.PurchaseMapper;
import com.spring.task.repository.CustomerRepository;
import com.spring.task.repository.ProductRepository;
import com.spring.task.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private final PurchaseMapper purchaseMapper;



    @Override
    public PurchaseDTO savePurchase(Purchase purchaseRequest) {
        Purchase purchase = new Purchase();

        purchase.setCustomer(customerRepository.findByCode(purchaseRequest.getCustomer().getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with code: " + purchaseRequest.getCustomer().getCode())));
        Product product = productRepository.findByCode(purchaseRequest.getProduct().getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + purchaseRequest.getProduct().getCode()));


//        // Check if the amount is divisible by the product price
//        if (purchaseRequest.getAmount() % product.getPrice() != 0) {
//            throw new IllegalArgumentException("Amount must be divisible by the product price");
//        }
        purchase.setProduct(product);
        purchase.setAmount(purchaseRequest.getAmount());


        Purchase savedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.purchaseToPurchaseDTO(savedPurchase);
    }




    @Override
    public PurchaseDTO getPurchaseById(Long id) {

        Purchase purchase = purchaseRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with id " + id));

        return purchaseMapper.purchaseToPurchaseDTO(purchase);
    }

    @Override
    public PurchaseDTO getPurchaseByCode(String code) {
        Purchase purchase = purchaseRepository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with code " + code));
        return purchaseMapper.purchaseToPurchaseDTO(purchase);

    }

    @Override
    public Collection<PurchaseDTO> list(int limit) {
        return purchaseRepository.findAll(PageRequest.of(0, limit)).toList()
                .stream()
                .map(purchaseMapper::purchaseToPurchaseDTO)
                .collect(Collectors.toList());
    }


}
