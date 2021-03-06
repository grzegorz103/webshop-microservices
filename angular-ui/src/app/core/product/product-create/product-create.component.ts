import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {CategoryService} from "../../../services/category.service";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ProductService} from "../../../services/product.service";
import {map, startWith} from "rxjs/operators";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.scss']
})
export class ProductCreateComponent implements OnInit {

  categories: any[] = [];
  filteredOptions: Observable<any[]>;
  form: FormGroup;

  constructor(private categoryService: CategoryService,
              private productService: ProductService,
              private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
    this.fetchCategories();
  }

  private _filter(value: string): any[] {
    const filterValue = value.toLowerCase();
    console.log(value)
    return this.categories.filter(option => option.name.toLowerCase().indexOf(filterValue) === 0);
  }

  fetchCategories() {
    this.categoryService.getAll().subscribe(res => {
      this.categories = res.content;
      this.filteredOptions = this.form.get('category').valueChanges.pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    });
  }

  createForm() {
    this.form = this.fb.group({
      name: [''],
      description: [''],
      price: [''],
      category: '',
      images: this.initImages()
    })
  }

  private initImages() {
    let imageArray = this.fb.array([]);
    imageArray.push(this.fb.control(''));
    return imageArray;
  }

  get images() {
    return this.form.get('images') as FormArray;
  }

  addImage() {
    this.images.push(this.fb.control(''));
  }

  sendForm() {
    this.productService.save(
      {
        name: this.form.get('name').value,
        description: this.form.get('description').value,
        price: this.form.get('price').value,
        category: {
          id: this.form.get('category').value
        },
        images: this.form.get('images').value
      }
    ).subscribe(res => alert('Wysłano'));
  }

  removeImage(i: number) {
    this.images.removeAt(i);
  }

}
