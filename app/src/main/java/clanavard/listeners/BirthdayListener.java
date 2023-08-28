package clanavard.listeners;

import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BirthdayListener extends ListenerAdapter {
	public BirthdayListener(){
	}
	@Override
	public void onGatewayPing(GatewayPingEvent event) {
    super.onGatewayPing(event);
    // new MessageReceivedEvent(event.getJDA(), event.getResponseNumber());
    // System.out.println(event);
	}
}
