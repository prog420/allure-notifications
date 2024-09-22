package guru.qa.allure.notifications.config.telegram;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * @author kadehar
 * @since 4.0
 * Model class representing telegram settings.
 */
@Getter
public class Telegram {
    @SerializedName("token")
    private String token;
    @SerializedName("chat")
    private String chat;
    @SerializedName("thread")
    private String thread;
    @SerializedName("replyTo")
    private String replyTo;
    @SerializedName("templatePath")
    private String templatePath = "/templates/telegram.ftl";

    public String getThread() {
        return thread != null ? thread : "";
    }
}
