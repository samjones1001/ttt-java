package ttt.player;

import ttt.game.Game;
import ttt.service.PlayerService;

public class UnbeatablePlayer implements PlayerService {
    private String name;
    private String marker;

    public UnbeatablePlayer(String name, String marker) {
        this.name = name;
        this.marker = marker;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public Integer getMove(Game game) {
        return 8;
    }
}
