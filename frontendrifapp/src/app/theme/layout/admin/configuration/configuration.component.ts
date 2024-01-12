// Angular Import
import { Component, inject, TemplateRef, ViewEncapsulation } from '@angular/core';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
 
@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent {

 modalRef?: BsModalRef;
  constructor(private modalService: BsModalService) {}
 
  openModalWithClass(template: TemplateRef<void>) {
    this.modalRef = this.modalService.show(template, Object.assign({}, { class: 'gray modal-lg' }));
  }

 
}
