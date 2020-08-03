import {Component, OnInit} from '@angular/core';
import {ProductService} from "../product.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  products: any;
  pageSize = 5;

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.fetchProducts(0);
  }

  fetchProducts(page: number) {
    this.productService.getAll(page, this.pageSize).subscribe(res => {
      console.log(res)
      this.products = res;
    });
    console.log('fet')
  }

}
