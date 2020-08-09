import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private httpClient: HttpClient) { }

  getUserAddress(){
    return this.httpClient.get<any>(environment.apiUrl + 'address/address')
  }

  update(address: any) {
    return this.httpClient.put<any>(environment.apiUrl+'address/address/' + address.id, address);
  }
}
