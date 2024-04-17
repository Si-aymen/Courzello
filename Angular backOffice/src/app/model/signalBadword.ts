import { Question } from "./question";
import { User } from "./user";

export class SignalBadword{
    id!:String;
    badword!:String;
    question!:Question;
    user!:User;
}