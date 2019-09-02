import { Component } from '@angular/core';
import { PersonsService } from './persons.service';
import { Validators } from '@angular/forms';
import { windowWhen } from 'rxjs/operators';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-newperson',
  template: `
    <h1>New Person Form</h1>

      <form [formGroup]="personForm" (ngSubmit)="add()">
      <div>
        <label for="name">Name: </label><input  type="text" name="name" id="name" placeholder="Person name" required formControlName="name" />
        <span [hidden]="personForm.controls.name.valid || personForm.controls.name.pristine">You must specify the name</span>
      </div>
      <div>
        <label for="surname">Surname: </label><input  type="text" name="surname" id="surname" placeholder="Person surname" required formControlName="surname" />
        <span [hidden]="personForm.controls.surname.valid || personForm.controls.surname.pristine">You must specify the surname</span>
      </div>
      <div>
        <label for="fiscalCode">CF: </label><input  type="text" name="fiscalCode" id="fiscalCode" placeholder="Person fiscal code" required formControlName="fiscalCode" />
        <span [hidden]="personForm.controls.fiscalCode.valid || personForm.controls.fiscalCode.pristine">You must specify the CF</span>
      </div>
      <div>
        <label for="birthDate">Birth date: </label><input  type="date" name="birthDate" id="birthDate" required formControlName="birthDate" />
        <span [hidden]="personForm.controls.birthDate.valid || personForm.controls.birthDate.pristine">You must specify the birthdate</span>
      </div>
        <button type="submit" [disabled]="!personForm.valid">Submit</button>
      </form>

  `,
  providers: [PersonsService],
  styleUrls: ['./newperson.component.css']
})
export class NewpersonComponent {
  constructor(private fb: FormBuilder, private personsService: PersonsService) { }

  personForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(1)]],
    surname: ['', Validators.required],
    fiscalCode: ['', Validators.required],
    birthDate: ['', Validators.required]
});

  add() {
    //console.log(JSON.stringify(this.personForm.value));
    this.personsService.addPerson(JSON.stringify(this.personForm.value));
  }
}
