import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {ProductService} from "../services/product.service";

@Component({
  selector: 'app-product-edit-modal',
  templateUrl: './product-edit-modal.component.html',
  styleUrls: ['./product-edit-modal.component.scss']
})
export class ProductEditModalComponent implements OnInit {

  @Input('product')
  private product: any;

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private productService: ProductService) {

  }

  ngOnInit() {
    this.form = this.fb.group({
      id: [this.product.id],
      name: [this.product.name],
      category: this.fb.group({
        id: [this.product.category.id]
      }),
      price: [this.product.price],
      description: [this.product.description],
      images: this.initImages()
    });
  }

  private initImages() {
    let imageArray = this.fb.array([]);
    this.product.images.forEach(image => imageArray.push(this.fb.control(image)));
    return imageArray;
  }

  get images(){
    return this.form.get('images') as FormArray;
  }

  update() {
  this.productService.update(this.form.value)
    .subscribe(res=>alert('Product updated!'))
  }

}
