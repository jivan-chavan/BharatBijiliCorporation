import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-addcustomer',
  templateUrl: './addcustomer.component.html',
  styleUrls: ['./addcustomer.component.css']
})
export class AddcustomerComponent implements OnInit {
  customer: any = {}; 

  constructor(private router: Router,
    private customerService :CustomerService) { }

  ngOnInit(): void {
  }


  onSubmit(){
    console.log('this customer ::: '+this.customer)
    this.customerService.addCustomer(this.customer).subscribe(
        (response) => {
          console.log(response);
          this.router.navigate([`home`]);
        })
    }
  

  onCancel(){
    this.router.navigate([`home`]);
  }
}
