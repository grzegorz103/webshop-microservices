import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../../services/product.service";
import {CartWindowService} from "../../../services/cart-window.service";
import {Lightbox} from "ngx-lightbox";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  product: any;
  images = [];

  constructor(private route: ActivatedRoute,
              private cartWindowService: CartWindowService,
              private _lightbox: Lightbox,
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
          this.product.images.forEach(image=>{
            this.images.push({
                src: image,
                thumb: image
              }
            )
          })
        }
      );
  }

  addToCart() {
    this.cartWindowService.addProduct(this.product);
    this.cartWindowService.show();
  }

  open(index: number): void {
    // open lightbox
    this._lightbox.open(this.images, index);
  }

  close(): void {
    // close lightbox programmatically
    this._lightbox.close();
  }

}
