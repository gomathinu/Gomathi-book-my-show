import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less'] 
})
export class LoginComponent {
  mobile = '';

  constructor(private userService: UserService) {}

  login() {
    this.userService.login(this.mobile).subscribe(console.log);
  }
}