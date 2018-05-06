import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardComponent } from './dashboard.component';
import { NodeVisualComponent } from './visuals/node-visual.component';
import { LinkVisualComponent } from './visuals/link-visual.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [DashboardComponent, NodeVisualComponent, LinkVisualComponent],
  exports: [DashboardComponent]
})
export class DashboardModule { }
