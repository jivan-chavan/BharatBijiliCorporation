package com.bbc.ubp.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.ubp.entity.Customer;

@Repository
public class CustomerDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Customer.class)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        List<Customer> customers = criteria.list();
        
        session.close(); // Close the session when you're done
        
        return customers;
    }


    
    public String addCustomer(Customer newCustomerRequest) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newCustomerRequest);
            return "Customer added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add customer";
        }
    }

    public Customer findCustomerByCustomerId(long customerId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Customer.class, customerId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addCustomersInBulk(List<Customer> customerList) {
        try (Session session = sessionFactory.openSession()) {
            for (Customer customer : customerList) {
                session.save(customer);
            }
            return "Bulk customers added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add bulk customers";
        }
    }

    
    public String updateCustomer(Customer updatedCustomer) {
        try (Session session = sessionFactory.openSession()) {
            session.update(updatedCustomer);
            return "Customer updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to update customer";
        }
    }

    
    public String deleteCustomer(long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Customer existingCustomer = session.get(Customer.class, customerId);
            if (existingCustomer != null) {
                session.delete(existingCustomer);
                return "Customer deleted successfully";
            } else {
                return "Customer not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete customer";
        }
    }
}
  