import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ActivatedRoute, UrlSegment } from '@angular/router';
import { Subscription } from 'rxjs';
import { CoreService } from 'src/app/core/services/core.service';
import { Course } from 'src/app/shared/model/course';
import { ServiceAPI } from 'src/app/shared/services/api.service';

@Component({
  selector: 'app-students',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit, OnDestroy {

  public courseList: Course[] = [];
  public courseSelected!: Course;

  private sub!: Subscription;
  private apiService: ServiceAPI = inject(ServiceAPI);
  private coreService: CoreService = inject(CoreService);
  private activatedRoute: ActivatedRoute = inject(ActivatedRoute);

  constructor() { }

  public ngOnInit(): void {
    console.log(this.activatedRoute.snapshot.url);
    const teacherSegment: UrlSegment = this.activatedRoute.snapshot.url[1];
    if (teacherSegment?.path === 'teacher') {
      const teacherId: UrlSegment = this.activatedRoute.snapshot.url[2];
      this.loadCourseListFromTeacher(parseInt(teacherId.path))
    } else this.loadCourseList();
    this.coreService.emit({ key: 'loader-state', value: false });
  }

  public loadCourseList(): void {
    this.sub = this.apiService.getCourseList().subscribe((res: Course) => this.courseList.push(res));
  }

  public loadCourseListFromTeacher(id: number): void {
    this.sub = this.apiService.getCourseListFromTeacher(id).subscribe((res: Course) => this.courseList.push(res));
  }

  public click(course: Course): void {
    this.courseSelected = course;
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }

  public ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
