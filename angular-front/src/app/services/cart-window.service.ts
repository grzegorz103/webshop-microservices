import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartWindowService {

  products: any[];
  private _hidden = true;

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

  get hidden() {
    return this._hidden;
  }

  set hidden(value) {
    this._hidden = value;
  }

  show() {
    this._hidden = false;
  }

  hide() {
    this._hidden = true;
  }

}
