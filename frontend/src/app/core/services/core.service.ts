import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { CoreData } from "../model/core.data";

@Injectable({
    providedIn: 'root'
})
export class CoreService {

    private subject: Subject<CoreData> = new Subject<CoreData>();

    public observable: Observable<CoreData> = this.subject.asObservable();

    constructor() { }

    public emit(change: CoreData) {
        this.subject.next(change);
    }
}