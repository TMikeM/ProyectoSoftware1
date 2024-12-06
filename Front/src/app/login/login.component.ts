import { Component, HostListener } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  showBase: boolean = true;
  code: string = '';
  contrasena: string = '';
  user: Usuario | null = null; // Inicializamos la propiedad user

  constructor(
    private router: Router,
    private usuarioService: UsuarioService,
    private authService: AuthService,
  ) { }
  ngOnInit() {
    this.usuarioService.borrarCache();
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
    this.usuarioService.consultarUsuario(this.code, this.contrasena).subscribe(response => {
      console.log('Respuesta del backend:', response);
      if (response != null) {
        console.log('Usuario registrado:', response);
        // Guarda el usuario en el almacenamiento local
        localStorage.setItem('usuarioRegistrado', JSON.stringify(response));
        // Actualiza la propiedad user con el usuario registrado
        this.user = response;
        if (this.user.rol == 'Estudiante') {
          this.authService.login()
          this.router.navigate(['/lobby']);
        }
        else if ((this.user.rol == 'Admin')) {
          this.authService.login()
          this.router.navigate(['/admin']);
        }
        else {
          console.log('Tipo de rol no valido: ', this.user.rol)
        }
      } else {
        console.error('Error al autenticar usuario:');
      }
    });
  }
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  obtenerUsuarioDeLocalStorage(): any {
    const usuarioString = localStorage.getItem('usuarioRegistrado');
    if (usuarioString) {
      this.user = JSON.parse(usuarioString);
      console.log('Usuario cargado desde localStorage:', this.user);
      return this.user;
    } else {
      console.log('No se encontró usuario en localStorage');
      return null;
    }

  }
}


