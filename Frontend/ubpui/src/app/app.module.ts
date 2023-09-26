import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { CustomerComponent } from './components/customer/customer.component';
import { CustomerdetailsComponent } from './components/customer/customerdetails/customerdetails.component';
import { BsModalService, BsModalRef, ModalModule } from 'ngx-bootstrap/modal';
import { InvoicecardComponent } from './components/customer/Invoice/invoicecard/invoicecard.component';
import { TransactionComponent } from './components/customer/transaction/transaction.component';
import { PaycashComponent } from './components/customer/paycash/paycash.component';
import { AddcustomerComponent } from './components/customer/addcustomer/addcustomer.component';
import { AddbillComponent } from './components/addbill/addbill.component';
// import { ToastrModule } from 'ngx-toastr';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [ 
    AppComponent, LoginComponent, HomeComponent, HeaderComponent, CustomerComponent, CustomerdetailsComponent, InvoicecardComponent, TransactionComponent,
     PaycashComponent,
     AddcustomerComponent,
     AddbillComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ModalModule.forRoot(),
    // ToastrModule.forRoot({
    //   timeOut: 3000,
    //   positionClass: 'toast-top-right', 
    //   preventDuplicates: true, 
    // }),
  ],
  providers: [BsModalService],
  bootstrap: [AppComponent]
})
export class AppModule { }
