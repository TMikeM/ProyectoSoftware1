import { Component } from '@angular/core';
import { PurchaseValue } from '../purchase-value';
import { PurchaseValueService } from '../purchase-value.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-administrative',
  templateUrl: './administrative.component.html',
  styleUrl: './administrative.component.css'
})
export class AdministrativeComponent {
  purchase: PurchaseValue | null = null;
  cantidadDia: number = 0;
  cantidadCena: number = 0;
  cantidadMes: number = 0;
  constructor(
    private purchaseValueService: PurchaseValueService,
    private router: Router,
    private authService: AuthService,
  ) { }
  ngOnInit() {
    this.obtenerDefaultValuesLocalStorage();
  }
  obtenerDefaultValuesLocalStorage(): void {
    this.purchaseValueService.consultarValores().subscribe(response => {
      console.log('Respuesta del backend:', response);
      if (response != null) {
        console.log('Datos por defecto:', response);
        // Guarda el usuario en el almacenamiento local
        localStorage.setItem('purchaseValue', JSON.stringify(response));
        this.purchase = response;
        if (this.purchase) {
          this.cantidadDia = this.purchase.cantidadDiaria || 0;
          this.cantidadCena = this.purchase.cantidadCena || 0;
          this.cantidadMes = this.purchase.cantidadMensual || 0;
        }
      } else {
        console.error('Error al cargar datos: ', response);
      }
    });
  }
  actualizar(): void {
    if (this.purchase) {
      this.purchase.cantidadDiaria = this.cantidadDia;
      this.purchase.cantidadCena = this.cantidadCena;
      this.purchase.cantidadMensual = this.cantidadMes;
      console.log('Datos antes de actualizar',this.purchase)
      this.purchaseValueService.actualizarValores(this.purchase).subscribe(
        Response =>{
          console.log(`Datos Actualizados: ${this.purchase}`)
        },
        error => {
          console.error(`Error al actualizar datos: ${this.purchase}`);
        }
      )
    }
    else {
      console.log('El objeto es nulo')
    }
  }
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}

