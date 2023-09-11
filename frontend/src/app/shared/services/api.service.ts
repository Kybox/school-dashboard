import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable, NgZone, inject } from "@angular/core";
import { Observable, Observer } from "rxjs";
import { Course } from "../model/course";
import { CourseSub } from "../model/course.sub";
import { Person } from "../model/person";

@Injectable({
    providedIn: 'root'
})
export class ServiceAPI {

    private rootUrl!: string;
    private httpOptions;

    private ngZone: NgZone = inject(NgZone);

    constructor(private http: HttpClient) {
        this.rootUrl = 'http://localhost:8080/';
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/x-ndjson'
            })
        };
    }

    public getTeachers(): Observable<Person> {
        return this.initRequest(this.rootUrl + 'teachers');
    }

    public getStudents(): Observable<Person> {
        return this.initRequest(this.rootUrl + 'students');
    };

    public getCourseList(): Observable<Course> {
        return this.initRequest(this.rootUrl + 'courses');
    };

    public getCourseListFromTeacher(id: number): Observable<Course> {
        return this.initRequest(this.rootUrl + 'courses/teacher/' + id);
    };

    public getSubscriptionList(): Observable<CourseSub> {
        return this.initRequest(this.rootUrl + 'subscriptions');
    };

    public getSubscriptionListByStudent(id: number): Observable<CourseSub> {
        return this.initRequest(this.rootUrl + '/subscriptions/student/' + id);
    };

    private initRequest(url: string): Observable<any> {
        return new Observable<Person>((subscriber: Observer<any>) => {
            const source: EventSource = new EventSource(url);
            source.onmessage = (evt: MessageEvent) => this.ngZone.run(() => subscriber.next(JSON.parse(evt.data)));
            source.onopen = (evt: Event) => console.log('[API SERVICE] - STREAM OPENED ');
            source.onerror = (evt) => {
                console.log('[API SERVICE] - STREAM CLOSED');
                source.close();
            };
        });
    }
}