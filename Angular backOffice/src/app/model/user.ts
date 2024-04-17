import { Question } from "./question";
import { Reponse } from "./reponse";
import { Vote } from "./vote";

export class User{
    id!:String;
    firstName!:String;
    votes!:Vote[];
    questions!:Question[];
    reponses! :Reponse[];
}