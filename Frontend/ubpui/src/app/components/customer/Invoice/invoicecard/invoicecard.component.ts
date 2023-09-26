import { Component, Input, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-invoicecard',
  templateUrl: './invoicecard.component.html',
  styleUrls: ['./invoicecard.component.css']
})
export class InvoicecardComponent implements OnInit {
  
  @Input() customerDetails: any;
  @Input() bill: any;
  
  constructor(public bsModalRef: BsModalRef) {} 

  ngOnInit(): void {
  }

}
