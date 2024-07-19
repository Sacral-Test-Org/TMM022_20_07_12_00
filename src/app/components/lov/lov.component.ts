import { Component, Input } from '@angular/core';
import { ValidationService } from '../../services/validation.service';

@Component({
  selector: 'app-lov',
  templateUrl: './lov.component.html',
  styleUrls: ['./lov.component.css']
})
export class LovComponent {
  @Input() globalParameter: number;
  lovData: any[] = [];

  constructor(private validationService: ValidationService) {}

  displayLov() {
    if (this.globalParameter === 0) {
      this.validationService.getLovData().subscribe(data => {
        this.lovData = data;
      });
    } else if (this.globalParameter === 1) {
      this.validationService.getEditLovData().subscribe(data => {
        this.lovData = data;
      });
    }
  }

  selectLovItem(selectedItem: any) {
    // Logic to handle the selection of an item from the LOV
    // Navigate to the Group ID field
    console.log('Selected Item:', selectedItem);
    // Assuming there's a method to navigate to the Group ID field
    this.navigateToGroupIdField();
  }

  showLOV() {
    if (this.globalParameter === 0) {
      this.validationService.getPartLovData().subscribe(data => {
        this.lovData = data;
      });
    } else if (this.globalParameter === 1) {
      this.validationService.getEditPartLovData().subscribe(data => {
        this.lovData = data;
      });
    }
  }

  showGroupLov() {
    this.validationService.getGroupLovData().subscribe(data => {
      this.lovData = data;
    });
  }

  showEditGroupLov() {
    this.validationService.getEditGroupLovData().subscribe(data => {
      this.lovData = data;
    });
  }

  private navigateToGroupIdField() {
    // Logic to navigate to the Group ID field
    console.log('Navigating to Group ID field');
  }
}
