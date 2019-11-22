package ttt.player;

import ttt.game.Game;

public interface Player {
    String getName();
    String getMarker();
    Integer getMove(Game game);
}
