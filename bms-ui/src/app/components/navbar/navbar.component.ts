import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar.component',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class NavbarComponent {

}
