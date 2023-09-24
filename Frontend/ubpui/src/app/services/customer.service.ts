import { HttpClient } from '@angular/common/http';
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

  
  uploadFile(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}bills/upload`, formData);
  }
  
  }


