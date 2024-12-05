import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private basedURL ='http://localhost:8080';
  constructor(private httpClient: HttpClient) { }
}
