import { Component, OnInit } from '@angular/core';
import { FormInitializationService } from './services/form-initialization.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'T K A P - [IS]';
  currentFormName: string;

  constructor(private formInitializationService: FormInitializationService) {}

  ngOnInit() {
    this.initializeForm();
  }

  initializeForm() {
    this.formInitializationService.initializeForm();
  }
}
