import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-task-priority-view',
  templateUrl: './task-priority-view.component.html',
  styleUrls: ['./task-priority-view.component.less']
})
export class TaskPriorityViewComponent implements OnInit {

  @Input() priority: string;
  constructor() { }

  ngOnInit() {
  }

  displayText() {
    switch(this.priority) {
      case 'BLOCKER':         
        return 'Highest';
      case 'CRITICAL': 
        return 'High';
      case 'MAJOR':
        return 'Medium';
      case 'NORMAL':
        return 'Low';
      case 'MINOR': 
        return 'Lowset';
    }
  }

  cssClass() {
    switch(this.priority) {
      case 'BLOCKER':         
        return 'text-danger icon-long-arrow-up';
      case 'CRITICAL': 
        return 'text-danger icon-long-arrow-up';
      case 'MAJOR':
        return 'text-warning icon-long-arrow-up';
      case 'NORMAL':
        return 'text-success icon-long-arrow-down';
      case 'MINOR': 
        return 'text-success icon-long-arrow-down';
    }
  }

}
