package com.bbc.ubp.controller;

import com.bbc.ubp.entity.Bill;
import com.bbc.ubp.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bills")
@CrossOrigin(origins = "http://localhost:4200")
public class BillController {


	@Autowired
    private BillService billService;
   
	 @GetMapping("/getbillsbycustomerid/{customerid}")
	    public List<Bill> getBillsByCustomerId(@PathVariable("customerid") long customerId) {
	        return billService.getBillsByCustomerId(customerId);
	    }
	
    @PostMapping("/addbill/{customerid}")
    public String addBill(@PathVariable("customerid") long customerid, @RequestBody Bill newBill) {
        return billService.addBill(customerid,newBill);
    }

    @GetMapping("/getBillById/{billid}")
    public Bill getBillById(@PathVariable("billid") long billId) {
        return billService.getBillById(billId);
    }
    @GetMapping("/getallbills")
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }
    
  @PostMapping("/upload")
  public String uploadBillInBulk(@RequestParam("file") MultipartFile file) {
	return  billService.uploadBillInBulk(file);
	  
  }
   

    @PutMapping("/{billid}")
    public String updateBill(
            @PathVariable("billid") long billId,
            @RequestParam long customerId,
            @RequestBody Bill updatedBill
    ) {
        return billService.updateBill(billId, customerId, updatedBill);
    }

    @DeleteMapping("/{billid}")
    public String deleteBill(
            @PathVariable("billid") long billId,
            @RequestParam Long customerId
    ) {
        return billService.deleteBill(billId, customerId);
    }

   

    @GetMapping("/getPaidBillsByCustomerId/{customerid}")
    public List<Bill> getPaidBillsByCustomerId(@PathVariable("customerid") Long customerId) {
        return billService.getPaidBillsByCustomerId(customerId);
    }

    @GetMapping("/getNotPaidBillsByCustomerId/{customerId}")
    public List<Bill> getNotPaidBillsByCustomerId(@PathVariable("customerid") Long customerId) {
        return billService.getNotPaidBillsByCustomerId(customerId);
    }
}
