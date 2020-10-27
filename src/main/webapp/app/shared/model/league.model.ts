import { ISport } from 'app/shared/model/sport.model';
import { IRegion } from 'app/shared/model/region.model';
import { ITeam } from 'app/shared/model/team.model';

export interface ILeague {
  id?: number;
  name?: string;
  sport?: ISport;
  region?: IRegion;
  teams?: ITeam[];
}

export class League implements ILeague {
  constructor(public id?: number, public name?: string, public sport?: ISport, public region?: IRegion, public teams?: ITeam[]) {}
}
