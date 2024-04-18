import { Hashtag } from "./hashtag";
import { Reponse } from "./reponse";
import { Technologie } from "./technologie";
import { User } from "./user";
import { Vote } from "./vote";

export class Question {
    id!:String;
    contenue!:String;
    tech!:Technologie[];
    hashtag!:Hashtag[];
    reponses:Reponse[]=[];
    votes!:Vote[];
    user!:User;


}