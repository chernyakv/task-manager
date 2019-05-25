import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskPriorityViewComponent } from './task-priority-view.component';

describe('TaskPriorityViewComponent', () => {
  let component: TaskPriorityViewComponent;
  let fixture: ComponentFixture<TaskPriorityViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskPriorityViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskPriorityViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
