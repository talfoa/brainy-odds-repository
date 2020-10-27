import { Moment } from 'moment';
import { IGameEvent } from 'app/shared/model/game-event.model';
import { IScoreBoard } from 'app/shared/model/score-board.model';
import { IOpportunity } from 'app/shared/model/opportunity.model';
import { ITeam } from 'app/shared/model/team.model';
import { ILeague } from 'app/shared/model/league.model';
import { ISport } from 'app/shared/model/sport.model';
import { IRegion } from 'app/shared/model/region.model';

export interface IMatch {
  id?: number;
  timeOfMessage?: Moment;
  matchId?: number;
  matchDate?: Moment;
  cornerSending?: boolean;
  gameEvents?: IGameEvent[];
  scoreBoards?: IScoreBoard[];
  opportunities?: IOpportunity[];
  homeTeam?: ITeam;
  awayTeam?: ITeam;
  league?: ILeague;
  sport?: ISport;
  region?: IRegion;
}

export class Match implements IMatch {
  constructor(
    public id?: number,
    public timeOfMessage?: Moment,
    public matchId?: number,
    public matchDate?: Moment,
    public cornerSending?: boolean,
    public gameEvents?: IGameEvent[],
    public scoreBoards?: IScoreBoard[],
    public opportunities?: IOpportunity[],
    public homeTeam?: ITeam,
    public awayTeam?: ITeam,
    public league?: ILeague,
    public sport?: ISport,
    public region?: IRegion
  ) {
    this.cornerSending = this.cornerSending || false;
  }
}
