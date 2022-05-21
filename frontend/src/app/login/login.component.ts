import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private route :Router,private toastr: ToastrService) { }

  ngOnInit(): void {
  }
  login(value:any)
  {
      this.route.navigate(['/'])
  }

}
