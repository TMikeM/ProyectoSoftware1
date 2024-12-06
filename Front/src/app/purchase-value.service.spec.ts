import { TestBed } from '@angular/core/testing';

import { PurchaseValueService } from './purchase-value.service';

describe('PurchaseValueService', () => {
  let service: PurchaseValueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PurchaseValueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
