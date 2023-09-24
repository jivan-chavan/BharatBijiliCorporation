import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PayService {

 // baseUrl:string=environment.baseUrl;

constructor(private http:HttpClient) { }


  payRequest(customerId: number, billId: number): Observable<any> {
    const url = 'http://localhost:8080/payments/addbyid';
    const body = { customerId: customerId, billId: billId };
    
    return this.http.post(url, body)
  }
}
