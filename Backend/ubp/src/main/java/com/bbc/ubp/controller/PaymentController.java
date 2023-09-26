package com.bbc.ubp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bbc.ubp.entity.Payment;
import com.bbc.ubp.service.PaymentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addbyid")
    public String addPaymentByCustomerIdAndBillId( @RequestBody Map<String, Long> params) {
    	Long customerId = params.get("customerId");
        Long billId = params.get("billId");

       paymentService.addPaymentByCustomerIdAndBillId( customerId , billId);
       return "added successfully";
    }
    
    
    @PostMapping("/add")
    public void addPayment(@RequestBody Payment newPayment) {
        paymentService.addPayment(newPayment);
    }

    @GetMapping("/{paymentid}")
    public Payment getPayment(@PathVariable("paymentid") long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PutMapping("/update")
    public void updatePayment(@RequestBody Payment updatedPayment) {
        paymentService.updatePayment(updatedPayment);
    }

    @DeleteMapping("/delete/{paymentid}")
    public void deletePayment(@PathVariable("paymentid") long paymentId) {
        paymentService.deletePayment(paymentId);
    }
    @GetMapping("/bycustomerid/{customerid}")
    public List<Payment> getAllPaymentsByCustomerId(@PathVariable("customerid") long customerId) {
        return paymentService.getAllPaymentsByCustomerId(customerId);
    }
}
