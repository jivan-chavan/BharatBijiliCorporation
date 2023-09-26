import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Customer } from 'src/model/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
 
  baseUrl:string=environment.baseUrl;

  constructor(private http:HttpClient) { }

  getAllCustomers():Observable<Customer[]>{
    return this.http.get<Customer[]>(`${this.baseUrl}customers/all`);
  }

  getCustomerDetail(customerId: any){
    return this.http.get(`${this.baseUrl}customers/${customerId}`)
  }

  

  addCustomer(customer : any){
    return this.http.post(`${this.baseUrl}customers/addcustomer`, customer,{ responseType: 'text' });

  }

    uploadBillFile(file:any) {
  
      // Create form data
      const formData = new FormData(); 
        
      // Store form name as "file" with file data
      formData.append("file", file, file.name);
        
      // Make http post request over api
      // with formData as req
      return this.http.post(`${this.baseUrl}bills/upload`, formData,{ responseType: 'text' })
  }
  }


