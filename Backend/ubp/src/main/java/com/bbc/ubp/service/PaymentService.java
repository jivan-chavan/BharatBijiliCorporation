
package com.bbc.ubp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbc.ubp.dao.PaymentDao;
import com.bbc.ubp.entity.Payment;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentDao paymentDao;

    @Autowired
    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void addPaymentByCustomerIdAndBillId(long customerId, long billId) {
        paymentDao.addPaymentByCustomerIdAndBillId(customerId, billId);
    }
    
    
    public void addPayment(Payment newPayment) {
        paymentDao.addPayment(newPayment);
    }

    public Payment getPaymentById(long paymentId) {
        return paymentDao.getPaymentById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

    public void updatePayment(Payment updatedPayment) {
        paymentDao.updatePayment(updatedPayment);
    }

    public void deletePayment(long paymentId) {
        paymentDao.deletePayment(paymentId);
    }
    public List<Payment> getAllPaymentsByCustomerId(long customerId) {
        return paymentDao.getAllPaymentsByCustomerId(customerId);
    }
}
