import {Component, OnInit} from '@angular/core';
import {ProductService} from "../product.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  products: any;
  currentPage = 0;

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.fetchProducts(0);
  }

  fetchProducts(page: number) {
    this.productService.getAll(page).subscribe(res => {
      console.log(res)
      this.products = res;
    });
    console.log('fet')
  }

}
