package clanavard.commands.fun;

import java.net.Proxy;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import clanavard.Utils;
import clanavard.commands.Category;
import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import clanavard.structures.YesNoResponse;

import com.google.gson.Gson;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@CommandInfo(
  name = "yesorno",
  description = "Ask a question and the bot will answer if yes or no",
  category = Category.FUN,
  args = "[QUESTION]"
)
public class YesNoCommand extends Command {
  @Override
  public void handle(MessageReceivedEvent event, List<String> args) {
    if (args == null) {
      sendMessage(event, "Provide a question");
      return;
    }

    if (!String.join(" ", args).endsWith("?")) {
      sendMessage(event, "Question should end with a question mark");
      return;
    }

    event.getMessage().getChannel().sendTyping().queue();

    Request req = new Request.Builder()
      .get()
      .url(HttpUrl.get("https://yesno.wtf/api"))
      .build();

    OkHttpClient client = new OkHttpClient.Builder()
      .followRedirects(true)
      .proxy(Proxy.NO_PROXY)
      .connectTimeout(Duration.ofSeconds(30))
      .build();

    try {
      Response res = client.newCall(req).execute();

      Gson gson = Utils.getGson();

      YesNoResponse resp = gson.fromJson(res.body().string(), YesNoResponse.class);
      
      Request imageReq = new Request.Builder()
        .get()
        .cacheControl(new CacheControl.Builder()
          .maxAge(24, TimeUnit.HOURS)
          .build()
        )
        .url(HttpUrl.get(resp.image))
        .build();

      Response imageRes = client.newCall(imageReq).execute();

      String filename = Paths.get(URI.create(resp.image).toURL().getFile()).getFileName().toString();

      MessageCreateData msgdata = new MessageCreateBuilder()
        .addContent(capitalize(resp.answer))
        .addFiles(
          FileUpload.fromData(imageRes.body().bytes(), filename)
        )
        .build();

      event.getMessage().getChannel().asTextChannel().sendMessage(msgdata).queue();
    } catch (Exception e) {
      sendMessage(event, "An error occurred");
      e.printStackTrace();
    }    
  }

  private String capitalize(String input){
    return Character.toUpperCase(input.charAt(0)) + input.substring(1, input.length());
  }
}
