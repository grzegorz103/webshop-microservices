import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {ProductComponent} from "./product/product.component";
import {ProductCreateComponent} from "./product-create/product-create.component";
import {OrdersComponent} from "./orders/orders.component";


const routes: Routes = [
  {path: 'products', component: ProductComponent},
  {path: 'products/create', component: ProductCreateComponent},
  {path: 'orders', component: OrdersComponent},
  {path: '**', component: IndexComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
