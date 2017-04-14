package a404_notfound.sourceappwater.model;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Handles The logic for Push Notifications across all apps
 */

public class OmiNotificationService extends FirebaseMessagingService {
    /**
     * Constructor for service
     */
    public OmiNotificationService() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
