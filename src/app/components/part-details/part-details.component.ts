import { Component, OnInit } from '@angular/core';
import { FormInitializationService } from 'src/app/services/form-initialization.service';
import { ValidationService } from 'src/app/services/validation.service';

@Component({
  selector: 'app-part-details',
  templateUrl: './part-details.component.html',
  styleUrls: ['./part-details.component.css']
})
export class PartDetailsComponent implements OnInit {
  partStatus: string = 'A';
  currentDate: Date = new Date();
  screenName: string = 'Part Details Screen';
  modeOfOperation: string = 'View';
  unitId: string = '';
  unitName: string = '';
  groupId: string = '';
  groupName: string = '';
  lineId: string = '';
  lineDescription: string = '';
  partId: string = '';
  partNumber: string = '';
  partDescription: string = '';
  saveButtonEnabled: boolean = false;

  constructor(
    private formInitializationService: FormInitializationService,
    private validationService: ValidationService
  ) {}

  ngOnInit(): void {
    this.formInitializationService.initializePartStatus();
  }

  showValidationMessage(message: string): void {
    alert(message);
  }

  moveFocusToField(fieldName: string): void {
    const field = document.getElementById(fieldName);
    if (field) {
      field.focus();
    }
  }

  onPartDescriptionClick(): void {
    if (this.saveButtonEnabled) {
      this.saveButtonEnabled = false;
    }
    if (this.partDescription) {
      this.partDescription = '';
    }
    this.moveFocusToField('partDescription');
  }

  validateAndNavigate(): void {
    if (!this.unitId || !this.unitName) {
      this.showValidationMessage('Unit ID and Unit Name should not be null');
      this.moveFocusToField('unitId');
      return;
    }
    if (!this.groupId || !this.groupName) {
      this.showValidationMessage('Group ID and Group Name should not be null');
      this.moveFocusToField('groupId');
      return;
    }
    if (!this.lineId || !this.lineDescription) {
      this.showValidationMessage('Line ID and Line Name should not be null');
      this.moveFocusToField('lineId');
      return;
    }
    if (this.validationService.globalParameter === 0) {
      if (!this.partNumber) {
        this.showValidationMessage('Part No and Part Description should not be null');
        this.moveFocusToField('partNumber');
        return;
      }
      if (this.unitId && this.unitName && this.groupId && this.groupName && this.lineId && this.lineDescription && this.partNumber && this.partDescription) {
        this.partStatus = 'A';
        this.moveFocusToField('partStatus');
      }
    } else if (this.validationService.globalParameter === 1) {
      if (!this.partId) {
        this.showValidationMessage('Kindly Choose data from LOV before changing Description');
        this.moveFocusToField('partNumber');
        return;
      }
      if (!this.partNumber || !this.partDescription) {
        this.showValidationMessage('Part No and Part Description should not be null');
        this.moveFocusToField('partNumber');
        return;
      }
      if (this.unitId && this.unitName && this.groupId && this.groupName && this.lineId && this.lineDescription && this.partId && this.partNumber && this.partDescription) {
        this.partStatus = 'A';
        this.moveFocusToField('partStatus');
      }
    }
  }
}