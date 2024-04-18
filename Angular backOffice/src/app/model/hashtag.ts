import { Question } from "./question";
import { Technologie } from "./technologie";

export class Hashtag{
    id!:String;
    technologie!:Technologie;
    questions !:Question[];
}