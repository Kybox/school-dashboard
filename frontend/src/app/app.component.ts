import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnDestroy, OnInit, Renderer2, ViewChild, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subscription, delay } from 'rxjs';
import { NavigationComponent } from './components/navigation/navigation.component';
import { CoreData } from './core/model/core.data';
import { CoreService } from './core/services/core.service';
import { Person } from './shared/model/person';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterModule, NavigationComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  @ViewChild('contentRef')
  public contentRef!: ElementRef;

  public displayLoader: boolean = true;
  public student!: Person;

  private coreSubscription!: Subscription;
  private coreService: CoreService = inject(CoreService);
  private renderer: Renderer2 = inject(Renderer2)

  constructor() { }

  public ngOnInit(): void {
    this.coreSubscription = this.coreService.observable.pipe(delay(0)).subscribe((res: CoreData) => {
      switch (res.key) {
        case 'loader-state':
          this.displayLoader = res.value;
          this.contentState(!res.value);
          break;
        case 'student':
          this.student = res.value;
          break;
        case 'req-student':
          this.coreService.emit({ key: 'res-student', value: this.student });
          break;
      }
    });
  }

  private contentState(state: boolean): void {
    switch (state) {
      case true:
        this.renderer.addClass(this.contentRef?.nativeElement, 'display');
        break;
      case false:
        this.renderer.removeClass(this.contentRef?.nativeElement, 'display');
        break;
    }
  }

  public ngOnDestroy(): void {
    this.coreSubscription.unsubscribe();
  }
}
