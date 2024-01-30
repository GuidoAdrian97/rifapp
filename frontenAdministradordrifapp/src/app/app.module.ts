// Angular Import
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// project import
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './theme/layout/admin/admin.component';
import { GuestComponent } from './theme/layout/guest/guest.component';
import { ConfigurationComponent } from './theme/layout/admin/configuration/configuration.component';
import { NavBarComponent } from './theme/layout/admin/nav-bar/nav-bar.component';
import { NavigationComponent } from './theme/layout/admin/navigation/navigation.component';
import { NavLeftComponent } from './theme/layout/admin/nav-bar/nav-left/nav-left.component';
import { NavRightComponent } from './theme/layout/admin/nav-bar/nav-right/nav-right.component';
import { NavSearchComponent } from './theme/layout/admin/nav-bar/nav-left/nav-search/nav-search.component';
import { ChatMsgComponent } from './theme/layout/admin/nav-bar/nav-right/chat-msg/chat-msg.component';
import { ChatUserListComponent } from './theme/layout/admin/nav-bar/nav-right/chat-user-list/chat-user-list.component';
import { FriendComponent } from './theme/layout/admin/nav-bar/nav-right/chat-user-list/friend/friend.component';
import { NavContentComponent } from './theme/layout/admin/navigation/nav-content/nav-content.component';
import { NavCollapseComponent } from './theme/layout/admin/navigation/nav-content/nav-collapse/nav-collapse.component';
import { NavGroupComponent } from './theme/layout/admin/navigation/nav-content/nav-group/nav-group.component';
import { NavItemComponent } from './theme/layout/admin/navigation/nav-content/nav-item/nav-item.component';
import { SharedModule } from './theme/shared/shared.module';
import { OpcConsignacionComponent } from './demo/consignaciones/opc-consignacion/opc-consignacion.component';
import { ConsignacionAprobadaComponent } from './demo/consignaciones/consignacion-aprobada/consignacion-aprobada.component';
import { ConsignacionRechazadaComponent } from './demo/consignaciones/consignacion-rechazada/consignacion-rechazada.component';
import { ConsignacionEsperaComponent } from './demo/consignaciones/consignacion-espera/consignacion-espera.component';
import { ConsignacionTipoComponent } from './demo/consignaciones/consignacion-tipo/consignacion-tipo.component';
import {MatSliderModule} from '@angular/material/slider';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    GuestComponent,
    ConfigurationComponent,
    NavBarComponent,
    NavigationComponent,
    NavLeftComponent,
    NavRightComponent,
    NavSearchComponent,
    ChatMsgComponent,
    ChatUserListComponent,
    FriendComponent,
    NavContentComponent,
    NavItemComponent,
    NavCollapseComponent,
    NavGroupComponent,
    OpcConsignacionComponent,
    ConsignacionAprobadaComponent,
    ConsignacionRechazadaComponent,
    ConsignacionTipoComponent,
    ConsignacionEsperaComponent,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  imports: [BrowserModule, AppRoutingModule, SharedModule, FormsModule, ReactiveFormsModule, BrowserAnimationsModule,MatSliderModule,
    MatFormFieldModule, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
