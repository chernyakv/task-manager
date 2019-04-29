import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { TaskModule } from '../task/task.module';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserService } from 'src/app/_services/user.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { AdminComponent } from './admin/admin.component';
import { HeaderComponent } from './header/header.component';
import { UserModule } from '../user/user.module';
import { FooterComponent } from './footer/footer.component';
import { ProjectModule } from '../project/project.module';
import { TabsModule } from 'ngx-bootstrap';

@NgModule({
  declarations: [
    NotFoundComponent,
    HomeComponent,
    LoginComponent,
    AdminComponent,
    HeaderComponent,
    FooterComponent],
  imports: [    
    CommonModule,
    TaskModule,
    ProjectModule,
    ReactiveFormsModule,
    UserModule,
    TabsModule.forRoot()
  ],  
  exports:[HeaderComponent, LoginComponent, HomeComponent, FooterComponent],
  providers: [UserService, AuthenticationService]
  
})
export class LayoutModule { }
