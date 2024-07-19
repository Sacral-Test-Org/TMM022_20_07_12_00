import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormInitializationService {
  private globalParameter: number = 0;
  private currentFormName: string = 'FormName';

  initializeForm(): void {
    // Set the main application window title
    document.title = 'T K A P - [IS]';

    // Store the current form name in a variable
    this.currentFormName = 'CurrentFormName';

    // Set the current date in the SYSDATE field, truncated to remove the time portion
    const currentDate = new Date().toISOString().split('T')[0];
    (document.getElementById('SYSDATE') as HTMLInputElement).value = currentDate;

    // Initialize a global parameter to 0
    this.globalParameter = 0;

    // Determine the form mode based on the global parameter and set it to either "Create Mode" or "Edit Mode"
    const formMode = this.globalParameter === 0 ? 'Create Mode' : 'Edit Mode';
    (document.getElementById('formMode') as HTMLInputElement).value = formMode;

    // Set the cursor style to default
    document.body.style.cursor = 'default';

    // Disable the "SAVE" button
    (document.getElementById('SAVE') as HTMLButtonElement).disabled = true;

    // Enable the fields: Group ID, Part Number, Part Status, Part Description, Line ID
    ['GroupID', 'PartNumber', 'PartStatus', 'PartDescription', 'LineID'].forEach(fieldId => {
      (document.getElementById(fieldId) as HTMLInputElement).disabled = false;
    });

    // If the "Unit ID" field is not enabled, enable it
    const unitIdField = document.getElementById('UnitID') as HTMLInputElement;
    if (unitIdField.disabled) {
      unitIdField.disabled = false;
    }

    // Move the cursor to the "Unit ID" field
    unitIdField.focus();
  }

  resetFormFields(): void {
    // Clear all form fields without validation
    const formElements = document.querySelectorAll('input, select, textarea');
    formElements.forEach(element => {
      if (element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement) {
        element.value = '';
      } else if (element instanceof HTMLSelectElement) {
        element.selectedIndex = 0;
      }
    });

    // Execute the 'WHEN-NEW-FORM-INSTANCE' trigger logic
    this.executeWhenNewFormInstanceTrigger();
  }

  executeWhenNewFormInstanceTrigger(): void {
    // Logic for 'WHEN-NEW-FORM-INSTANCE' trigger
    this.initializeForm();
  }

  clearForm(): void {
    // Clear the form fields without committing any changes
    this.resetFormFields();
  }

  reinitializeForm(): void {
    // Reinitialize the form as if it were newly opened
    this.initializeForm();
  }
}
