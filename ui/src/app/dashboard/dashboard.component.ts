import { Component, Input } from '@angular/core';
import { Person } from '../people/person';
import { PeopleGraph } from './graph';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
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
