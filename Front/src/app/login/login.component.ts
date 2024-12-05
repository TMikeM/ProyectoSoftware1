import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  showBase: boolean = true;

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
}

