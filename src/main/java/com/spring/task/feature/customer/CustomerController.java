package com.spring.task.feature.customer;

import com.spring.task.common.response.Response;
import com.spring.task.feature.customer.dto.CustomerDTO;
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
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Response> addCustomer(@RequestBody @Valid Customer customer) {
        CustomerDTO customerDTO = customerService.saveCustomer(customer);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("customer", customerDTO))
                        .message( "Customer Created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }


    @GetMapping("/{code}")
    public ResponseEntity<Response> getCustomerByCode(@PathVariable String code) {
        CustomerDTO customer = customerService.getCustomerByCode(code);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("customer",customer))
                        .message("Customer Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("/{code}")
    public ResponseEntity<Response> updateCustomer(@PathVariable String code, @RequestBody @Valid Customer customerDetails) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(code, customerDetails);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("customer",updatedCustomer))
                        .message("Customer Updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable String code) {
        boolean deleted = customerService.deleteCustomer(code);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("delete", deleted))
                        .message("Customer Deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );    }

    @GetMapping("/list/{limit}")
    public ResponseEntity<Response> listCustomers(@PathVariable int limit) {
        List<CustomerDTO> customers = (List<CustomerDTO>)customerService.list(limit);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("customers", customers))
                        .message("Customers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
