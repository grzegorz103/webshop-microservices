import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient) {
  }

  getAll(page: number | 0, size: number | 20, name: string) {
    let params = new HttpParams();
    params = params.append('size', String(size));
    params = params.append('page', String(page));

    return this.httpClient.get<any>(environment.apiUrl + 'orders/orders/users');
  }

}
