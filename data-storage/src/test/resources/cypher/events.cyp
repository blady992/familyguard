CREATE (EventWithLocation:Event {name: 'Event with location', description: 'Description of event with location', locationName: 'FTIMS', locationLatitude: 51.747237, locationLongitude: 19.4534342}),
       (EventWithoutLocation:Event {name: 'Event without location', description: 'Description of event without location'}),
       (FirstParticipant:Person {name: 'First participant', sex: 'MALE'}),
       (SecondParticipant:Person {name: 'Second participant', sex: 'FEMALE'}),

       (EventWithLocation)<-[:PARTICIPATES]-(FirstParticipant),
       (EventWithoutLocation)<-[:PARTICIPATES]-(SecondParticipant);
