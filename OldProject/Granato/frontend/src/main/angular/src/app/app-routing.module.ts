import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProvaComponent } from './prova/prova.component';
import { PersonsComponent } from './persons/persons.component';
import { NewpersonComponent } from './persons/newperson.component';


const routes: Routes = [
   {path: '', component: HomeComponent},
   {path: 'prova', component: ProvaComponent},
   {path: 'listpersons', component: PersonsComponent},
   {path: 'newperson', component: NewpersonComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
