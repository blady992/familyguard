import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Person} from './person';
import {Observable} from 'rxjs';
import {PersonResponse} from './person-response';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private http: HttpClient) {
  }

  getPeople(): Observable<Person[]> {
    return this.http.get<PersonResponse>(`${environment.dataStorageUrl}/api/v1/people`)
      .pipe(map(personResponse => personResponse.content));
  }
}
