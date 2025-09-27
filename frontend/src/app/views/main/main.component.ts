import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
 p: number = 1;
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  logout(): void{
    this.authService.logout();
  }

}
