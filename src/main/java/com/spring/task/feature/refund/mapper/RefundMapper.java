package com.spring.task.feature.refund.mapper;



import com.spring.task.feature.customer.mapper.CustomerMapper;
import com.spring.task.feature.product.mapper.ProductMapper;
import com.spring.task.feature.purchase.mapper.PurchaseMapper;
import com.spring.task.feature.refund.Refund;
import com.spring.task.feature.refund.dto.RefundDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class, PurchaseMapper.class})
public interface RefundMapper {

//    @Mapping(source = "customer", target = "customer")
//    @Mapping(source = "product", target = "product")
    @Mapping(source = "purchase", target = "purchase")
    RefundDTO refundToRefundDTO(Refund refund);
}
