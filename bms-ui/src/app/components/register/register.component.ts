import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less']
})
export class RegisterComponent {
  user = { name: '', mobile: '', email: '' };

  constructor(private userService: UserService) {}

  register() {
    this.userService.register(this.user).subscribe(console.log);
  }
}
