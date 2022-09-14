import { TestBed } from '@angular/core/testing';

import { User.ServiceService } from './user.service.service';

describe('User.ServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: User.ServiceService = TestBed.get(User.ServiceService);
    expect(service).toBeTruthy();
  });
});
