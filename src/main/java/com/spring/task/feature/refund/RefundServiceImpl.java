package com.spring.task.feature.refund;

import com.spring.task.exception.ResourceNotFoundException;
import com.spring.task.feature.purchase.Purchase;
import com.spring.task.feature.refund.dto.RefundDTO;
import com.spring.task.feature.refund.mapper.RefundMapper;
import com.spring.task.repository.CustomerRepository;
import com.spring.task.repository.ProductRepository;
import com.spring.task.repository.PurchaseRepository;
import com.spring.task.repository.RefundRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final PurchaseRepository purchaseRepository;

    private final RefundMapper refundMapper;

    @Override
    public RefundDTO saveRefund(Refund refundRequest) {
        Refund refund = new Refund();

        Purchase purchase = purchaseRepository.findByCode(refundRequest.getPurchase().getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with code: " + refundRequest.getPurchase().getCode()));

        // Check if the refund amount is greater than the purchase amount
        if (refundRequest.getAmount() > purchase.getAmount()) {
            throw new IllegalArgumentException("Refund amount cannot be greater than the purchase amount");
        }



        // Check if there are previous refunds for the same purchase and if it has all the amount
        Double totalRefundedAmount = refundRepository.findByPurchase(purchase).stream()
                .mapToDouble(Refund::getAmount)
                .sum();
        if (totalRefundedAmount + refundRequest.getAmount() > purchase.getAmount()) {
            throw new IllegalArgumentException("Total refund amount cannot exceed the purchase amount");
        }

        refund.setPurchase(purchase);
        refund.setCustomer(purchase.getCustomer());
        refund.setProduct(purchase.getProduct());
        refund.setAmount(refundRequest.getAmount());

        Refund savedRefund = refundRepository.save(refund);
        return refundMapper.refundToRefundDTO(savedRefund);
    }


    @Override
    public Collection<RefundDTO> list(int limit) {
        return refundRepository.findAll(PageRequest.of(0, limit)).toList()
                .stream()
                .map(refundMapper::refundToRefundDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long id) {
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Refund not found with id " + id));
        return refundMapper.refundToRefundDTO(refund);
    }

    @Override
    public RefundDTO getRefundByCode(String code) {
        Refund refund = refundRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Refund not found with code " + code));
        return refundMapper.refundToRefundDTO(refund);
    }
}
