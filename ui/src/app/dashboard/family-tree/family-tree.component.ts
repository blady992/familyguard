import {Component, Input} from '@angular/core';
import {Person} from '../../common/people/person';
import {PeopleGraph} from './graph/graph';

@Component({
  selector: 'app-family-tree',
  templateUrl: './family-tree.component.html',
  styleUrls: ['./family-tree.component.css']
})
export class FamilyTreeComponent {
  private _people: Person[];
  private _graph: PeopleGraph;

  @Input()
  set people(people: Person[]) {
    this._people = people;
    this._graph = new PeopleGraph(this.people);
  }

  get people() {
    return this._people;
  }

  get graph() {
    return this._graph;
  }
}
