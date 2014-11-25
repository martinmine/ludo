package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.AudioManager;
import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.client.gui.game.Faction;
import no.hig.imt3281.ludo.messaging.GameStartedMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type GameStartedMessage
 */
public class GameStartedMessageHandler implements MessageHandler {
    public void handle(GameStartedMessage message, CommunicationContext context) {
        GuiManager.getSideTopPanel().getDicePanel().setValue(0);
        GuiManager.getSideTopPanel().getDicePanel().repaint();
        GuiManager.getSideTopPanel().getFeedbackPanel().setFontColorByFaction(message.getFaction());

        if (message.getFaction() == Faction.RED.getIndex()) {
            GuiManager.getSideTopPanel()
                      .getFeedbackPanel()
                      .setText(Main.resourceBundle.getString("PLAYER_TURN"));
        } else {
            GuiManager.getSideTopPanel()
                      .getFeedbackPanel()
                      .setText(Main.resourceBundle.getString("GAME_WAITING"));
        }

        GuiManager.getSideTopPanel().getFeedbackPanel().setText("");
        GuiManager.getGamePanel().setCurrentPlayerFaction(message.getFaction());
        System.out.println("Your color is: " + message.getFaction());
        AudioManager.playSound("start_game.wav", true);
    }
}
