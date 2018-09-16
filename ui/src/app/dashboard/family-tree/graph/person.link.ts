import {SimulationLinkDatum} from 'd3';
import {PersonNode} from './person.node';

export class PersonLink implements SimulationLinkDatum<PersonNode> {
  source: PersonNode;
  target: PersonNode;
  index?: number;

  constructor(source: PersonNode, target: PersonNode) {
    this.source = source;
    this.target = target;
  }
}
