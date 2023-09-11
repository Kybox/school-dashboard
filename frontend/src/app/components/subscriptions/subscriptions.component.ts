import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnDestroy, OnInit, Renderer2, ViewChild, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { CoreData } from 'src/app/core/model/core.data';
import { CoreService } from 'src/app/core/services/core.service';
import { Course } from 'src/app/shared/model/course';
import { CourseSub } from 'src/app/shared/model/course.sub';
import { Person } from 'src/app/shared/model/person';
import { ServiceAPI } from 'src/app/shared/services/api.service';

@Component({
  selector: 'app-subscription',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class subscriptionsComponent implements OnInit, OnDestroy {

  @ViewChild('dropdown')
  private dropdownRef!: ElementRef<HTMLDivElement>

  public subList: CourseSub[] = [];
  public courseList: Course[] = [];
  public courseSelected!: Course;
  public student!: Person;

  private dropdownState: boolean = false;
  private subAPI!: Subscription;
  private subCore!: Subscription;
  private apiService: ServiceAPI = inject(ServiceAPI);
  private coreService: CoreService = inject(CoreService);
  private renderer: Renderer2 = inject(Renderer2);

  constructor() { }

  public ngOnInit(): void {
    this.subCore = this.coreService.observable.subscribe((res: CoreData) => {
      switch (res.key) {
        case 'res-student':
          this.student = res.value;
          break;
      }
    });
    this.subAPI = this.apiService.getCourseList().subscribe((res: Course) => this.courseList.push(res));
    this.coreService.emit({ key: 'loader-state', value: false });
    this.coreService.emit({ key: 'req-student', value: null });
  }

  public clickDropdown(): void {
    this.dropdownState = !this.dropdownState;
    if (this.dropdownState) this.renderer.addClass(this.dropdownRef.nativeElement, 'is-active');
    else this.renderer.removeClass(this.dropdownRef.nativeElement, 'is-active');
  }

  public clickCourse(course: Course): void {
    this.courseSelected = course;
  }

  public subscribe(): void {
    const sub: CourseSub = { id: this.subList.length, course: this.courseSelected, person: this.student };
    this.subList.push(sub);
  }

  public ngOnDestroy(): void {
    this.subAPI.unsubscribe();
    this.subCore.unsubscribe();
  }
}
