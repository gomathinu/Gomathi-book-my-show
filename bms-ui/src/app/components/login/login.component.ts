import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class LoginComponent {
  mobile = '';

  constructor(private userService: UserService) {}

  login() {
    this.userService.login(this.mobile).subscribe(console.log);
  }
}