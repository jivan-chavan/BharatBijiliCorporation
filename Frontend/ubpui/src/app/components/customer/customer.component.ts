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
  fileUploadResponseMessege!:string;
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

    selectedFile!: File | null;

    onFileSelected(event: any) {
      this.selectedFile = event.target.files[0];
    }
  
    
  
    uploadFile(): void {
      if (this.selectedFile) {
        const formData = new FormData();
        formData.append('file', this.selectedFile);
  
        this.customerService.uploadFile(formData).subscribe(
          (response:string) => {
            // console.log('File uploaded successfully')
            // this.fileUploadResponseMessege=response;
            // console.log('this.fileUploadResponseMessege'+this.fileUploadResponseMessege);
          }
        );
      }
    }

}