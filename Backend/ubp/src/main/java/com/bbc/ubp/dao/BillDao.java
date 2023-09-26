package com.bbc.ubp.dao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.ubp.constants.ConstantValues;
import com.bbc.ubp.entity.Bill;
import com.bbc.ubp.entity.Customer;
import com.bbc.ubp.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BillDao {

    private SessionFactory sessionFactory;
    private CustomerService customerService;

    @Autowired
    public BillDao(SessionFactory sessionFactory,CustomerService customerService) {
        this.sessionFactory = sessionFactory;
        this.customerService=customerService;
    }

public String uploadBillInBulk(List<Bill> bills) {
		
		Session session = sessionFactory.openSession();
        Transaction transaction=session.beginTransaction(); 
        
        for (Bill bill : bills) {
      
        	bill.setBillDuration(LocalDate.now());
        	bill.setBillAmount(bill.getUnitConsumed()*(ConstantValues.costPerUnit));

            session.save(bill);
            
		}
        	transaction.commit();
    		session.close();
            return "Bill added successfully"; 		
	}



public List<Bill> getBillsByCustomerId(long customerId) {
    try (Session session = sessionFactory.openSession()) {
        return session.createCriteria(Bill.class)
                .createAlias("customer", "c")
                .add(Restrictions.eq("c.customerId", customerId))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }
}
public List<Bill> getAllBills() {
    Session session = sessionFactory.openSession();

    List<Bill> list=session.createCriteria(Bill.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
 		session.close();
                return list;   
    }

    public String addBill(long customerid, Bill newBill) {
        Customer customer =customerService.findCustomerByCustomerId(customerid) ;       	
    	if(customer==null) {
    	return "No such Customer";	
    	}      	  
        Session session = sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();        	
        	newBill.setCustomer(customer);
        	newBill.setBillDuration(LocalDate.now());
        	newBill.setBillAmount(newBill.getUnitConsumed()*(ConstantValues.costPerUnit));
            session.save(newBill);
            transaction.commit();
    		session.close();
            return "Bill added successfully"; 
        	     
    }
 
    public Bill getBillById( long billId) {
        Session session = sessionFactory.openSession();
        Bill bill=session.get(Bill.class, billId);
        session.close();
            return bill; 
    }

  

    

  
    public String updateBill(long billId, long customerId, Bill updatedBill) {
        Session session = sessionFactory.openSession();
            Transaction transaction=session.beginTransaction();        	
            Bill existingBill = session.get(Bill.class, billId);
            if (existingBill != null) {
                if (existingBill.getCustomer().getCustomerId() == customerId) {
                    existingBill.setUnitConsumed(updatedBill.getUnitConsumed());
                    existingBill.setBillAmount(updatedBill.getBillAmount());
                    existingBill.setBillDuration(updatedBill.getBillDuration());
                    existingBill.setDueDate(updatedBill.getDueDate());
                    existingBill.setPaid(updatedBill.isPaid());                  
                    transaction.commit();
                    return "Bill updated successfully";
                } else {
                    transaction.rollback();
                    return "Customer ID mismatch, cannot update Bill";
                }
            }
            return "No such Customer";
       
    }

    public String deleteBill(long billId, Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Bill bill = session.get(Bill.class, billId);
            if (bill != null) {
                if ((bill.getCustomer().getCustomerId())==(customerId)) {
                    session.delete(bill);
                    return "Bill deleted successfully";
                } else {
                    return "Customer ID mismatch, cannot delete Bill";
                }
            } else {
                return "Bill not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete Bill";
        }
    }

   

    public List<Bill> getPaidBillsByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("customerId", customerId));
            criteria.add(Restrictions.eq("isPaid", true));
            return criteria.list();
        }
    }

    public List<Bill> getNotPaidBillsByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("customerId", customerId));
            criteria.add(Restrictions.eq("isPaid", false));
            return criteria.list();
        }
    }

	
}
