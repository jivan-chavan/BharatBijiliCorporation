import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TransactionService } from 'src/app/services/transaction/transaction.service';
import { Transaction } from 'src/model/Transaction';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  customerId:any;
  transaction!:Transaction[];

  constructor(private route: ActivatedRoute,
    private transactionService: TransactionService
    ) {}
  
  ngOnInit(): void {
    this.getCustomerId();
  }


  getCustomerId(){
    this.route.paramMap.subscribe(params => {
      this.customerId = params.get('customerId');
      
      console.log('Customer ID from route:'+ this.customerId);
      this.transactionService.getTransactionsByCustomerId(this.customerId).subscribe(
        (data:any) => {
          console.log(data);
         this.transaction = data;   
        }
      );
    });
  }

  
}
