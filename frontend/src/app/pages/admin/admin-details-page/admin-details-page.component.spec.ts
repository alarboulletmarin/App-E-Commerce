import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDetailsPageComponent } from './admin-details-page.component';

describe('AdminDetailsPageComponent', () => {
  let component: AdminDetailsPageComponent;
  let fixture: ComponentFixture<AdminDetailsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminDetailsPageComponent]
    });
    fixture = TestBed.createComponent(AdminDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
