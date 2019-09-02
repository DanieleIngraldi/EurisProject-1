import { TestBed } from '@angular/core/testing';

import { PersonsService } from './persons.service';

describe('PersonsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonsService = TestBed.get(PersonsService);
    expect(service).toBeTruthy();
  });
});





/*
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

// Other imports
import { TestBed } from '@angular/core/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { Hero } from './hero';
import { PersonsService } from './persons.service';
import { HttpErrorHandler } from '../http-error-handler.service';
import { MessageService } from '../message.service';

describe('PersonsService', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let personService: PersonsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      // Import the HttpClient mocking services
      imports: [ HttpClientTestingModule ],
      // Provide the service-under-test and its dependencies
      providers: [
        PersonsService,
        HttpErrorHandler,
        MessageService
      ]
    });

    // Inject the http, test controller, and service-under-test
    // as they will be referenced by each test.
    httpClient = TestBed.get(HttpClient);
    httpTestingController = TestBed.get(HttpTestingController);
    personService = TestBed.get(PersonsService);
  });

  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpTestingController.verify();
  });

  /// personService method tests begin ///

  describe('#getPersons', () => {
    let expectedPersons: Person[];

    beforeEach(() => {
      personService = TestBed.get(PersonsService);
      expectedPersons = [
       {
               "id": 1,
               "fiscalCode": "FAKECFMOCK",
               "name": "Jack",
               "surname": "Bauer",
               "birthDate": "1980-01-30"
           },
           {
               "id": 2,
               "fiscalCode": "FAKECFMOCK",
               "name": "Chloe",
               "surname": "O'Brian",
               "birthDate": "1987-05-15"
           },
           {
               "id": 3,
               "fiscalCode": "FAKECFMOCK",
               "name": "Kim",
               "surname": "Bauer",
               "birthDate": "1990-02-14"
           },
           {
               "id": 4,
               "fiscalCode": "FAKECFMOCK",
               "name": "David",
               "surname": "Palmer",
               "birthDate": "1955-07-06"
           },
           {
               "id": 5,
               "fiscalCode": "FAKECFMOCK",
               "name": "Michelle",
               "surname": "Dessler",
               "birthDate": "1954-04-23"
           }
       ] as Person[];
    });

    it('should return expected persons (called once)', () => {

      personService.getPersons().subscribe(
        persons => expect(persons).toEqual(expectedPersons, 'should return expected persons'),
        fail
      );

      // personService should have made one request to GET persons from expected URL
      const req = httpTestingController.expectOne(personService.personsUrl);
      expect(req.request.method).toEqual('GET');

      // Respond with the mock persons
      req.flush(expectedPersons);
    });

    it('should be OK returning no persons', () => {

      personService.getPersons().subscribe(
        persons => expect(persons.length).toEqual(0, 'should have empty persons array'),
        fail
      );

      const req = httpTestingController.expectOne(personService.personsUrl);
      req.flush([]); // Respond with no persons
    });

    // This service reports the error but finds a way to let the app keep going.
    it('should turn 404 into an empty persons result', () => {

      personService.getPersons().subscribe(
        persons => expect(persons.length).toEqual(0, 'should return empty persons array'),
        fail
      );

      const req = httpTestingController.expectOne(personService.personsUrl);

      // respond with a 404 and the error message in the body
      const msg = 'deliberate 404 error';
      req.flush(msg, {status: 404, statusText: 'Not Found'});
    });

    it('should return expected persons (called multiple times)', () => {

      personService.getPersons().subscribe();
      personService.getPersons().subscribe();
      personService.getPersons().subscribe(
        persons => expect(persons).toEqual(expectedPersons, 'should return expected persons'),
        fail
      );

      const requests = httpTestingController.match(personService.personsUrl);
      expect(requests.length).toEqual(3, 'calls to getPersons()');

      // Respond to each request with different mock hero results
      requests[0].flush([]);
      requests[1].flush([{id: 1, name: 'bob'}]);
      requests[2].flush(expectedPersons);
    });
  });

  describe('#updateHero', () => {
    // Expecting the query form of URL so should not 404 when id not found
    const makeUrl = (id: number) => `${personService.personsUrl}/?id=${id}`;

    it('should update a hero and return it', () => {

      const updateHero: Hero = { id: 1, name: 'A' };

      personService.updateHero(updateHero).subscribe(
        data => expect(data).toEqual(updateHero, 'should return the hero'),
        fail
      );

      // personService should have made one request to PUT hero
      const req = httpTestingController.expectOne(personService.personsUrl);
      expect(req.request.method).toEqual('PUT');
      expect(req.request.body).toEqual(updateHero);

      // Expect server to return the hero after PUT
      const expectedResponse = new HttpResponse(
        { status: 200, statusText: 'OK', body: updateHero });
      req.event(expectedResponse);
    });

    // This service reports the error but finds a way to let the app keep going.
    it('should turn 404 error into return of the update hero', () => {
      const updateHero: Hero = { id: 1, name: 'A' };

      personService.updateHero(updateHero).subscribe(
        data => expect(data).toEqual(updateHero, 'should return the update hero'),
        fail
      );

      const req = httpTestingController.expectOne(personService.personsUrl);

      // respond with a 404 and the error message in the body
      const msg = 'deliberate 404 error';
      req.flush(msg, {status: 404, statusText: 'Not Found'});
    });
  });

  // TODO: test other personService methods
});
*/
