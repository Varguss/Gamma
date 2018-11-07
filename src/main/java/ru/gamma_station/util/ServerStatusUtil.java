package ru.gamma_station.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.gamma_station.domain.Player;
import ru.gamma_station.domain.ServerStatus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerStatusUtil {

    // http://www.byond.com/games/exadv1/spacestation13
    public static ServerStatus getGammaStationServerStatus() throws IOException {
        Document document = Jsoup.parse(new URL("http://www.byond.com/games/exadv1/spacestation13"), 10000);
        Elements elements = document.select(".live_game_entry");

        ServerStatus serverStatus = new ServerStatus();

        for (Element element : elements) {
            String content = element.select(".live_game_status").first().text();

            String serverName = content.substring(content.indexOf(" [") + 2, content.indexOf(" —"));

            if (serverName.contains("GammaStation")) {
                serverStatus.setOnline(true);

                String map = content.substring(content.indexOf(" —") + 3, content.indexOf(" ("));
                String mode = content.substring(content.indexOf("(site): ") + "(site): ".length(), content.indexOf(", ~"));
                String host = content.substring(content.indexOf("hosted by ") + "hosted by ".length(), content.indexOf("Logged in:")).trim().replace("]", "");

                serverStatus.setName(serverName);
                serverStatus.setMap(map);
                serverStatus.setMode(mode);
                serverStatus.setHost(host);

                List<Player> playersOnline = new ArrayList<>();

                Elements playerElements = element.select(".live_game_player_list").first().select("nobr");

                playerElements.forEach((playerElement) -> {
                    Element aTag = playerElement.select("a").first();

                    String byondLink = aTag.attr("href");
                    String ckey = aTag.attr("user_ckey");

                    Player playerEntity = new Player(byondLink, ckey);

                    playersOnline.add(playerEntity);
                });

                Element privatePlayersElement = element.select("span.smaller").last();

                int privatePlayersCount = Integer.parseInt(privatePlayersElement.ownText().split(" ")[0]);

                serverStatus.setPrivatePlayersCount(privatePlayersCount);
                serverStatus.setPlayersOnline(playersOnline);

                break; // Server info found, no metter to continue searching.
            }
        }

        return serverStatus;
    }
}