import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { CoreService } from 'src/app/core/services/core.service';
import { Course } from 'src/app/shared/model/course';
import { Person } from 'src/app/shared/model/person';
import { ServiceAPI } from 'src/app/shared/services/api.service';

@Component({
  selector: 'app-teachers',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit, OnDestroy {

  public personList: Person[] = [];

  private sub!: Subscription;
  private apiService: ServiceAPI = inject(ServiceAPI);
  private coreService: CoreService = inject(CoreService);

  constructor() { }

  public ngOnInit(): void {
    this.sub = this.apiService.getTeachers().subscribe((res: Person) => this.personList.push(res));
    this.coreService.emit({ key: 'loader-state', value: false });
  }

  public ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
