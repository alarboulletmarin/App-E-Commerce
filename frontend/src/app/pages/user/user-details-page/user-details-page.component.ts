import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/core/models/user.model';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-user-details-page',
  templateUrl: './user-details-page.component.html',
  styleUrls: ['./user-details-page.component.sass'],
})
export class UserDetailsPageComponent implements OnInit {
  public user!: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserDetails().subscribe((data) => {
      this.user = data;
    });
  }
}
