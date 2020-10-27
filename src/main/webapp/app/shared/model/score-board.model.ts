import { Moment } from 'moment';
import { IMatch } from 'app/shared/model/match.model';

export interface IScoreBoard {
  id?: number;
  timeOfMessage?: Moment;
  gamePart?: string;
  score?: string;
  scorePart?: string;
  hidden?: boolean;
  hideTimer?: boolean;
  remainingTimeInPeriod?: number;
  relativePlayerCount?: number;
  match?: IMatch;
}

export class ScoreBoard implements IScoreBoard {
  constructor(
    public id?: number,
    public timeOfMessage?: Moment,
    public gamePart?: string,
    public score?: string,
    public scorePart?: string,
    public hidden?: boolean,
    public hideTimer?: boolean,
    public remainingTimeInPeriod?: number,
    public relativePlayerCount?: number,
    public match?: IMatch
  ) {
    this.hidden = this.hidden || false;
    this.hideTimer = this.hideTimer || false;
  }
}
