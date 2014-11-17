package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeListDialog extends JDialog {
    private ChallengeList challengeableUsersList;

    public ChallengeListDialog() {
       this.challengeableUsersList = new ChallengeList();

       /**
        *  JList legger JComponents i en liste.
        *  en item må altså være en component
        */
       setVisible(true);
   }
}
