import { Injectable } from '@angular/core';
import { NgbDatepickerI18n, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Injectable()
export class NgbDatepickerI18nCustom extends NgbDatepickerI18n {
  // Ejemplo de nombres de meses en español
  private readonly MONTHS = [
    'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'
  ];

  // Ejemplo de nombres de días en español
  private readonly WEEKDAYS = ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'];

  getMonthShortName(month: number): string {
    return this.MONTHS[month - 1];
  }

  getMonthFullName(month: number): string {
    return this.MONTHS[month - 1];
  }

  getWeekdayShortName(weekday: number): string {
    return this.WEEKDAYS[weekday - 1];
  }

  getWeekdayFullName(weekday: number): string {
    return this.WEEKDAYS[weekday - 1];
  }

  getWeekdayLabel(_?: number): string {
    throw new Error('Not implemented');
  }

  getDayAriaLabel(date: NgbDateStruct): string {
    return `${date.day}-${date.month}-${date.year}`;
  }
}
