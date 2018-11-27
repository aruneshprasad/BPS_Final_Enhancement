import { Component, OnInit } from '@angular/core';
import { GenerateBillService } from '../generate-bill.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  file:File
  constructor(private genBill:GenerateBillService,private route:Router) { }
  details:any;
   ngOnInit() {
     if(sessionStorage.getItem('user')===null){
       alert("Unauthorized Access");
       this.route.navigate(['/'])
     }
     this.loadScript('../../assets/min.js');
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
 
   
   uploadFile(){
    
     const billFile = {
      file:this.file
      
     }
     console.log(billFile);
     this.genBill.addFile(billFile).subscribe(data => {
      this.details=data;
       console.log(data);
       alert("Uploaded Successfully");  
     }); 
     this.route.navigate(['/home/generatebill'])
   }
}
