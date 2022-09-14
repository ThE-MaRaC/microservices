import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { User.ListComponent } from './user.list.component';

describe('User.ListComponent', () => {
  let component: User.ListComponent;
  let fixture: ComponentFixture<User.ListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ User.ListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(User.ListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
