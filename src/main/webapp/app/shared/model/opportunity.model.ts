import { Moment } from 'moment';
import { IMatch } from 'app/shared/model/match.model';

export interface IOpportunity {
  id?: number;
  timeOfMessage?: Moment;
  opportunityId?: number;
  type?: string;
  handicap?: string;
  line?: string;
  sequence?: string;
  tradingStatus?: number;
  actualTradingTime?: Moment;
  note?: string;
  betStop?: number;
  results?: string;
  match?: IMatch;
}

export class Opportunity implements IOpportunity {
  constructor(
    public id?: number,
    public timeOfMessage?: Moment,
    public opportunityId?: number,
    public type?: string,
    public handicap?: string,
    public line?: string,
    public sequence?: string,
    public tradingStatus?: number,
    public actualTradingTime?: Moment,
    public note?: string,
    public betStop?: number,
    public results?: string,
    public match?: IMatch
  ) {}
}