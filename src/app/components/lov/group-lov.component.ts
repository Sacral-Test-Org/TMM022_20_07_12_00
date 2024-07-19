import { Component } from '@angular/core';
import { ValidationService } from '../../services/validation.service';

@Component({
  selector: 'app-group-lov',
  templateUrl: './group-lov.component.html',
  styleUrls: ['./group-lov.component.css']
})
export class GroupLovComponent {
  groupLovData: any[] = [];

  constructor(private validationService: ValidationService) {}

  show() {
    this.validationService.getGroupLovData().subscribe(
      (data: any[]) => {
        this.groupLovData = data;
      },
      (error: any) => {
        console.error('Error fetching GROUP_LOV data', error);
      }
    );
  }
}
