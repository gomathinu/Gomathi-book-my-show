import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less']
})
export class RegisterComponent {
  mobile: string = '';

  constructor(private userService: UserService, private router: Router) {}

  register(): void {
    if (!this.mobile) {
      alert('Please mobile number');
      return;
    }

    this.userService.register(this.mobile).subscribe(() => {
      localStorage.setItem('mobile', this.mobile);
      this.router.navigate(['/verify-otp']);
    });
  }
}