import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOpportunity } from 'app/shared/model/opportunity.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OpportunityService } from './opportunity.service';
import { OpportunityDeleteDialogComponent } from './opportunity-delete-dialog.component';

@Component({
  selector: 'jhi-opportunity',
  templateUrl: './opportunity.component.html',
})
export class OpportunityComponent implements OnInit, OnDestroy {
  opportunities: IOpportunity[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected opportunityService: OpportunityService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.opportunities = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.opportunityService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IOpportunity[]>) => this.paginateOpportunities(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.opportunities = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOpportunities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOpportunity): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOpportunities(): void {
    this.eventSubscriber = this.eventManager.subscribe('opportunityListModification', () => this.reset());
  }

  delete(opportunity: IOpportunity): void {
    const modalRef = this.modalService.open(OpportunityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.opportunity = opportunity;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateOpportunities(data: IOpportunity[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.opportunities.push(data[i]);
      }
    }
  }
}
