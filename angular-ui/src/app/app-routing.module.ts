import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexComponent} from "./shared/index/index.component";
import {ProductComponent} from "./core/product/product-list/product.component";
import {ProductCreateComponent} from "./core/product/product-create/product-create.component";
import {OrdersComponent} from "./core/order/orders/orders.component";
import {AddressComponent} from "./core/address/address.component";
import {ProductDetailsComponent} from "./core/product/product-details/product-details.component";
import {EventLogComponent} from "./core/event-log/event-log.component";
import {OrdersAdminComponent} from "./core/order/orders-admin/orders-admin.component";
import {ManagementComponent} from "./core/management/management.component";


const routes: Routes = [
  {path: 'products', component: ProductComponent},
  {path: 'products/create', component: ProductCreateComponent},
  {path: 'products/:id', component: ProductDetailsComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'address', component: AddressComponent},
  {path: 'manage', component: ManagementComponent},
  {path: 'events', component: EventLogComponent},
  {path: 'orders/admin', component: OrdersAdminComponent},
  {path: '**', component: IndexComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
