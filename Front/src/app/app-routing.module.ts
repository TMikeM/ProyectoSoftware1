import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LobbyComponent } from './lobby/lobby.component';

const routes: Routes = [
  { path:'', redirectTo:'/login', pathMatch:'full'},
  { path: 'login', component: LoginComponent },
  { path: 'lobby', component: LobbyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
