import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatSelectionComponent } from './seat-selection.component';

describe('SeatSelection', () => {
  let component: SeatSelectionComponent;
  let fixture: ComponentFixture<SeatSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeatSelectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeatSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
