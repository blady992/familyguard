import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  people = [
    {
        'id': 0,
        'name': 'Grandfather by father',
        'wives': [
            1
        ],
        'children': [
            4
        ]
    },
    {
        'id': 1,
        'name': 'Grandmother by father',
        'husbands': [
            0
        ],
        'children': [
            4
        ]
    },
    {
        'id': 2,
        'name': 'Grandfather by mother',
        'wives': [
            3
        ],
        'children': [
            5
        ]
    },
    {
        'id': 3,
        'name': 'Grandmother by mother',
        'husbands': [
            2
        ],
        'children': [
            5
        ]
    },
    {
        'id': 4,
        'name': 'Father',
        'father': 0,
        'mother': 1,
        'wives': [
            5
        ],
        'children': [
            6,
            7
        ]
    },
    {
        'id': 5,
        'name': 'Mother',
        'father': 2,
        'mother': 3,
        'husbands': [
            4
        ],
        'children': [
            6,
            7
        ]
    },
    {
        'id': 6,
        'name': 'Son',
        'father': 4,
        'mother': 5
    },
    {
        'id': 7,
        'name': 'Daughter',
        'father': 4,
        'mother': 5
    }
];
}
