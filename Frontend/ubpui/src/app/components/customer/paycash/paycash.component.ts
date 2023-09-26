import { Component, Input, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { PayService } from 'src/app/services/paycash/pay.service';

@Component({
  selector: 'app-paycash',
  templateUrl: './paycash.component.html',
  styleUrls: ['./paycash.component.css']
})
export class PaycashComponent implements OnInit {

  @Input() customerDetails: any;
  @Input() bill: any;
  
 isPaymentDone:boolean=false;
  constructor(
    private payService : PayService,
    public bsModalRef: BsModalRef
  ) {}
  ngOnInit(): void {
  }
  onPay() {
   
    // Hide the notification after 3 seconds (adjust the timeout as needed)
    if(!this.isPaymentDone){
      // payment logic 

      this.payService.payRequest(this.customerDetails.customerId,this.bill.billId).
      subscribe((response)=> 
      {
        console.log(response)
        console.log("Payment done....");
      }
      )
      
    }


    this.bsModalRef.hide();
  }
}
