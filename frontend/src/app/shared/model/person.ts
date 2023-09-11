import { Role } from "./role";
import { Subject } from "./subject";

export interface Person {
    id: number;
    name: string;
    avatar: string;
    role: Role;
    subject?: Subject;
}