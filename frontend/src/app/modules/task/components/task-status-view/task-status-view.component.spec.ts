import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskStatusViewComponent } from './task-status-view.component';

describe('TaskStatusViewComponent', () => {
  let component: TaskStatusViewComponent;
  let fixture: ComponentFixture<TaskStatusViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskStatusViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskStatusViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
