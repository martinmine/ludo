package no.hig.imt3281.ludo.messaging;

/**
 * Message sent when the user sends a chat message in-game.
 */
public class GameChatMessage extends ChatMessage {
    private int gameId;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
