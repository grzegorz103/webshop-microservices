import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../services/product.service";
import {CartWindowService} from "../services/cart-window.service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  product: any;

  constructor(private route: ActivatedRoute,
              private cartWindowService: CartWindowService,
              private productService: ProductService) {
    this.fetchProduct();
  }

  ngOnInit() {
  }

  private fetchProduct() {
    this.productService.getById(this.route.snapshot.paramMap.get('id'))
      .subscribe(res => {
          // @ts-ignore
          this.product = res;
        }
      );
  }

  addToCart() {
    this.cartWindowService.addProduct(this.product);
    this.cartWindowService.show();
  }
}
