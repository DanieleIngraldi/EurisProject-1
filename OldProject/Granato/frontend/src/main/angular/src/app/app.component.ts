import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
      <mg-menu></mg-menu>
      <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular';
}
