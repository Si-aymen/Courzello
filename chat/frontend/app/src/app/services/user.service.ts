import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  baseUrl = "http://localhost:8095";

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<User[]>(this.baseUrl + "/user/getall")
  }

  adduser(user: User): Observable<Object> {
    return this.httpClient.post(this.baseUrl + "/user/add", user);
  }

  getUserByuserName(userName: any) {
    return this.httpClient.get<User>(this.baseUrl + "/user/getbyuserName/" + userName)
  }

}
