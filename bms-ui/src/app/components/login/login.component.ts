import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent {
  mobile: string = '';

  constructor(private userService: UserService, private router: Router) {}

  login(): void {
    if (!this.mobile) {
      alert('Please enter a valid mobile number');
      return;
    }

    this.userService.login(this.mobile).subscribe(() => {
      localStorage.setItem('mobile', this.mobile);
      this.router.navigate(['/verify-otp']);
    });
  }
}