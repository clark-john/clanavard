package clanavard.commands.fun

import java.nio.file.Paths
import java.time.Duration
import java.util.concurrent.TimeUnit

import clanavard.Utils
import clanavard.commands.Category
import clanavard.commands.Command
import clanavard.commands.CommandInfo
import clanavard.structures.YesNoResponse

import com.google.gson.Gson
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.utils.FileUpload
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import okhttp3.CacheControl
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

@CommandInfo(
  name = "yesorno",
  description = "Ask a question and the bot will answer if yes or no",
  category = Category.FUN,
  args = "[QUESTION]"
)
class YesNoCommand extends Command {
  @Override
  void handle(MessageReceivedEvent event, List<String> args) {
    if (!args) {
      sendMessage(event, "Provide a question")
      return
    }

    if (!args.join(" ").endsWith("?")) {
      sendMessage(event, "Question should end with a question mark")
      return
    }

    event.getMessage().getChannel().sendTyping().queue()

    def req = new Request.Builder()
      .get()
      .url(HttpUrl.get("https://yesno.wtf/api"))
      .build()

    def client = new OkHttpClient.Builder()
      .followRedirects(true)
      .proxy(Proxy.NO_PROXY)
      .connectTimeout(Duration.ofSeconds(30))
      .build()

    def res = client.newCall(req).execute()

    Gson gson = Utils.getGson()

    def resp = gson.fromJson(res.body().string(), YesNoResponse.class)
    
    def imageReq = new Request.Builder()
      .get()
      .cacheControl(new CacheControl.Builder()
        .maxAge(24, TimeUnit.HOURS)
        .build()
      )
      .url(HttpUrl.get(resp.image))
      .build()

    def imageRes = client.newCall(imageReq).execute()

    def filename = Paths.get(URI.create(resp.image).toURL().getFile()).getFileName().toString()

    def msgdata = new MessageCreateBuilder()
      .addContent(capitalize(resp.answer))
      .addFiles(
        FileUpload.fromData(imageRes.body().bytes(), filename)
      )
      .build()

    event.getMessage().getChannel().asTextChannel().sendMessage(msgdata).queue()
  }

  private String capitalize(String input){
    return Character.toUpperCase(input.charAt(0)) as String + input.substring(1, input.length())
  }
}
