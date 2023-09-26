import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from 'src/model/Customer';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customers: Customer[]=[];
  showSuccessMessage: boolean = false;
      
  constructor(private customerService: CustomerService,
      private router:Router) {}

  ngOnInit(): void {
    this.getAllTheCustomers();
    
  }
    getAllTheCustomers(){
      this.customerService.getAllCustomers().subscribe((response) => {
        this.customers = response;
        console.log(response);
      });
    }

    
    addCustomer(){
      this.router.navigate([`home/addcustomer`]);
    }

    loading: boolean = false; 
    file: File|null = null; 


    onBillFileSelect(event:any) {
      this.file = event.target.files[0];
  }

  onBillFileUploadClick() {
    this.loading = !this.loading;
    console.log(this.file);
    this.customerService.uploadBillFile(this.file).subscribe(
        (event: any) => {
            if (typeof (event) === 'object') {
                this.loading = false; 
                this.showSuccessMessage = true;
            }
        }
    );
}
}
  