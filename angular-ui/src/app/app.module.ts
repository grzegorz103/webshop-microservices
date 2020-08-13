import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {IndexComponent} from './index/index.component';
import {NavbarComponent} from './navbar/navbar.component';
import {ProductComponent} from './product/product.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { FooterComponent } from './footer/footer.component';
import {MatIconModule} from "@angular/material/icon";
import { ProductCreateComponent } from './product-create/product-create.component';
import {MatOptionModule} from "@angular/material/core";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatSelectModule} from "@angular/material/select";
import {InterceptorService} from "./services/interceptor.service";
import { OrdersComponent } from './orders/orders.component';
import {MatExpansionModule} from "@angular/material/expansion";
import { CartWindowComponent } from './cart-window/cart-window.component';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import { AddressComponent } from './address/address.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import {NgxGalleryModule} from "ngx-gallery";
import {LightboxModule} from "ngx-lightbox";
import { EventLogComponent } from './event-log/event-log.component';
import { OrdersAdminComponent } from './orders-admin/orders-admin.component';
import { ProductEditModalComponent } from './product-edit-modal/product-edit-modal.component';
import {NgbCarouselModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    NavbarComponent,
    ProductComponent,
    FooterComponent,
    ProductCreateComponent,
    OrdersComponent,
    CartWindowComponent,
    AddressComponent,
    ProductDetailsComponent,
    EventLogComponent,
    OrdersAdminComponent,
    ProductEditModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, MatInputModule, MatIconModule,
    HttpClientModule, FormsModule, ReactiveFormsModule, LightboxModule,
    BrowserAnimationsModule, MatOptionModule, MatAutocompleteModule, MatSelectModule, MatExpansionModule, MatCardModule, MatButtonModule, MatListModule, NgbCarouselModule
  ],
  providers: [  {
    provide: HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
