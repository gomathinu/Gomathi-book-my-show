import { Component, computed, signal } from '@angular/core';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common'; 

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.less'],
  imports: [NgIf]
})
export class NavbarComponent {
  isLoggedIn = computed(() => !!localStorage.getItem('token'));

  constructor(private router: Router) {}

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}