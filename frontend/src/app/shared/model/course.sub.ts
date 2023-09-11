import { Course } from "./course";
import { Person } from "./person";

export interface CourseSub {
    id: number;
    course: Course;
    person: Person;
}