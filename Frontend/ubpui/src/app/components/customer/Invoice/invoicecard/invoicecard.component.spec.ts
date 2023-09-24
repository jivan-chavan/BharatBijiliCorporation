import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoicecardComponent } from './invoicecard.component';

describe('InvoicecardComponent', () => {
  let component: InvoicecardComponent;
  let fixture: ComponentFixture<InvoicecardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoicecardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoicecardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
