import {Component, Input} from '@angular/core';
import {PersonNode} from '../person.node';

@Component({
  selector: '[app-node-visual]',
  templateUrl: './node-visual.component.html',
  styleUrls: ['./node-visual.component.css']
})
export class NodeVisualComponent {
  @Input()
  node: PersonNode;
}
