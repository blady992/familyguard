import { SimulationNodeDatum } from 'd3';
import { Person } from '../people/person';

export class PersonNode implements SimulationNodeDatum {
    index?: number;
    x?: number;
    y?: number;
    vx?: number;
    vy?: number;
    fx?: number | null;
    fy?: number | null;

    private _person: Person;

    constructor(person: Person) {
        this._person = person;
    }

    get person() {
        return this._person;
    }
}
