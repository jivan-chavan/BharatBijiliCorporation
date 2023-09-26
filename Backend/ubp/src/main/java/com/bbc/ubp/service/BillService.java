package com.bbc.ubp.service;

import com.bbc.ubp.dao.BillDao;
import com.bbc.ubp.entity.Bill;
import com.bbc.ubp.entity.Customer;
import com.opencsv.CSVReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    private BillDao billDao;

    @Autowired
    public BillService(BillDao billDao) {
        this.billDao = billDao;
    }

    public String uploadBillInBulk(MultipartFile file){
    	 List<Bill> bills = new ArrayList<>();

         try (InputStream inputStream = file.getInputStream()) {
             CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
             String[] line;
             csvReader.skip(1); 

             while ((line = csvReader.readNext()) != null) {
                 double unitConsumed = Double.parseDouble(line[0]);
             //    LocalDate dueDate = LocalDate.parse(line[1]);
                 
                 String dateText = line[1];
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                 LocalDate dueDate = LocalDate.parse(dateText, formatter);
               //  boolean isPaid = Boolean.parseBoolean(line[2]);
                 long customerId = Long.parseLong(line[2]);

                 Customer customer = new Customer();
                 customer.setCustomerId(customerId);
    
                 Bill bill = new Bill();
                 bill.setUnitConsumed(unitConsumed);
                 bill.setDueDate(dueDate);
               //  bill.setPaid(isPaid);
                 bill.setCustomer(customer);

                 bills.add(bill);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
        
         return billDao.uploadBillInBulk(bills);
    }
    
    
    public String addBill(long customerid, Bill newBill) {
    	
        return billDao.addBill(customerid,newBill);
    }

    public Bill getBillById(long billId) {
    	
        return billDao.getBillById(billId);
    }

    
    public List<Bill> getAllBills() {
        return billDao.getAllBills();
    }

    
    public String updateBill(long billId, long customerId, Bill updatedBill) {
        return billDao.updateBill(billId, customerId, updatedBill);
    }

    public String deleteBill(long billId, Long customerId) {
        return billDao.deleteBill(billId, customerId);
    }

    public List<Bill> getBillsByCustomerId(long customerId) {
        return billDao.getBillsByCustomerId(customerId);
    }

    public List<Bill> getPaidBillsByCustomerId(Long customerId) {
        return billDao.getPaidBillsByCustomerId(customerId);
    }

    public List<Bill> getNotPaidBillsByCustomerId(Long customerId) {
        return billDao.getNotPaidBillsByCustomerId(customerId);
    }
}
