import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerRegService } from '../customer-reg.service';
import { HttpClient } from 'selenium-webdriver/http';
import { GenerateBillService } from '../generate-bill.service';
//import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-generate-bill',
  templateUrl: './generate-bill.component.html',
  styleUrls: ['./generate-bill.component.css']
})
export class GenerateBillComponent implements OnInit {

customerId:string;
customerName:string;
cardNo:string;
cardValidity:Date;
cardMonth:number;
cardYear:number;
vendorId:string;
vendorName:string;
vendorType:string;
pendingAmount:number;
details:any;
object:any;
payDate=new Date().toLocaleDateString();
amountToPay:number;
  constructor(private route:Router,private billService:GenerateBillService) { }
  ngOnInit() {
    if(sessionStorage.getItem('user')===null){
      alert("Unauthorized Access");
      this.route.navigate(['/'])
    }
    this.loadScript('../../assets/min.js');
   // this.custId:custId;
    
  }
  
  uploadFile():void{
    this.route.navigate(['/home/upload']);
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
  searchVendor(){
    var i = 0;
    this.billService.getVendor(this.customerId).subscribe(data => {
        this.details=data;
        if(this.details['vendorName']===null)
        {
          alert("Customer "+this.customerId+" not found");
          this.route.navigate(['/home/generatebill']);
        }
        else{
          this.customerId=data['customerId'];
          this.vendorName=data['vendorName'];
          this.vendorType=data.amount['vendorType'];
          this.customerName=data['customerName'];
          this.cardNo=data.card['cardNo'];
          this.cardValidity=new Date(data.card['cardValidity']);
          this.cardMonth=this.cardValidity.getMonth()+1;
          //console.log(this.cardMonth);
          this.cardYear=this.cardValidity.getFullYear();
          //console.log(this.cardYear);
          this.billService.getAmountToPay(this.customerId).subscribe(dat => {
            this.object=dat;
            console.log(dat);
  
            if(this.object!=null){
              console.log(this.object[0]['dueAmount']);
              this.pendingAmount=this.object[0]['dueAmount'];
               
              if(this.pendingAmount<=0)
                alert("Bill Already Paid");
                
            }
            else{
            console.log(this.details.amount['amount']);
            this.pendingAmount=this.details.amount['amount'];
            }
            console.log(this.pendingAmount);
          });
          console.log(data);
          
         
        }
    });
  }
}
