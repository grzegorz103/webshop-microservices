import {Component, OnInit} from '@angular/core';
import {ProductService} from "../product.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  products: any;
  pageSize = 5;
  nameControl = new FormControl('');

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.nameControl.valueChanges.subscribe(v => this.fetchProducts(0));
    this.fetchProducts(0);
  }

  fetchProducts(page: number) {
    this.productService.getAll(page, this.pageSize, this.nameControl.value).subscribe(res => {
      console.log(res)
      this.products = res;
    });
  }

}
