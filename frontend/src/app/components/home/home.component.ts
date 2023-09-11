import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { CoreService } from 'src/app/core/services/core.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export default class HomeComponent implements OnInit {

  private coreService: CoreService = inject(CoreService);

  constructor() { }

  public ngOnInit(): void {
    this.coreService.emit({ key: 'loader-state', value: false });
  }
}
