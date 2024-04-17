import { Question } from "./question";
import { User } from "./user";

export class Vote {
    id!:String;
    vote!:String;
    question!:Question;
    user!:User;


}