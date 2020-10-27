import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IScoreBoard } from 'app/shared/model/score-board.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ScoreBoardService } from './score-board.service';
import { ScoreBoardDeleteDialogComponent } from './score-board-delete-dialog.component';

@Component({
  selector: 'jhi-score-board',
  templateUrl: './score-board.component.html',
})
export class ScoreBoardComponent implements OnInit, OnDestroy {
  scoreBoards: IScoreBoard[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected scoreBoardService: ScoreBoardService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.scoreBoards = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.scoreBoardService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IScoreBoard[]>) => this.paginateScoreBoards(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.scoreBoards = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInScoreBoards();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IScoreBoard): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInScoreBoards(): void {
    this.eventSubscriber = this.eventManager.subscribe('scoreBoardListModification', () => this.reset());
  }

  delete(scoreBoard: IScoreBoard): void {
    const modalRef = this.modalService.open(ScoreBoardDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.scoreBoard = scoreBoard;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateScoreBoards(data: IScoreBoard[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.scoreBoards.push(data[i]);
      }
    }
  }
}
