import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Event as NavigationEvent, Router, RouterModule } from '@angular/router';
import { CoreService } from 'src/app/core/services/core.service';

@Component({
  selector: 'navigation',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  private router: Router = inject(Router);
  private coreService: CoreService = inject(CoreService);

  constructor() {
    this.router.events.subscribe((evt: NavigationEvent) => {
      this.coreService.emit({ key: 'loader-state', value: true });
    });
  }
}
