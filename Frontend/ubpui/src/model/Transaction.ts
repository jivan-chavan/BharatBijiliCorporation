export interface Transaction{
     paymentId :number;
     amount:number ;
	 discountAmount:number;
	 finalAmount:number;
	 paidCurrency:boolean;
	 paidinOnline:boolean;
	 paymentDate:Date;
}