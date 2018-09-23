package org.newbuy.livestream.eventlisteneradapters;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom AtmosphereResourceEventListenerAdapter.
 * @author Ian van Nieuwkoop
 * @version 1.0
 */
public final class LiveStreamEventListenerAdapter extends AtmosphereResourceEventListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(LiveStreamEventListenerAdapter.class);
    
    @Override
    public void onBroadcast(AtmosphereResourceEvent event) {
    	logger.debug("broadcasting");
    }
    
    @Override
    public void onDisconnect(AtmosphereResourceEvent event) {
        if (event.isCancelled()) {
            logger.debug("Browser {} unexpectedly disconnected", event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            logger.debug("Browser {} closed the connection", event.getResource().uuid());
        }
    }
}
