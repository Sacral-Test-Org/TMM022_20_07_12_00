import { Injectable } from '@angular/core';
import { ValidationResult } from '../models/validation-result.model';
import { LineController } from '../controllers/line.controller';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {
  constructor(private lineController: LineController) {}

  validatePartDescription(partDescription: string): ValidationResult {
    const result = new ValidationResult();
    if (partDescription) {
      partDescription = '';
      result.message = 'Part Description cleared.';
    }
    result.isValid = true;
    return result;
  }

  validateFields(fields: any): ValidationResult {
    const result = new ValidationResult();
    if (!fields.unitId || !fields.unitName) {
      result.isValid = false;
      result.message = 'Unit ID and Unit Name should not be null';
      result.focusField = 'unitId';
      return result;
    }
    if (!fields.groupId || !fields.groupName) {
      result.isValid = false;
      result.message = 'Group ID and Group Name should not be null';
      result.focusField = 'groupId';
      return result;
    }
    if (!fields.lineId || !fields.lineDescription) {
      result.isValid = false;
      result.message = 'Line ID and Line Name should not be null';
      result.focusField = 'lineId';
      return result;
    }
    if (fields.globalParameter === 0) {
      if (!fields.partNo || !fields.partDescription) {
        result.isValid = false;
        result.message = 'Part No and Part Description should not be null';
        result.focusField = 'partNo';
        return result;
      }
    } else if (fields.globalParameter === 1) {
      if (!fields.partId) {
        result.isValid = false;
        result.message = 'Kindly Choose data from LOV before changing Description';
        result.focusField = 'partNo';
        return result;
      }
      if (!fields.partNo || !fields.partDescription) {
        result.isValid = false;
        result.message = 'Part No and Part Description should not be null';
        result.focusField = 'partNo';
        return result;
      }
    }
    result.isValid = true;
    return result;
  }

  validateUnitID(unitID: string, unitName: string, globalParameter: number): ValidationResult {
    const result = new ValidationResult();
    if (globalParameter === 0) {
      // Validate against MES Unit Master table
      // Assuming a method exists to check the database
      const isValid = this.checkMesUnitMaster(unitID, unitName);
      if (!isValid) {
        result.isValid = false;
        result.message = 'Invalid Unit ID or Unit Name';
        return result;
      }
    } else if (globalParameter === 1) {
      // Validate against both MES Unit Master and HPM Part Master tables
      // Assuming a method exists to check the database
      const isValid = this.checkMesAndHpmPartMaster(unitID, unitName);
      if (!isValid) {
        result.isValid = false;
        result.message = 'Invalid Unit ID or Unit Name';
        return result;
      }
    }
    result.isValid = true;
    return result;
  }

  validateUnitName(unitName: string): ValidationResult {
    const result = new ValidationResult();
    // Validate against MES Unit Master table
    // Assuming a method exists to check the database
    const isValid = this.checkMesUnitMasterByName(unitName);
    if (!isValid) {
      result.isValid = false;
      result.message = 'Invalid Unit Name';
      return result;
    }
    result.isValid = true;
    return result;
  }

  validatePartNumber(partNumber: string, unitId: string, globalParameter: number): ValidationResult {
    const result = new ValidationResult();
    if (globalParameter === 0) {
      // Validate against part master table for given unit ID and active status
      // Assuming a method exists to check the database
      const isValid = this.checkPartMaster(unitId, partNumber);
      if (!isValid) {
        result.isValid = false;
        result.message = 'Invalid Part Number';
        return result;
      }
    } else if (globalParameter === 1) {
      // Validate against part master table for given unit ID, group ID, line ID, and part ID
      // Assuming a method exists to check the database
      const isValid = this.checkPartMasterWithDetails(unitId, partNumber);
      if (!isValid) {
        result.isValid = false;
        result.message = 'Invalid Part Number';
        return result;
      }
    }
    result.isValid = true;
    return result;
  }

  validateLineIdAndDescription(globalParameter: number, lineId: string, lineDescription: string): ValidationResult {
    const result = new ValidationResult();
    const validationResponse = this.lineController.validateLineIdAndDescription(globalParameter, lineId, lineDescription);
    if (!validationResponse.isValid) {
      result.isValid = false;
      result.message = validationResponse.message;
      return result;
    }
    result.isValid = true;
    return result;
  }

  validateRequiredFields(unitId: string, unitName: string, groupId: string, groupName: string, lineId: string, lineDescription: string): ValidationResult {
    const result = new ValidationResult();
    if (!unitId || !unitName || !groupId || !groupName || !lineId || !lineDescription) {
      result.isValid = false;
      result.message = 'All fields are required';
      return result;
    }
    result.isValid = true;
    return result;
  }

  validatePartForm(formData: any): ValidationResult {
    const result = new ValidationResult();
    if (!formData.partId || !formData.unitName || !formData.groupName || !formData.lineDescription || !formData.partNumber || !formData.partDescription || !formData.partStatus) {
      result.isValid = false;
      result.message = 'All fields are required';
      return result;
    }
    result.isValid = true;
    return result;
  }

  validateGroupIdAndName(groupId: string, groupName: string, unitId: string): ValidationResult {
    const result = new ValidationResult();
    // Check if Group ID and Group Name exist in the database with the correct unit ID and status
    // Assuming a method exists to check the database
    const isValid = this.checkGroupMaster(groupId, groupName, unitId);
    if (!isValid) {
      result.isValid = false;
      result.message = 'Invalid Group ID or Group Name';
      return result;
    }
    result.isValid = true;
    return result;
  }

  validateUnitIdAndName(unitId: string, unitName: string): ValidationResult {
    const result = new ValidationResult();
    if (!unitId || !unitName) {
      result.isValid = false;
      result.message = 'Unit ID and Unit Name should not be null';
      return result;
    }
    result.isValid = true;
    return result;
  }

  // Placeholder methods for database checks
  private checkMesUnitMaster(unitID: string, unitName: string): boolean {
    // Implement database check logic here
    return true;
  }

  private checkMesAndHpmPartMaster(unitID: string, unitName: string): boolean {
    // Implement database check logic here
    return true;
  }

  private checkMesUnitMasterByName(unitName: string): boolean {
    // Implement database check logic here
    return true;
  }

  private checkPartMaster(unitId: string, partNumber: string): boolean {
    // Implement database check logic here
    return true;
  }

  private checkPartMasterWithDetails(unitId: string, partNumber: string): boolean {
    // Implement database check logic here
    return true;
  }

  private checkGroupMaster(groupId: string, groupName: string, unitId: string): boolean {
    // Implement database check logic here
    return true;
  }
}
