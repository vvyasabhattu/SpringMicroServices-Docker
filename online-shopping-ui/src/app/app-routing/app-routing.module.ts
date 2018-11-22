import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { LoginComponent } from '../login/login.component';

const routesArray: Routes=[
  {path:'home',component:HomeComponent},
  {path:'login',component:LoginComponent}
]
@NgModule({
  imports: [RouterModule.forRoot(routesArray)],
  exports:[RouterModule]
})
export class AppRoutingModule { }
