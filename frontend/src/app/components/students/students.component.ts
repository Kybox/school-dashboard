import { CommonModule } from '@angular/common';
import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, inject } from '@angular/core';
import { Observable, Subscription, interval, retry, take, timeout } from 'rxjs';
import { CoreService } from 'src/app/core/services/core.service';
import { Person } from 'src/app/shared/model/person';
import { ServiceAPI } from 'src/app/shared/services/api.service';

@Component({
  selector: 'app-students',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements AfterViewInit, OnDestroy {

  public personList: Person[] = [];

  private sub!: Subscription;
  private apiService: ServiceAPI = inject(ServiceAPI);
  private coreService: CoreService = inject(CoreService);

  constructor() { }

  public ngAfterViewInit(): void {
    this.sub = this.apiService.getStudents().subscribe((res: Person) => this.personList.push(res));
    this.coreService.emit({ key: 'loader-state', value: false });
  }

  public click(index: number): void {
    this.coreService.emit({ key: 'student', value: this.personList[index] });
  }

  public ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
