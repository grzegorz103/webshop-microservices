import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  getAll(page: number, size: number) {
    return this.httpClient.get<any>(environment.apiUrl + 'products/products?size=' + size + '&page=' + page);
  }

}
