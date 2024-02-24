package com.spring.task.feature.product;


import com.spring.task.common.response.Response;
import com.spring.task.feature.product.dto.ProductDTO;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Response> addProduct(@RequestBody @Valid Product product) {
        ProductDTO productDTO = productService.saveProduct(product);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("product", productDTO))
                        .message("Product Created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Response> getProductByCode(@PathVariable String code) {
        ProductDTO product = productService.getProductByCode(code);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("product", product))
                        .message("Product Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PutMapping("/{code}")
    public ResponseEntity<Response> updateProduct(@PathVariable String code, @RequestBody @Valid Product productDetails) {
        ProductDTO updatedProduct = productService.updateProduct(code, productDetails);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("product", updatedProduct))
                        .message("Product Updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Response> deleteProduct(@PathVariable String code) {
        boolean deleted = productService.deleteProduct(code);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("delete", deleted))
                        .message("Product Deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/list/{limit}")
    public ResponseEntity<Response> listProducts(@PathVariable int limit) {
        List<ProductDTO> products = (List<ProductDTO>) productService.list(limit);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("products", products))
                        .message("Products retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
}

