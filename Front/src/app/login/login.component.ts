import { Component, HostListener } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  showBase: boolean = true;
  dni: string='45673921';
  contrasena: string='securepass321';
  constructor(private router: Router, private usuarioService: UsuarioService, private location: Location) {
  }
  toggleDisplay(): void {
    this.showBase = !this.showBase;
    console.log('Button clicked, showBase:', this.showBase); // Para depuración
  }

  // Método para manejar clics en cualquier parte del documento
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event): void {
    const target = event.target as HTMLElement;
    const rectangle = document.getElementById('rectangle');
    if (rectangle && !rectangle.contains(target)) {
      if (!this.showBase) {
        this.showBase = true;
        console.log('Clicked outside, showBase:', this.showBase); // Para depuración
      }
    }
  }
  consulta(): void {
    this.usuarioService.consultarUsuario(this.dni, this.contrasena).subscribe(response => {
      console.log('Usuario registrado:', this.dni,this.contrasena);
      if (response != null) {
        this.router.navigate(['/lobby']);
      } else {
        console.error('Error al autenticar usuario:');
      }
    });
  }

}

