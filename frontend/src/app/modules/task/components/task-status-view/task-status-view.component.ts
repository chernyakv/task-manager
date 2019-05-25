import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-task-status-view',
  templateUrl: './task-status-view.component.html',
  styleUrls: ['./task-status-view.component.less']
})
export class TaskStatusViewComponent implements OnInit {

  @Input() status: string;
  constructor() { }

  ngOnInit() {
    
  }

  displayText() {
    switch(this.status) {
      case 'OPEN':         
        return 'Open';
      case 'IN_PROGRESS': 
        return 'In progress';
      case 'READY_FOR_TEST':
        return 'Ready for tes';
      case 'CLOSED':
        return 'Closed';
    }
  }

  cssClass() {
    switch(this.status) {
      case 'OPEN':
        return 'badge-success';
      case 'IN_PROGRESS':
        return 'badge-primary';
      case 'READY_FOR_TEST':
        return 'badge-warning';
      case 'CLOSED':
        return 'badge-danger';
    }
  }

}
