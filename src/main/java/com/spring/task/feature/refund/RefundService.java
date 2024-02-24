package com.spring.task.feature.refund;

import com.spring.task.feature.refund.dto.RefundDTO;

import java.util.Collection;

public interface RefundService {
    RefundDTO saveRefund(Refund refund);
    Collection<RefundDTO> list(int limit);
    RefundDTO getRefundById(Long id);
    RefundDTO getRefundByCode(String code);
}
