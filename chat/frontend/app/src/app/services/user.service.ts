import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

<<<<<<< HEAD
<<<<<<< HEAD
  baseUrl = "http://localhost:8095";
=======
  baseUrl = "http://localhost:8090";
>>>>>>> 9c94761f2eb0aa9a853227c20cfce771558f1a98
=======
  baseUrl = "http://localhost:8095";
>>>>>>> f7f3f0c1ebcf0b1666c8743ca514667af63f75f0

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<User[]>(this.baseUrl + "/user/getall")
  }

  adduser(user: User): Observable<Object> {
    return this.httpClient.post(this.baseUrl + "/user/add", user);
  }

  getUserByUsername(username: any) {
    return this.httpClient.get<User>(this.baseUrl + "/user/getbyusername/" + username)
  }

}
