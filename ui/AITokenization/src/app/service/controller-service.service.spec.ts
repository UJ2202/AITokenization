import { TestBed } from '@angular/core/testing';

import { ControllerServiceService } from './controller-service.service';

describe('ControllerServiceService', () => {
  let service: ControllerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ControllerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
