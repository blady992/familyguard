CREATE (GrandfatherByFather:Person {name: 'Grandfather by father', sex: 'MALE'}),
       (GrandmotherByFather:Person {name: 'Grandmother by father', sex: 'FEMALE'}),
       (GrandfatherByMother:Person {name: 'Grandfather by mother', sex: 'MALE'}),
       (GrandmotherByMother:Person {name: 'Grandmother by mother', sex: 'FEMALE'}),
       (Father:Person {name: 'Father', sex: 'MALE'}),
       (Mother:Person {name: 'Mother', sex: 'FEMALE'}),
       (Son:Person {name: 'Son', sex: 'MALE'}),
       (Daughter:Person {name: 'Daughter', sex: 'FEMALE'}),
       (Event:Event {name: 'Event name', description: 'Event description'}),

       (Father)<-[:PARENT_OF]-(GrandfatherByFather),
       (Father)<-[:PARENT_OF]-(GrandmotherByFather),

       (Mother)<-[:PARENT_OF]-(GrandfatherByMother),
       (Mother)<-[:PARENT_OF]-(GrandmotherByMother),

       (Son)<-[:PARENT_OF]-(Father),
       (Son)<-[:PARENT_OF]-(Mother),
       (Daughter)<-[:PARENT_OF]-(Father),
       (Daughter)<-[:PARENT_OF]-(Mother),

       (Father)-[:PARTICIPATES]->(Event),
       (Mother)-[:PARTICIPATES]->(Event);
