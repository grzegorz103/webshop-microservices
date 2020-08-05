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
    if (this.products.some(e => e.id === product.id)) {
      return;
    }

    this.products.push(product);
    this.save();
  }

  loadProducts() {
    this.products = JSON.parse(localStorage.getItem('cart'));
  }

  save() {
    localStorage.setItem('cart', JSON.stringify(this.products));
  }

  clear() {
    this.products = [];
    this.save();
  }
}
