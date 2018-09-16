import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from './app-routing.module';
import {DashboardModule} from "./dashboard/dashboard.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LayoutModule} from '@angular/cdk/layout';
import {NavbarModule} from "./common/navbar/navbar.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DashboardModule,
    AppRoutingModule,
    LayoutModule,
    NavbarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
