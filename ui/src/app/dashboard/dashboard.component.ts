import {Component} from '@angular/core';
import {PeopleService} from "../common/people/people.service";
import {Person} from "../common/people/person";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  people: Person[] = [];

  constructor(peopleService: PeopleService) {
    peopleService.getPeople()
      .subscribe(people => this.people = people);
  }
}
