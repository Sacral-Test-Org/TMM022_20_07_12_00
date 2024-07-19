import { Component, Input } from '@angular/core';
import { ValidationService } from 'src/app/services/validation.service';

@Component({
  selector: 'app-edit-line-lov',
  templateUrl: './edit-line-lov.component.html',
  styleUrls: ['./edit-line-lov.component.css']
})
export class EditLineLovComponent {
  @Input() globalParameter: number;
  @Input() lineId: string;
  @Input() lineDescription: string;

  constructor(private validationService: ValidationService) {}

  onSelectLineId(lineId: string, lineDescription: string): void {
    this.validationService.validateLineIdAndDescription(this.globalParameter, lineId, lineDescription)
      .subscribe(
        (isValid: boolean) => {
          if (isValid) {
            // Navigate to PARTNO field
            const partNoField = document.getElementById('PARTNO');
            if (partNoField) {
              partNoField.focus();
            }
          } else {
            alert('Validation failed for Line ID and Line Description.');
          }
        },
        (error: any) => {
          console.error('Error during validation', error);
          alert('An error occurred during validation.');
        }
      );
  }
}
