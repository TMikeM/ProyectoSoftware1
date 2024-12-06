import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Buy } from './buy';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyService {

  private basedURL ='http://localhost:8080/api/buy';
  constructor(private httpClient: HttpClient) {}

  registrarCompra(compra:Buy): Observable<any> {
    return this.httpClient.post(`${this.basedURL}`, compra);
  }
}
