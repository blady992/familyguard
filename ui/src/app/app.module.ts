import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { DashboardModule } from './dashboard/dashboard.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    DashboardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
