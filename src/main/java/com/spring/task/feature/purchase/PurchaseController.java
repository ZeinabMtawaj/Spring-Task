package com.spring.task.feature.purchase;

import com.spring.task.common.response.Response;
import com.spring.task.feature.purchase.dto.PurchaseDTO;
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
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Response> addPurchase(@RequestBody @Valid Purchase purchase) {
        PurchaseDTO purchaseDTO = purchaseService.savePurchase(purchase);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("purchase", purchaseDTO))
                        .message("Purchase Created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Response> getPurchaseByCode(@PathVariable String code) {
        PurchaseDTO purchase = purchaseService.getPurchaseByCode(code);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("purchase", purchase))
                        .message("Purchase Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/list/{limit}")
    public ResponseEntity<Response> listPurchases(@PathVariable int limit) {
        List<PurchaseDTO> purchases= (List<PurchaseDTO>) purchaseService.list(limit);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("purchases", purchaseService.list(limit)))
                        .message("Purchases retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
}
