import { Component } from '@angular/core';
import { PeopleService } from './people/people.service';
import { Person } from './people/person';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(peopleService: PeopleService) {
    peopleService.getPeople()
      .subscribe(people => this.people = people);
  }

  people: Person[] = [];
}
