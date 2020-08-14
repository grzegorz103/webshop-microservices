import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {ProductComponent} from "./product/product.component";
import {ProductCreateComponent} from "./product-create/product-create.component";
import {OrdersComponent} from "./orders/orders.component";
import {AddressComponent} from "./address/address.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {EventLogComponent} from "./event-log/event-log.component";
import {OrdersAdminComponent} from "./orders-admin/orders-admin.component";
import {ManagementComponent} from "./management/management.component";


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
