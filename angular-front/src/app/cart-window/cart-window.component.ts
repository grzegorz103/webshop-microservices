import {Component, OnInit} from '@angular/core';
import {CartWindowService} from "../cart-window.service";

@Component({
  selector: 'app-cart-window',
  templateUrl: './cart-window.component.html',
  styleUrls: ['./cart-window.component.scss']
})
export class CartWindowComponent implements OnInit {
  hidden = true;

  constructor(private cartWindowService: CartWindowService) {
  }

  ngOnInit() {
  }

  hide() {
    this.hidden = !this.hidden;
  }
}
