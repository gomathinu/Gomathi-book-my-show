import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class RegisterComponent {
  user = { name: '', mobile: '', email: '' };

  constructor(private userService: UserService) {}

  register() {
    this.userService.register(this.user).subscribe(console.log);
  }
}
