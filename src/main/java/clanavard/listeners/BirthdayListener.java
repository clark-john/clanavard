package clanavard.listeners;

import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class BirthdayListener extends ListenerAdapter {
  @Override
  public void onGatewayPing(@NotNull @Nonnull GatewayPingEvent event) {
    super.onGatewayPing(event);
  }
}
