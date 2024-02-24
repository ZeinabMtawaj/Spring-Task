package com.spring.task.feature.refund;

import com.spring.task.common.response.Response;
import com.spring.task.feature.purchase.Purchase;
import com.spring.task.feature.refund.Refund;
import com.spring.task.feature.refund.RefundService;
import com.spring.task.feature.refund.dto.RefundDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/refund")
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public ResponseEntity<Response> addRefund(@RequestBody @Valid Refund refund) {
        RefundDTO refundDTO = refundService.saveRefund(refund);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("refund", refundDTO))
                        .message("Refund Created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Response> getRefundByCode(@PathVariable String code) {
        RefundDTO refund = refundService.getRefundByCode(code);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("refund", refund))
                        .message("Refund Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/list/{limit}")
    public ResponseEntity<Response> listRefunds(@PathVariable int limit) {
        List<RefundDTO> refunds= (List<RefundDTO>) refundService.list(limit);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("refunds", refunds))
                        .message("Refunds retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
}
