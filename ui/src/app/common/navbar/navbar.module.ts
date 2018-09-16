import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavbarComponent} from './navbar.component';
import {MatSidenavModule, MatToolbarModule} from "@angular/material";

@NgModule({
  imports: [
    CommonModule,
    MatSidenavModule,
    MatToolbarModule,
  ],
  declarations: [NavbarComponent],
  exports: [NavbarComponent]
})
export class NavbarModule {
}
