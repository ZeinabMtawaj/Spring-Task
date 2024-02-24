package com.spring.task.feature.purchase.mapper;



import com.spring.task.feature.customer.mapper.CustomerMapper;
import com.spring.task.feature.product.mapper.ProductMapper;
import com.spring.task.feature.purchase.Purchase;
import com.spring.task.feature.purchase.dto.PurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface PurchaseMapper {

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "product", target = "product")
    PurchaseDTO purchaseToPurchaseDTO(Purchase purchase);
}
