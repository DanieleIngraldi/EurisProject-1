import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }      from '@angular/forms';
import { NgModule, ViewChild } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProvaComponent } from './prova/prova.component';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { PersonsComponent } from './persons/persons.component';
import { ConfigComponent }      from './config/config.component';

import { HttpErrorHandler }     from './http-error-handler.service';
import { MessageService }       from './message.service';
import { NewpersonComponent } from './persons/newperson.component';

@NgModule({
  declarations: [
    AppComponent,
    ConfigComponent,
    ProvaComponent,
    HomeComponent,
    MenuComponent,
    PersonsComponent,
    NewpersonComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    HttpErrorHandler,
    MessageService
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
