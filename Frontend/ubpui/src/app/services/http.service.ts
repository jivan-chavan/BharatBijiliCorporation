import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
// baseUrl:string='http://localhost:8080/';
baseUrl:string=environment.baseUrl;

constructor(private http:HttpClient) { }

sendRequestForOTP(employeeid : any){
  return(this.http.get(`${this.baseUrl}ubp/employee/email/send/${employeeid}`,{responseType:'text'}))
}


loginUser(employeeid:any, otp:any){
  const params = {
    employeeID: employeeid,
    otp: otp
  }; 

  return (this.http.post(`${this.baseUrl}user/login`,params,{responseType:'text'}));
}



}