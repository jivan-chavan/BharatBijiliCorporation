import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Transaction } from 'src/model/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  baseUrl:string=environment.baseUrl;

  constructor(private http:HttpClient) { }

  getTransactionsByCustomerId(customerId : any):Observable<Transaction[]>{
    return this.http.get<Transaction[]>(`${this.baseUrl}payments/bycustomerid/${customerId}`);
  }


}
