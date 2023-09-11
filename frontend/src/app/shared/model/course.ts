import { Person } from "./person";
import { Subject } from "./subject";

export interface Course {
    id: number;
    title: string;
    description: string;
    teacher: Person;
    subject: Subject;
}