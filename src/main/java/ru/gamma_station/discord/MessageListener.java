package ru.gamma_station.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gamma_station.service.PostService;
import ru.gamma_station.util.MarkdownUtil;

@Component
public class MessageListener extends ListenerAdapter implements InitializingBean {
    private PostService postService;

    @Autowired
    public MessageListener(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JDA jda = new JDABuilder("NTMxNTgzNjI0MzQ1Mjg4NzE5.DxQKvw.V3pzWo8AW6pwZL5-UxtRYQaAN_U").build();
        jda.addEventListener(this);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();

        String serverName = event.getGuild().getName();
        if (serverName.equals("GammaStation Official") || serverName.equals("Discordia")) {
            if (channel.getType() == ChannelType.TEXT) {
                if (channel.getName().equals("testing-bay") || channel.getName().equals("news") || channel.getName().equals("devblog")) {
                    postService.saveDiscordPost(event.getAuthor().getName(), MarkdownUtil.translateMarkdownTextToHTML(event.getMessage().getContentRaw()), event.getMessageIdLong());
                }
            }
        }
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        MessageChannel channel = event.getChannel();

        String serverName = event.getGuild().getName();
        if (serverName.equals("GammaStation Official") || serverName.equals("Discordia")) {
            if (channel.getType() == ChannelType.TEXT) {
                if (channel.getName().equals("testing-bay") || channel.getName().equals("news") || channel.getName().equals("devblog")) {
                    postService.editDiscordPost(event.getMessageIdLong(), MarkdownUtil.translateMarkdownTextToHTML(event.getMessage().getContentRaw()));
                }
            }
        }
    }

    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        MessageChannel channel = event.getChannel();

        String serverName = event.getGuild().getName();
        if (serverName.equals("GammaStation Official") || serverName.equals("Discordia")) {
            if (channel.getType() == ChannelType.TEXT) {
                if (channel.getName().equals("testing-bay") || channel.getName().equals("news") || channel.getName().equals("devblog")) {
                    postService.deleteDiscordPost(event.getMessageIdLong());
                }
            }
        }
    }
}