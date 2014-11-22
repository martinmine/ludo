package no.hig.imt3281.ludo.backend.game;

/**
 * Created by marti_000 on 22.11.2014.
 */
public interface GameMapUpdateListener {
    public void tokenUpdated(final int factionId, final int tokenId, final int position);
}
