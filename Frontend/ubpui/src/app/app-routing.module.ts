import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { CustomerdetailsComponent } from './components/customer/customerdetails/customerdetails.component';
import { TransactionComponent } from './components/customer/transaction/transaction.component';

const routes: Routes = [
  {
    path:'',component:LoginComponent
}, 
 
  {
    path:'home',component:HomeComponent
  }, 
  {
    path:'home/customerdetails/:customerId',component:CustomerdetailsComponent
  },
  
  {
    path:'home/customerdetails/:customerId/transactions',component:TransactionComponent
  }
// {
//   path:"**",component:NotfoundComponent
// }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
