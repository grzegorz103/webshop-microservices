import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent implements OnInit {

  users:any;

  constructor(private http:HttpClient) { }

  ngOnInit() {
    this.fetchUsers();
  }

  private fetchUsers() {
    this.http.get<any>('https://dev-brcvsf2g.eu.auth0.com/api/v2/users').subscribe(res=>this.users=res);
  }

}
