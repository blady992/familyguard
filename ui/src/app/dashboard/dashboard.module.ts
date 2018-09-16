import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from './dashboard.component';
import {FamilyTreeModule} from "./family-tree/family-tree.module";

@NgModule({
  imports: [
    CommonModule,
    FamilyTreeModule
  ],
  declarations: [DashboardComponent]
})
export class DashboardModule {
}
