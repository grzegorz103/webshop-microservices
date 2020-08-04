import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {IndexComponent} from './index/index.component';
import {NavbarComponent} from './navbar/navbar.component';
import {ProductComponent} from './product/product.component';
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { FooterComponent } from './footer/footer.component';
import {MatIconModule} from "@angular/material/icon";
import { ProductCreateComponent } from './product-create/product-create.component';
import {MatOptionModule} from "@angular/material/core";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatSelectModule} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    NavbarComponent,
    ProductComponent,
    FooterComponent,
    ProductCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, MatInputModule, MatIconModule,
    HttpClientModule, FormsModule, ReactiveFormsModule,
    BrowserAnimationsModule, MatOptionModule, MatAutocompleteModule, MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
