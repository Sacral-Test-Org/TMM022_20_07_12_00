import { Component, OnInit } from '@angular/core';
import { FormInitializationService } from 'src/app/services/form-initialization.service';
import { ValidationService } from 'src/app/services/validation.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  globalParameter: number = 0;
  formTitle: string = 'T K A P - [IS]';
  currentFormName: string = '';
  currentDate: string = '';
  partStatus: string = '';
  saveButtonEnabled: boolean = false;
  unitIdEnabled: boolean = true;

  constructor(
    private formInitializationService: FormInitializationService,
    private validationService: ValidationService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.formInitializationService.initializeForm();
    this.currentFormName = this.formInitializationService.getCurrentFormName();
    this.currentDate = this.formInitializationService.getCurrentDate();
    this.globalParameter = this.formInitializationService.getGlobalParameter();
    this.setFormMode();
    this.setCursorStyle('default');
    this.disableSaveButton();
    this.enableFields(['groupId', 'partNumber', 'partStatus', 'partDescription', 'lineId']);
    if (!this.unitIdEnabled) {
      this.enableField('unitId');
    }
    this.moveCursorToField('unitId');
  }

  setFormMode(): void {
    if (this.globalParameter === 0) {
      this.partStatus = 'Create Mode';
    } else {
      this.partStatus = 'Edit Mode';
    }
  }

  setCursorStyle(style: string): void {
    document.body.style.cursor = style;
  }

  disableSaveButton(): void {
    this.saveButtonEnabled = false;
  }

  enableFields(fields: string[]): void {
    fields.forEach(field => this.enableField(field));
  }

  enableField(field: string): void {
    // Logic to enable the field
  }

  moveCursorToField(field: string): void {
    // Logic to move cursor to the specified field
  }

  saveData(): void {
    if (this.validateFields()) {
      // Logic to save data
    }
  }

  clearFields(): void {
    // Logic to clear all input fields
  }

  editData(): void {
    this.clearFields();
    this.initializeForm();
    this.partStatus = 'A';
    this.setFormMode();
    this.disableSaveButton();
  }

  exitApplication(): void {
    if (confirm('Do you want to exit?')) {
      // Logic to close the application
    }
  }

  validateFields(): boolean {
    if (!this.partStatus) {
      alert('Part Status should not be empty');
      return false;
    }
    const requiredFields = ['unitId', 'unitName', 'groupId', 'groupName', 'lineId', 'lineDescription'];
    for (const field of requiredFields) {
      if (!this[field]) {
        alert(`${field} should not be empty`);
        return false;
      }
    }
    if (this.globalParameter === 0 && !this['partNo']) {
      alert('Part No should not be empty');
      return false;
    }
    if (this.globalParameter === 1 && !this['partId']) {
      alert('Part ID should not be empty');
      return false;
    }
    if (this.globalParameter === 1 && (!this['partNo'] || !this['partDescription'])) {
      alert('Part No and Part Description should not be empty');
      return false;
    }
    return true;
  }

  onPartStatusChange(): void {
    this.validateFields();
  }

  onPartDescriptionClick(): void {
    if (this.saveButtonEnabled) {
      this.disableSaveButton();
    }
    if (this['partDescription']) {
      this['partDescription'] = '';
    }
    this.moveCursorToField('partDescription');
  }

  handleDoubleClick(event: any): void {
    if (this.globalParameter === 0) {
      // Display LOV for Unit ID
    } else {
      // Display different LOV for editing Unit ID
    }
    this.moveCursorToField('groupId');
  }

  handleClick(event: any): void {
    this.disableSaveButton();
    this.clearFields();
    this.moveCursorToField('unitId');
  }

  handleFieldNavigation(event: any): void {
    if (!this['unitId'] || !this['unitName']) {
      alert('Unit ID and Unit Name should not be empty');
      this.moveCursorToField('unitId');
      return;
    }
    this.moveCursorToField('groupId');
  }

  validateUnitID(unitID: string, unitName: string): boolean {
    return this.validationService.validateUnitID(unitID, unitName, this.globalParameter);
  }

  onPartNumberDoubleClick(): void {
    if (this.globalParameter === 0) {
      // Display LOV for part numbers
    } else {
      // Display different LOV for editing part numbers
    }
    this.moveCursorToField('partDescription');
  }

  onPartNumberClick(): void {
    this.disableSaveButton();
    this.clearFields();
    this.moveCursorToField('partNumber');
  }

  onNextItem(): void {
    if (!this.validateFields()) {
      return;
    }
    this.moveCursorToField('partDescription');
  }

  validatePartNumber(): boolean {
    return this.validationService.validatePartNumber(this['partNumber'], this.globalParameter);
  }

  onExitButtonClick(): void {
    if (confirm('Do you want to exit?')) {
      // Logic to close the application
    }
  }

  clearForm(): void {
    this.formInitializationService.resetFormFields();
    if (!this.unitIdEnabled) {
      this.unitIdEnabled = true;
    }
    this.partStatus = 'A';
    this.formInitializationService.executeWhenNewFormInstanceTrigger();
  }

  handleEditButtonClick(): void {
    this.clearForm();
    this.initializeForm();
    this.partStatus = 'A';
    this.setFormMode();
    this.disableSaveButton();
  }

  onLineIdDoubleClick(): void {
    if (this.globalParameter === 0) {
      // Display LINE_LOV
    } else {
      // Display EDIT_LINE_LOV
    }
    this.moveCursorToField('partNumber');
  }

  onLineIdClick(): void {
    this.disableSaveButton();
    this.clearFields();
    this.moveCursorToField('lineId');
  }

  onLineIdValidate(): void {
    if (!this.validationService.validateLineIdAndDescription(this.globalParameter, this['lineId'], this['lineDescription'])) {
      alert('Invalid Line ID or Line Description');
    }
  }

  handleGroupIdDoubleClick(): void {
    if (this.globalParameter === 0) {
      // Display GROUP_LOV
    } else {
      // Display EDIT_GROUP_LOV
    }
    this.moveCursorToField('lineId');
  }

  handleGroupIdClick(): void {
    this.disableSaveButton();
    this.clearFields();
    this.moveCursorToField('groupId');
  }

  validateForm(): boolean {
    return this.validationService.validatePartForm({
      partId: this['partId'],
      unitName: this['unitName'],
      groupName: this['groupName'],
      lineDescription: this['lineDescription'],
      partNumber: this['partNumber'],
      partDescription: this['partDescription'],
      partStatus: this['partStatus']
    });
  }

  saveOrUpdatePart(): void {
    if (this.validateForm()) {
      if (confirm('Do you want to save or update the part information?')) {
        this.formInitializationService.saveOrUpdatePart({
          partId: this['partId'],
          unitName: this['unitName'],
          groupName: this['groupName'],
          lineDescription: this['lineDescription'],
          partNumber: this['partNumber'],
          partDescription: this['partDescription'],
          partStatus: this['partStatus']
        });
        alert('Part information saved successfully');
        this.clearForm();
      }
    }
  }
}