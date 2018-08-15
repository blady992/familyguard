import {forceCenter, forceLink, forceManyBody, forceSimulation, forceY, Simulation} from 'd3';
import {Person} from '../people/person';
import {PersonNode} from './person.node';
import {PersonLink} from './person.link';

export class PeopleGraph {
  private readonly _simulation: Simulation<any, any>;
  private readonly _nodes: PersonNode[];
  private readonly _childLinks: PersonLink[];
  private readonly _marriageLinks: PersonLink[];

  get simulation() {
    return this._simulation;
  }

  get nodes() {
    return this._nodes;
  }

  get childLinks() {
    return this._childLinks;
  }

  get marriageLinks() {
    return this._marriageLinks;
  }

  constructor(people: Person[]) {
    this._nodes = people.map(person => new PersonNode(person));
    this._childLinks = this.initChildLinks(this._nodes);
    this._marriageLinks = this.initMarriageLinks(this._nodes);

    const generationMap = this.initGenerationMap(people);
    this._simulation = this.initSimulation(generationMap);
  }

  private initSimulation(generationMap) {
    const simulation = forceSimulation()
      .force('charge', forceManyBody().strength(-300))
      .force('child_links', forceLink(this._childLinks).id(d => d['id']).strength(0.2))
      .force('marriage_links', forceLink(this._marriageLinks).id(d => d['id']).strength(0.2))
      .force('centers', forceCenter(400, 300))
      .force('sorts', forceY((d) => generationMap[d['person']['id']] * 100).strength(1))
    ;
    simulation.nodes(this._nodes);
    return simulation;
  }

  private initChildLinks(nodes: PersonNode[]) {
    const childLinks = [];
    nodes.forEach(node =>
      (node.person.children || []).map(childId =>
        nodes.find(nodeToFind =>
          nodeToFind.person.id === childId))
        .forEach(childNode =>
          childLinks.push(new PersonLink(node, childNode))));
    return childLinks;
  }

  private initMarriageLinks(nodes: PersonNode[]) {
    const marriageLinks: PersonLink[] = [];
    nodes.forEach(node =>
      (node.person.husbands || []).concat(node.person.wives || []).map(spouseId =>
        nodes.find(nodeToFind =>
          nodeToFind.person.id === spouseId))
        .forEach(spouseNode => {
          if (!marriageLinks.find((val) => val.source === node && val.target === spouseNode) && !marriageLinks.find((val) => val.target === node && val.source === spouseNode)) {
            marriageLinks.push(new PersonLink(node, spouseNode))
          }
        }));
    return marriageLinks;
  }

  private initGenerationMap(people: Person[]): Map<number, number> {
    const generationMap = new Map();
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
