import {Component, OnInit} from '@angular/core';
import {OrderService} from "../order.service";
import {CartWindowService} from "../cart-window.service";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders: any[];

  constructor(private orderService: OrderService,
              private  cartService: CartWindowService) {
  }

  ngOnInit() {
    this.fetchOrders();
  }

  fetchOrders() {
    this.orderService.getAll(0, 20, null)
      .subscribe(res => this.orders = res.content);
  }

}
