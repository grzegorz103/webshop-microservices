import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  getAll(page: number, size: number, name: string) {
    let params = new HttpParams();
    params = params.append('size', String(size));
    params = params.append('page', String(page));
    if (name)
      params = params.append('name', name);

    return this.httpClient.get<any>(environment.apiUrl + 'products/products?size=' + size + '&page=' + page, {params: params});
  }

  save(value: any) {
    return this.httpClient.post<any>(environment.apiUrl + 'products/products', value);
  }
}
