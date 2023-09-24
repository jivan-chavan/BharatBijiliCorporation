import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  myOtPResposeMessage!:String;
  incorrect: string | null = null;
  isShow: boolean = true;
  employeeId: string = '';

  constructor(private service:HttpService, private router: Router){

  }
  ngOnInit(): void {
    this.isShow = true;
  }


  onSubmit(form: NgForm): void {
    if (form.valid) {
    
      const employeeID = form.value.employeeid;
      const otp = form.value.otp;

      this.service.loginUser(employeeID, otp)
          .subscribe(
              (response) => {
                  console.log(`response :: ${response}`);
                  if (response === 'Login Successful.') {
                   
                      console.log(`Successful login`);

                      this.incorrect = null;
                      this.router.navigate(['/home']); 
                      
                  } else {
                      this.incorrect = 'Login Failed. Please check your Employee ID and OTP.';
                  }
              },
              (error) => {
                  this.incorrect = 'Something went wrong, Please try again.'; 
              }
          );
  }
  }

  sendOTP(employeeId: string): void {
    console.log('Sending OTP to Employee ID:', employeeId);
    this.service.sendRequestForOTP(employeeId)
        .subscribe(
            (response) => {
                this.myOtPResposeMessage = response;
                console.log(`myOtPResposeMessage :: ${this.myOtPResposeMessage}`);
                this.incorrect = null; 
            },
            (error) => {
                this.incorrect = 'Failed to send OTP. Please try again.'; 
            }
        );
}


  onShow(): void {
    this.isShow = !this.isShow;
  }
}