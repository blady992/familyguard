import { Simulation, forceSimulation, forceManyBody, forceCollide, forceLink, forceCenter, forceY } from 'd3';
import { Person } from './person';
import { PersonNode } from './person.node';
import { PersonLink } from './person.link';

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
        let lastGeneration: Person[];
        let generation = people.filter(person => !person.children || person.children.length === 0);
        let generationIteration = 1;
        while (generation.length > 0) {
            lastGeneration = generation;
            generation = people.filter(person =>
                lastGeneration.map(p => p.father).indexOf(person.id) >= 0 ||
                lastGeneration.map(p => p.mother).indexOf(person.id) >= 0);
        }
        lastGeneration.forEach(person => generationMap[person.id] = generationIteration);
        generationIteration++;
        generation = lastGeneration;
        while (generation.length > 0) {
            generation = people.filter(person =>
                generation.map(p => p.children).reduce((a, b) => (a || []).concat(b)).indexOf(person.id) >= 0);
            generation.forEach(person => generationMap[person.id] = generationIteration);
            generationIteration++;
        }
        return generationMap;
    }
}
