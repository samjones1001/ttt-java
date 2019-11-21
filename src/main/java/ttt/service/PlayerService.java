package ttt.service;

import ttt.game.Game;

public interface PlayerService {
    String getName();
    String getMarker();
    Integer getMove(Game game);
}
