import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BillService } from 'src/app/services/bill/bill.service';

@Component({
  selector: 'app-addbill',
  templateUrl: './addbill.component.html',
  styleUrls: ['./addbill.component.css']
})
export class AddbillComponent implements OnInit {
  customerId: string | null;
  bill: any = {}; 

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private billService: BillService
  ) {
    this.customerId = this.route.snapshot.paramMap.get('customerId');
  }
  ngOnInit(): void {

  }

 
  onSubmit() {
    if (this.customerId !== null) {


      console.log("bill "+this.bill)
      this.billService.addBill(this.customerId, this.bill).subscribe(
        (response) => {
          console.log('Bill added successfully:', response);
          // Redirect back to the customer details page
          this.router.navigate([`home/customerdetails/${this.customerId}`]);
        }
      );
    } else {
      console.error('customerId is null. Cannot add the bill.');
    }
  }
  onCancel(){
    this.router.navigate([`home/customerdetails/${this.customerId}`]);
  }
}



