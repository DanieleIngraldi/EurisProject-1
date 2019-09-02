import { Component, OnInit } from '@angular/core';

import { Person } from './person';
import { PersonsService } from './persons.service';


@Component({
  selector: 'mg-persons',
  templateUrl: './persons.component.html',
  providers: [PersonsService],
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {
  persons: Person[];
  editPerson: Person;

  constructor(private personsService: PersonsService) { }

  ngOnInit() {
    this.getPersons();
  }

  getPersons(): void {
    this.personsService.getPersons()
      .subscribe(persons => (this.persons = persons));
  }

  deletePerson(person: Person): void {
    this.persons = this.persons.filter(p => p !== person);
    this.personsService
      .deletePerson(person.id)
      .subscribe();
    /*
    // oops ... subscribe() is missing so nothing happens
    this.personsService.deletePerson(hero.id);
    */
  }

  edit(person: Person) {
    this.editPerson = person;
  }

  updatePerson() {
    if (this.editPerson) {
      this.personsService
        .updatePerson(this.editPerson)
        .subscribe(person => {
          // replace the person in the persons list with update from server
          const ix = person ? this.persons.findIndex(p => p.id === person.id) : -1;
          if (ix > -1) {
            this.persons[ix] = person;
          }
        });
      this.editPerson = undefined;
    }
  }

  showinsertrow(){ // add a new person with mock datas
      this.personsService.addPerson({"name": "NewPersonName","surname": "NewPersonSurname","fiscalCode": "EDITCF","birthDate": "1970-01-01"});
  }
}
