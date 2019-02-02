package ru.gamma_station.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.gamma_station.domain.Player;
import ru.gamma_station.domain.ServerStatus;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public abstract class ServerStatusUtil {

    // http://www.byond.com/games/exadv1/spacestation13
    public static ServerStatus getGammaStationServerStatus() throws IOException {
        Document document;
        try {
            document = Jsoup.connect("http://www.byond.com/games/exadv1/spacestation13").get();
        } catch (SocketTimeoutException e) {
            return new ServerStatus();
        }

        Elements elements = document.select(".live_game_entry");

        ServerStatus serverStatus = new ServerStatus();

        for (Element element : elements) {
            String content = element.select(".live_game_status").first().text();

            String serverName;
            try {
                serverName = content.substring(content.indexOf("[") + 1, content.indexOf(" —"));
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }

            if (serverName.contains("GammaStation")) {
                serverStatus.setOnline(true);

                String map = content.substring(content.indexOf(" —") + 3, content.indexOf(" ("));
                String mode = content.substring(content.indexOf("(site): ") + "(site): ".length(), content.indexOf(", ~"));

                String host;
                try {
                    host = content.substring(content.indexOf("hosted by ") + "hosted by ".length(), content.indexOf("Logged in:")).trim().replace("]", "");
                } catch (StringIndexOutOfBoundsException e) {
                    host = content.substring(content.indexOf("hosted by ") + "hosted by ".length(), content.indexOf("No players.")).trim().replace("]", "");
                }

                serverStatus.setName(serverName);
                serverStatus.setMap(map);
                serverStatus.setMode(mode);
                serverStatus.setHost(host);

                List<Player> playersOnline = new ArrayList<>();

                extractPlayers(element, playersOnline);

                int privatePlayersCount = extractPrivatePlayersCount(element);

                serverStatus.setPrivatePlayersCount(privatePlayersCount);
                serverStatus.setPlayersOnline(playersOnline);

                break; // Server info found, no metter to continue searching.
            }
        }

        return serverStatus;
    }

    private static int extractPrivatePlayersCount(Element element) {
        try {
            Element privatePlayersElement = element.select("span.smaller").last();

            return Integer.parseInt(privatePlayersElement.ownText().split(" ")[0]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static void extractPlayers(Element element, List<Player> playersOnline) {
        try {
            Elements playerElements = element.select(".live_game_player_list").first().select("nobr");

            playerElements.forEach((playerElement) -> {
                Element aTag = playerElement.select("a").first();

                String byondLink = aTag.attr("href");
                String ckey = aTag.attr("user_ckey");

                Player playerEntity = new Player(byondLink, ckey);

                playersOnline.add(playerEntity);
            });
        } catch (NullPointerException e) {

        }
    }

    public static ServerStatus getErisStationServerStatus() throws IOException {
        Document document;
        try {
            document = Jsoup.connect("http://www.byond.com/games/exadv1/spacestation13").get();
        } catch (SocketTimeoutException e) {
            return new ServerStatus();
        }

        Elements elements = document.select(".live_game_entry");

        ServerStatus serverStatus = new ServerStatus();

        for (Element element : elements) {
            String content = element.select(".live_game_status").first().text();

            String serverName;
            try {
                serverName = content.substring(content.indexOf("[") + 1, content.indexOf(" —"));
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }

            if (serverName.contains("CEV Eris: Discordia [RU]")) {
                serverStatus.setOnline(true);

                String map = content.substring(content.indexOf(" —") + 3, content.indexOf(" ("));
                String mode = content.substring(content.indexOf("(Default): ") + "(Default): ".length(), content.indexOf(", hosted"));

                String host;
                try {
                    host = content.substring(content.indexOf("hosted by ") + "hosted by ".length(), content.indexOf("Logged in:")).trim().replace("]", "");
                } catch (StringIndexOutOfBoundsException e) {
                    host = content.substring(content.indexOf("hosted by ") + "hosted by ".length(), content.indexOf("No players.")).trim().replace("]", "");
                }

                serverStatus.setName(serverName);
                serverStatus.setMap(map);
                serverStatus.setMode(mode);
                serverStatus.setHost(host);

                List<Player> playersOnline = new ArrayList<>();

                extractPlayers(element, playersOnline);
                int privatePlayersCount = extractPrivatePlayersCount(element);

                serverStatus.setPrivatePlayersCount(privatePlayersCount);
                serverStatus.setPlayersOnline(playersOnline);

                break; // Server info found, no metter to continue searching.
            }
        }

        return serverStatus;
    }
}