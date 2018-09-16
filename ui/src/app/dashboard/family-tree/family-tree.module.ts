import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {FamilyTreeComponent} from './family-tree.component';
import {NodeVisualComponent} from './graph/visuals/node-visual.component';
import {LinkVisualComponent} from './graph/visuals/link-visual.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [FamilyTreeComponent, NodeVisualComponent, LinkVisualComponent],
  exports: [FamilyTreeComponent]
})
export class FamilyTreeModule {
}
