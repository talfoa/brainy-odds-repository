import { ITeam } from 'app/shared/model/team.model';

export interface IPlayer {
  id?: number;
  name?: string;
  nickName?: string;
  shirtNumber?: number;
  position?: string;
  team?: ITeam;
}

export class Player implements IPlayer {
  constructor(
    public id?: number,
    public name?: string,
    public nickName?: string,
    public shirtNumber?: number,
    public position?: string,
    public team?: ITeam
  ) {}
}
