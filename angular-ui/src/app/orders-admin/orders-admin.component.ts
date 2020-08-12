import {Component, OnInit} from '@angular/core';
import {OrderService} from "../services/order.service";
import {CartWindowService} from "../services/cart-window.service";

@Component({
  selector: 'app-orders-admin',
  templateUrl: './orders-admin.component.html',
  styleUrls: ['./orders-admin.component.scss']
})
export class OrdersAdminComponent implements OnInit {

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

  updateState(order: any) {
    if (order) {
      switch (order.orderState) {
        case 'CREATED':
          order.orderState = 'DONE';
          break;
        case 'DONE':
          order.orderState = 'ABANDONED';
          break;
        case 'ABANDONED':
          order.orderState = 'CREATED';
          break;
      }
      this.orderService.update(order)
        .subscribe(res => this.fetchOrders());
    }
  }

}
