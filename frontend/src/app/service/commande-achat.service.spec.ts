import { TestBed } from '@angular/core/testing';

import { CommandeAchatService } from './commande-achat.service';

describe('CommandeAchatService', () => {
  let service: CommandeAchatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommandeAchatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
