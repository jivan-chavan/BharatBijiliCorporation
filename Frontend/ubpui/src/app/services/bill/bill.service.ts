import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Bill } from 'src/model/Bill';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  baseUrl:string=environment.baseUrl;

  constructor(private http:HttpClient) { }
  getAllBillsByCustomerId(customerId: any):Observable<Bill[]>{
    return this.http.get<Bill[]>(`${this.baseUrl}bills/getbillsbycustomerid/${customerId}`)

  }

  // generateInvoice(customerId: any):Observable<Bill>{
  //   return this.http.get<Bill[]>(`${this.baseUrl}bills/getbillsbycustomerid/${customerId}`)

  // }
}
