import { Simulation, forceSimulation, forceManyBody, forceCollide, forceLink, forceCenter, forceY } from 'd3';
import { Person } from './person';
import { PersonNode } from './person.node';
import { PersonLink } from './person.link';
import * as _ from 'underscore';

export class PeopleGraph {
    private _simulation: Simulation<any, any>;
    private _nodes: PersonNode[];
    private _links: PersonLink[];

    get simulation() {
        return this._simulation;
    }

    get nodes() {
        return this._nodes;
    }

    get links() {
        return this._links;
    }

    constructor(people: Person[]) {
        this._nodes = people.map(person => new PersonNode(person));
        this._links = [];
        this._nodes.forEach(node =>
            (node.person.children || []).map(childId =>
                this._nodes.find(nodeToFind =>
                    nodeToFind.person.id === childId))
                .forEach(childNode =>
                    this._links.push(new PersonLink(node, childNode))));
        const generationMap = this.initGenerationMap(people);
        this.initSimulation(this._nodes, this._links, generationMap);
    }

    private initSimulation(nodes: PersonNode[], links: PersonLink[], generationMap) {
        this._simulation = forceSimulation()
            .force('charge', forceManyBody().strength(-300))
            .force('links', forceLink(links).id(d => d['id']).strength(0.2))
            .force('centers', forceCenter(400, 300))
            .force('sorts', forceY((d, i, data) => generationMap[d['person']['id']] * 100).strength(1))
            ;
        this._simulation.nodes(nodes);
    }

    private initGenerationMap(people: Person[]) {
        const generationMap = {};
        const peopleCopy = Object.assign([], people);
        while (peopleCopy.length > 0) {
            this.generateMapRecursive(generationMap, peopleCopy[0], peopleCopy, 0);
        }
        return generationMap;
    }

    private generateMapRecursive(generationMap: any, person: Person, people: Person[], level: number) {
        if (!people.includes(person)) {
            return;
        }
        people.splice(people.indexOf(person), 1);
        generationMap[person.id] = level;

        if (person.father) {
            this.generateMapRecursive(generationMap, people.find(p => p.id === person.father), people, level - 1);
        }
        if (person.mother) {
            this.generateMapRecursive(generationMap, people.find(p => p.id === person.mother), people, level - 1);
        }

        if (person.children) {
            person.children
                .map(childId => people.find(p => p.id === childId))
                .forEach(child => this.generateMapRecursive(generationMap, child, people, level + 1));
        }
    }
}
