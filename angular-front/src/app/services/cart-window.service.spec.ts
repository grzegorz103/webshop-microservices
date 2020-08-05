import { TestBed } from '@angular/core/testing';

import { CartWindowService } from './cart-window.service';

describe('CartWindowService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CartWindowService = TestBed.get(CartWindowService);
    expect(service).toBeTruthy();
  });
});
