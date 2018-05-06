import { Component, Input } from '@angular/core';
import { PersonLink } from '../person.link';

@Component({
    selector: '[app-link-visual]',
    templateUrl: './link-visual.component.html',
    styleUrls: ['./link-visual.component.css']
})
export class LinkVisualComponent {
    @Input()
    link: PersonLink;
}
