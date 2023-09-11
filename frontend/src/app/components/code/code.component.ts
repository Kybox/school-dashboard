import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreService } from 'src/app/core/services/core.service';

@Component({
  selector: 'app-code',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './code.component.html',
  styleUrls: ['./code.component.css']
})
export class CodeComponent implements OnInit {

  private coreService: CoreService = inject(CoreService);

  constructor() { }

  public ngOnInit(): void {
    this.coreService.emit({ key: 'loader-state', value: false });
  }
}
