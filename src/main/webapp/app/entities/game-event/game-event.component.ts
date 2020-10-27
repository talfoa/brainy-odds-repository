import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGameEvent } from 'app/shared/model/game-event.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GameEventService } from './game-event.service';
import { GameEventDeleteDialogComponent } from './game-event-delete-dialog.component';

@Component({
  selector: 'jhi-game-event',
  templateUrl: './game-event.component.html',
})
export class GameEventComponent implements OnInit, OnDestroy {
  gameEvents: IGameEvent[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected gameEventService: GameEventService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.gameEvents = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.gameEventService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IGameEvent[]>) => this.paginateGameEvents(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.gameEvents = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGameEvents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGameEvent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGameEvents(): void {
    this.eventSubscriber = this.eventManager.subscribe('gameEventListModification', () => this.reset());
  }

  delete(gameEvent: IGameEvent): void {
    const modalRef = this.modalService.open(GameEventDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gameEvent = gameEvent;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateGameEvents(data: IGameEvent[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.gameEvents.push(data[i]);
      }
    }
  }
}
