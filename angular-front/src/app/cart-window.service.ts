import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartWindowService {

  products: any[];

  constructor() {
    this.loadProducts()
  }

  addProduct(product: any) {

    if (!this.products) {
      this.products = [];
    }
    this.products.push(product);
    localStorage.setItem('cart', JSON.stringify(this.products));
  }

  loadProducts() {
    this.products = JSON.parse(localStorage.getItem('cart'));
  }

}
