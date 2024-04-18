import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../models/chat';
import { Message } from '../models/message';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChatService {


  baseUrl = "http://localhost:8095";
  private baseUrl2 = 'http://localhost:8095/chats';

  constructor(private httpClient: HttpClient) { }


  updateChat(message: Message, chatId: any): Observable<Object> {
    return this.httpClient.put(this.baseUrl + "/chats/message/" + `${chatId}`, message);
  }

  getChatById(chatId: any) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/" + chatId)
  }

  createChatRoom(chat: Chat): Observable<Object> {
    return this.httpClient.post(this.baseUrl + "/chats/add", chat);
  }

  getChatByFirstuserNameAndSeconduserName(firstuserName: String, seconduserName: String) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/getChatByFirstuserNameAndSeconduserName" + '?firstuserName=' + firstuserName + '&seconduserName=' + seconduserName)
  }

  getChatByFirstuserNameOrSeconduserName(userName: any) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/getChatByFirstuserNameOrSeconduserName/" + userName)
  }
  
  deleteChat(chatId: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl2}/delete/${chatId}`);
  }

}
