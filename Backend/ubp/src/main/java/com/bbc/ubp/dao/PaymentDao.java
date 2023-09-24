
package com.bbc.ubp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.ubp.entity.Bill;
import com.bbc.ubp.entity.Customer;
import com.bbc.ubp.entity.Payment;
import com.bbc.ubp.service.BillService;
import com.bbc.ubp.service.CustomerService;

import java.util.List;

@Repository
public class PaymentDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PaymentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private BillService billService;
    
    public void  addPaymentByCustomerIdAndBillId(long customerId, long billId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
       Customer customer= customerService.findCustomerByCustomerId(customerId);
        Bill bill=billService.getBillById(billId);
        
       Payment payment= new Payment();
       payment.setPaidCurrency(true);
       payment.setCustomer(customer);
       
        bill.setPayment(payment);     
        session.save(payment);
        session.getTransaction().commit();
        session.close();
       
    }
    
    public void addPayment(Payment newPayment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(newPayment);
        session.getTransaction().commit();
        session.close();
    }

    public Payment getPaymentById(long paymentId) {
        Session session = sessionFactory.openSession();
        Payment payment = session.get(Payment.class, paymentId);
        session.close();
        return payment;
    }

    public List<Payment> getAllPayments() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Payment.class);
        List<Payment> payments = criteria.list();
        session.close();
        return payments;
    }

    public void updatePayment(Payment updatedPayment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(updatedPayment);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePayment(long paymentId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Payment payment = session.get(Payment.class, paymentId);
        if (payment != null) {
            session.delete(payment);
        }
        session.getTransaction().commit();
        session.close();
    }
    public List<Payment> getAllPaymentsByCustomerId(long customerId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.add(Restrictions.eq("customer.id", customerId));
        List<Payment> payments = criteria.list();
        session.close();
        return payments;
    }
}
