import { Component } from '@angular/core';
import { ValidationService } from 'src/app/services/validation.service';

@Component({
  selector: 'app-line-lov',
  templateUrl: './line-lov.component.html',
  styleUrls: ['./line-lov.component.css']
})
export class LineLovComponent {
  globalParameter: number;
  lineId: string;
  lineDescription: string;

  constructor(private validationService: ValidationService) {}

  onSelectLineId(lineId: string, lineDescription: string): void {
    this.lineId = lineId;
    this.lineDescription = lineDescription;

    this.validationService.validateLineIdAndDescription(this.globalParameter, this.lineId, this.lineDescription)
      .then(isValid => {
        if (isValid) {
          this.navigateToPartNoField();
        } else {
          alert('Validation failed. Please check Line ID and Line Description.');
        }
      })
      .catch(error => {
        console.error('Error during validation:', error);
        alert('An error occurred during validation.');
      });
  }

  private navigateToPartNoField(): void {
    // Logic to navigate to the PARTNO field
    console.log('Navigating to PARTNO field');
  }
}
