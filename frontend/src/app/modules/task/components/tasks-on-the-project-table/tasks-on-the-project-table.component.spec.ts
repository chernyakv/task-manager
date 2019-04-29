import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksOnTheProjectTableComponent } from './tasks-on-the-project-table.component';

describe('TasksOnTheProjectTableComponent', () => {
  let component: TasksOnTheProjectTableComponent;
  let fixture: ComponentFixture<TasksOnTheProjectTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasksOnTheProjectTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasksOnTheProjectTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
