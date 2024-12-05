import { TestBed } from '@angular/core/testing';

import { CodeQrService } from './code-qr.service';

describe('CodeQrService', () => {
  let service: CodeQrService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CodeQrService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
