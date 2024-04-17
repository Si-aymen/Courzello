import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-layout',
  templateUrl: './user-layout.component.html',
  styleUrl: './user-layout.component.scss'
})
export class UserLayoutComponent {
  title = 'frontOfficePiDev';
  constructor(
    private router:Router
) {

}
//rech
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  console.log(filterValue);
  if (filterValue.trim() === '') {
    this.router.navigate(['/front-office/forum-v']);
  } else {
    this.router.navigate(['/front-office/forum-v'], { queryParams: { filterValue: filterValue.trim() } });
  }
}
}
