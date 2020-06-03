import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingVersionDetailsComponent } from './training-version-details.component';

describe('TrainingVersionDetailsComponent', () => {
  let component: TrainingVersionDetailsComponent;
  let fixture: ComponentFixture<TrainingVersionDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingVersionDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingVersionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
