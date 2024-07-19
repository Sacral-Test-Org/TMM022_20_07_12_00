import { Component, OnInit } from '@angular/core';
import { ValidationService } from 'src/app/services/validation.service';

@Component({
  selector: 'app-edit-group-lov',
  templateUrl: './edit-group-lov.component.html',
  styleUrls: ['./edit-group-lov.component.css']
})
export class EditGroupLovComponent implements OnInit {
  editGroupLovData: any[] = [];

  constructor(private validationService: ValidationService) { }

  ngOnInit(): void {
    this.show();
  }

  show(): void {
    this.validationService.getEditGroupLovData().subscribe(
      (data: any[]) => {
        this.editGroupLovData = data;
      },
      (error: any) => {
        console.error('Error fetching EDIT_GROUP_LOV data', error);
      }
    );
  }
}
