import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-list-user',
  standalone: true,
  imports: [CommonModule, SharedModule],
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss']
})
export class ListUserComponent {

}
