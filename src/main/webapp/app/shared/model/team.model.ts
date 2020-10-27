import { IPlayer } from 'app/shared/model/player.model';
import { ILeague } from 'app/shared/model/league.model';
import { ISport } from 'app/shared/model/sport.model';

export interface ITeam {
  id?: number;
  name?: string;
  color?: string;
  flag?: string;
  players?: IPlayer[];
  leagues?: ILeague[];
  sport?: ISport;
}

export class Team implements ITeam {
  constructor(
    public id?: number,
    public name?: string,
    public color?: string,
    public flag?: string,
    public players?: IPlayer[],
    public leagues?: ILeague[],
    public sport?: ISport
  ) {}
}
