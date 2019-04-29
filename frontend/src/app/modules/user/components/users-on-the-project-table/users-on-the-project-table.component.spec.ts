import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersOnTheProjectTableComponent } from './users-on-the-project-table.component';

describe('UsersOnTheProjectTableComponent', () => {
  let component: UsersOnTheProjectTableComponent;
  let fixture: ComponentFixture<UsersOnTheProjectTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersOnTheProjectTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersOnTheProjectTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
