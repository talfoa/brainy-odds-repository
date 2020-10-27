import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.BrainyoddsAppRegionModule),
      },
      {
        path: 'sport',
        loadChildren: () => import('./sport/sport.module').then(m => m.BrainyoddsAppSportModule),
      },
      {
        path: 'league',
        loadChildren: () => import('./league/league.module').then(m => m.BrainyoddsAppLeagueModule),
      },
      {
        path: 'team',
        loadChildren: () => import('./team/team.module').then(m => m.BrainyoddsAppTeamModule),
      },
      {
        path: 'player',
        loadChildren: () => import('./player/player.module').then(m => m.BrainyoddsAppPlayerModule),
      },
      {
        path: 'match',
        loadChildren: () => import('./match/match.module').then(m => m.BrainyoddsAppMatchModule),
      },
      {
        path: 'game-event',
        loadChildren: () => import('./game-event/game-event.module').then(m => m.BrainyoddsAppGameEventModule),
      },
      {
        path: 'score-board',
        loadChildren: () => import('./score-board/score-board.module').then(m => m.BrainyoddsAppScoreBoardModule),
      },
      {
        path: 'opportunity',
        loadChildren: () => import('./opportunity/opportunity.module').then(m => m.BrainyoddsAppOpportunityModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BrainyoddsAppEntityModule {}
