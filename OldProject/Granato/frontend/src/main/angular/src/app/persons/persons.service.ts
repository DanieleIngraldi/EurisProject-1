import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Person } from './person';
import { HttpErrorHandler, HandleError } from '../http-error-handler.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class PersonsService {
  apiUrl = '/api/';  // URL to web api
  private handleError: HandleError;

  constructor(
    private http: HttpClient,
    httpErrorHandler: HttpErrorHandler) {
    this.handleError = httpErrorHandler.createHandleError('PersonsService');
  }

  /** GET persons from the server */
  getPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(this.apiUrl + "getall")
      .pipe(
        catchError(this.handleError('getPersons', []))
      );
  }

  /* DELETE a person from the server */
  deletePerson(id: number): Observable<{}> {
    const url = `${this.apiUrl}delete/${id}`; // DELETE api/heroes/42
    return this.http.delete(url, httpOptions)
      .pipe(
        catchError(this.handleError('deletePerson'))
      );
  }

  /* UPDATE (PUT) info of a person */
  updatePerson(person: Person): Observable<Person> {
    return this.http.put<Person>(this.apiUrl + "/editperson", person, httpOptions)
      .pipe(
        catchError(this.handleError('updatePerson', person))
      );
  }

  /* ADD (POST) a new person */
  addPerson(jsonData) {
    this.http.post(this.apiUrl + "/newperson", jsonData, httpOptions)
      .subscribe(
        data => {
          // console.log("POST Request is successful ", data);
          window.location.href = '/listpersons';
        },
        error => {
          console.log("Error", error);
        }
      );

  }
}
