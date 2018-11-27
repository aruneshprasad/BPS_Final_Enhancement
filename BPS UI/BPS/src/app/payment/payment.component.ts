import { Component, OnInit, Input } from '@angular/core';
import { GenerateBillService } from '../generate-bill.service';
import { $ } from 'protractor';
import { Router } from '@angular/router';
import { CustomerUpdateService } from '../customer-update.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
  providers:[GenerateBillService]
})
export class PaymentComponent implements OnInit {

  constructor(private generate:GenerateBillService,private route:Router, private customerUpdateService:CustomerUpdateService) { }
  
  
  customerName:string;
  address:string;
  contactNumber:string;
  country:string;
  state:string;
  email:string;
  balance:number;
  idDocType:string;
  docNo:string;
  regDate:Date;
  
  @Input()
  price:number;
  @Input()
  customerId:string;
  @Input()
  vendorId:string;
  @Input()
  vendorName:string;
  @Input()
  vendorType:string;
  @Input()
  pendingAmount:number;
  @Input()
  cardName:string;
  @Input()
  cardNo:string;
  @Input()
  cardMonth:number;
  @Input()
  cardYear:number;
  
  billId:string;
  details:any;
  custDetails:any;
  billDetails:any;
  payDate=new Date().toLocaleDateString();
  cvv:string;
  checked:boolean=false;
  ngOnInit() {
    
    /* this.generate.generate().subscribe(data => {
      console.log(data);
      this.price=data[data.length].amountToPay;
    });
    this.payDate=new Date().toLocaleDateString(); */
    this.loadScript('../../assets/pay.min.js');
  }
  public loadScript(url: string) {
    const body = <HTMLDivElement> document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
    
  }
  onSubmit(){
    console.log(this.cardYear);
    console.log(this.cardMonth);
    if(this.customerId.length===5 && this.cvv.length===3){
      this.generate.getAmountToPay(this.customerId).subscribe(dat => {
        this.billDetails=dat;
        console.log(this.billDetails);
        if(this.billDetails===null){
          const bill = {
            customerId:this.customerId,
            
            vendorName:this.vendorName,
            vendorType:this.vendorType,
            pendingAmount:this.pendingAmount,
            paymentDate:new Date(),
            amountToPay:this.price
          }
          this.generate.generate(bill).subscribe(data => {
            this.details=data;
            console.log(this.details);
            this.customerUpdateService.getCustomerDetails(this.customerId).subscribe(data =>{
              this.custDetails = data;
              const customer = {
                customerId : this.customerId,
                customerName:this.custDetails['customerName'],
                address:this.custDetails['address'],
                contactNumber:this.custDetails['contactNumber'],
                country:this.custDetails.country['country'],
                state:this.custDetails.country['state'],
                email:this.custDetails['email'],
                vendorType:this.custDetails.amount['vendorType'],
                vendorName:this.custDetails['vendorName'],
                cardNo:this.custDetails.card['cardNo'],
                balance:this.custDetails['balance']-this.price,
                idDocType:this.custDetails['idDocType'],
                docNo:this.custDetails['docNo'],
                regDate:this.custDetails['regDate']
              }
              this.customerUpdateService.updateCustomer(customer).subscribe(data => {
                this.details = data;
                console.log(data);
              });
            })
            alert("Bill successfully paid");
            this.route.navigate(['/home']);
          });
        }
        else{
          const billUpdate = {
            billId:this.billDetails[0]['billId'],
            customerId:this.customerId,
            vendorName:this.vendorName,
            vendorType:this.vendorType,
            pendingAmount:this.pendingAmount,
            paymentDate:new Date(),
            amountToPay:this.price
          }
          this.generate.update(billUpdate).subscribe(data => {
            this.details=data;
            console.log(this.details);
            this.customerUpdateService.getCustomerDetails(this.customerId).subscribe(data =>{
              this.custDetails = data;
              const customer = {
                customerId : this.customerId,
                customerName:this.custDetails['customerName'],
                address:this.custDetails['address'],
                contactNumber:this.custDetails['contactNumber'],
                country:this.custDetails.country['country'],
                state:this.custDetails.country['state'],
                email:this.custDetails['email'],
                vendorType:this.custDetails.amount['vendorType'],
                vendorName:this.custDetails['vendorName'],
                cardNo:this.custDetails.card['cardNo'],
                balance:this.custDetails['balance']-this.price,
                idDocType:this.custDetails['idDocType'],
                docNo:this.custDetails['docNo'],
                regDate:this.custDetails['regDate']
              }
              this.customerUpdateService.updateCustomer(customer).subscribe(data => {
                this.details = data;
                console.log(data);
              });
            })
            alert("Bill successfully paid");
            this.route.navigate(['/home']);
          });
        }
      })
      
      //alert("success");
  //this.route.navigate(['/home/billpay'])
    }
    
    else if(this.price>this.pendingAmount || this.price<=0){
      alert("Incorrect Amount");
    }
    else{
      alert("Invalid Card Details");
    }
  }
  
  cancel(){
    if(confirm("Are you sure?"))
    this.route.navigate(['/home/welcome']);
  }

  check(){
        this.checked=!this.checked;
  }
}
