import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILeague } from 'app/shared/model/league.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { LeagueService } from './league.service';
import { LeagueDeleteDialogComponent } from './league-delete-dialog.component';

@Component({
  selector: 'jhi-league',
  templateUrl: './league.component.html',
})
export class LeagueComponent implements OnInit, OnDestroy {
  leagues: ILeague[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected leagueService: LeagueService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.leagues = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.leagueService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ILeague[]>) => this.paginateLeagues(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.leagues = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLeagues();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILeague): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLeagues(): void {
    this.eventSubscriber = this.eventManager.subscribe('leagueListModification', () => this.reset());
  }

  delete(league: ILeague): void {
    const modalRef = this.modalService.open(LeagueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.league = league;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateLeagues(data: ILeague[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.leagues.push(data[i]);
      }
    }
  }
}
