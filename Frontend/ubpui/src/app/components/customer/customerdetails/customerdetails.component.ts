import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/model/Customer';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { BillService } from 'src/app/services/bill/bill.service';
import { Bill } from 'src/model/Bill';
import { BsModalService } from 'ngx-bootstrap';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { InvoicecardComponent } from '../Invoice/invoicecard/invoicecard.component';
import { TransactionService } from 'src/app/services/transaction/transaction.service';
import { Transaction } from 'src/model/Transaction';
import { PaycashComponent } from '../paycash/paycash.component';

@Component({
  selector: 'app-customerdetails',
  templateUrl: './customerdetails.component.html',
  styleUrls: ['./customerdetails.component.css']
})
export class CustomerdetailsComponent implements OnInit {
  customerId!: string | null; 
  //customerDetails: any; 
  customerDetails: Customer | null = null;

  //bills:any;
  bills!:Bill[];

  invoice: Bill|null=null;
  bsModalRef!: BsModalRef;

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private billService: BillService,
    private modalService: BsModalService,
    private transactionService: TransactionService,
    private router: Router

  ) {}

  
  ngOnInit(): void {
    this.showDetails();
  }
showDetails(){
  this.customerId = this.route.snapshot.paramMap.get('customerId');
  this.customerService.getCustomerDetail(this.customerId).subscribe(
    (data:any) => {
      console.log(data);
      this.customerDetails = data;
      
    }
  );
}

onClickViewBills(){
  console.log("view bills")
  this.billService.getAllBillsByCustomerId(this.customerId).subscribe(
    (data:any) => {
      console.log(data);
     this.bills = data;   
    }
  );
}

generateInvoice(bill: any) {
  this.bsModalRef = this.modalService.show(InvoicecardComponent, {
    initialState: {
      customerDetails: this.customerDetails,
      bill: bill
    }
  });
}

onClickViewTransaction(){
  this.router.navigate([`home/customerdetails/${this.customerId}/transactions`]); 

}

payBillByCash(bill:any){
  this.bsModalRef = this.modalService.show(PaycashComponent, {
    initialState: {
      customerDetails: this.customerDetails,
      bill: bill
    },
    ignoreBackdropClick: true
  });
}


addBill(){
  this.router.navigate([`home/addbill/${this.customerId}`]); 

}
backToHomepage(){
  this.router.navigate(['home']);

}
}
