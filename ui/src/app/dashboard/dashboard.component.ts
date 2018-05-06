import { Component, OnInit, Input } from '@angular/core';
import { Person } from './person';
import * as d3 from 'd3';
import { PeopleGraph } from './graph';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private _people: Person[];
  private _graph: PeopleGraph;

  @Input()
  set people(people: Person[]) {
    this._people = people;
  }

  get people() {
    return this._people;
  }

  get graph() {
    return this._graph;
  }

  ngOnInit(): void {
    this._graph = new PeopleGraph(this.people);
  }
}
