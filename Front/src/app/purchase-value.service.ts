import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PurchaseValue } from './purchase-value';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PurchaseValueService {
  private basedURL = 'http://localhost:8080/api/purchase-value';
  constructor(private httpClient: HttpClient) { }
  consultarValores(): Observable<PurchaseValue> {
    return this.httpClient.get<PurchaseValue>(`${this.basedURL}/1`).pipe(
      tap((purchase: PurchaseValue) => {
        localStorage.setItem('pValues', JSON.stringify(purchase));
        console.log('El elemento es: ', purchase)
      })
    );
  }
  actualizarValores(purchase:PurchaseValue):Observable<any>{
    return this.httpClient.put(`${this.basedURL}/1`, purchase).pipe(
      tap(()=>{
        console.log(`Datos Actualizados: ${purchase}`)
      })
    )
  }
  borrarCache():void{
    localStorage.clear();
  }
}
