import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISport } from 'app/shared/model/sport.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SportService } from './sport.service';
import { SportDeleteDialogComponent } from './sport-delete-dialog.component';

@Component({
  selector: 'jhi-sport',
  templateUrl: './sport.component.html',
})
export class SportComponent implements OnInit, OnDestroy {
  sports: ISport[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected sportService: SportService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.sports = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.sportService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ISport[]>) => this.paginateSports(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.sports = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSports();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISport): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSports(): void {
    this.eventSubscriber = this.eventManager.subscribe('sportListModification', () => this.reset());
  }

  delete(sport: ISport): void {
    const modalRef = this.modalService.open(SportDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sport = sport;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSports(data: ISport[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.sports.push(data[i]);
      }
    }
  }
}
