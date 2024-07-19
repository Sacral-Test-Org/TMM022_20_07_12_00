import { Component } from '@angular/core';

@Component({
  selector: 'app-exit-confirmation',
  templateUrl: './exit-confirmation.component.html',
  styleUrls: ['./exit-confirmation.component.css']
})
export class ExitConfirmationComponent {

  onYesClick(): void {
    // Logic to close the application without saving unsaved changes
    window.close();
  }

  onNoClick(): void {
    // Logic to close the confirmation dialog and remain in the application
    const confirmationDialog = document.getElementById('confirmationDialog');
    if (confirmationDialog) {
      confirmationDialog.style.display = 'none';
    }
  }
}
