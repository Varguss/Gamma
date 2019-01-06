package ru.gamma_station.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.gamma_station.dao.BanDAO;
import ru.gamma_station.dao.DAOException;
import ru.gamma_station.domain.Ban;
import ru.gamma_station.domain.ServerStatus;
import ru.gamma_station.util.PropertiesUtil;
import ru.gamma_station.util.ServerStatusUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/banhub")
public class BanhubController {
    private BanDAO gammaDAO, erisDAO;

    private int limit = PropertiesUtil.getBanhubRequestsLimit(), currentRequests = 0;
    private List<String> gammaPlayersCkeys, gammaAdminsCkeys, erisPlayersCkeys, erisAdminsCkeys;

    private static final Logger logger = Logger.getLogger(BanhubController.class);

    @Autowired
    public BanhubController(BanDAO gammaDAO, BanDAO erisDAO) {
        logger.debug("Инициализация контроллера банхаба...");
        this.gammaDAO = gammaDAO;
        this.erisDAO = erisDAO;

        try {
            logger.debug("Первоначальная загрузка кэша...");

            gammaPlayersCkeys = gammaDAO.getAllUniqueCkeys();
            gammaAdminsCkeys = gammaDAO.getAllUniqueAdminCkeys();
            erisPlayersCkeys = erisDAO.getAllUniqueCkeys();
            erisAdminsCkeys = erisDAO.getAllUniqueAdminCkeys();

            logger.debug("Инициализация контроллера банхаба завершена!");
        } catch (DAOException e) {
            logger.fatal("Ошибка инициализации банхаба :(", e);

            throw new BeanInitializationException("BanhubController initialization is failed.", e);
        }
    }

    @ModelAttribute(name = "eris")
    public ServerStatus addErisServerStatus() throws IOException {
        return ServerStatusUtil.getErisStationServerStatus();
    }

    @ModelAttribute(name = "gamma")
    public ServerStatus addGammaServerStatus() throws IOException {
        return ServerStatusUtil.getGammaStationServerStatus();
    }

    @GetMapping("gamma/player")
    public ModelAndView playerBanhub(@RequestParam(name = "ckey", required = false) String ckey,
                                     @RequestParam(required = false, name = "jobban") String jobban,
                                     HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        logger.info(String.format("Запрос с адреса %s. Цель запроса: %s", request.getRemoteAddr(), ckey == null ? "посещение." : "пользование."));

        if (ckey != null) {
            if (currentRequests >= limit) {
                modelAndView.addObject("limit_message", "Превышен лимит запросов. Повторите запрос через небольшое время.");

                logger.warn("Пользователю " + request.getRemoteAddr() + " закрыт доступ к данным из-за превышения лимита запросов. Доступ будет возобновлен после следующего сброса лимита.");

                modelAndView.setViewName("banhub/banhub_admin_limited");

                return modelAndView;
            } else {
                if (gammaPlayersCkeys.contains(ckey.toLowerCase())) {
                    logger.info("Извлечение данных о игроке " + ckey + ", текущее количество полученных запросов: " + currentRequests);
                    currentRequests++;

                    try {
                        List<Ban> bans = gammaDAO.getBansByCkey(ckey);
                        if (jobban != null)
                            bans.addAll(gammaDAO.getJobBansByCkey(ckey));

                        modelAndView.addObject("bans", bans);

                        logger.info(String.format("Данные об игроке %s были успешно отправлены по адресу %s!", ckey, request.getRemoteAddr()));
                    } catch (DAOException e) {
                        currentRequests--;
                        logger.error("Ошибка извлечения данных игрока " + ckey + " для отправки по адресу " + request.getRemoteAddr(), e);
                        modelAndView.addObject("retrieving_error", "Oops, похоже, на сервере неполадки, просим прощения! Если вы увидели данное сообщение, сообщите данную информацию в Дискорде администрации!");

                        modelAndView.setViewName("banhub/banhub_admin_error");
                        return modelAndView;
                    }
                } else {
                    logger.info("Данные об игроке " + ckey + " еще не были занесены в кэш, или же данный игрок не получал еще баны.");
                }
            }
        }

        modelAndView.setViewName("bans");

        return modelAndView;
    }

    @GetMapping("gamma/admin")
    public ModelAndView adminBanhub(@RequestParam(name = "ckey", required = false) String ckey,
                                    @RequestParam(required = false, name = "jobban") String jobban,
                                    HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        logger.info(String.format("Запрос с адреса %s. Цель запроса: %s", request.getRemoteAddr(), ckey == null || ckey.isEmpty() ? "посещение." : "пользование."));

        if (ckey != null) {
            if (currentRequests >= limit) {
                modelAndView.addObject("limit_message", "Превышен лимит запросов. Повторите запрос через небольшое время.");

                logger.warn("Пользователю " + request.getRemoteAddr() + " закрыт доступ к данным из-за превышения лимита запросов. Доступ будет возобновлен после следующего сброса лимита.");

                modelAndView.setViewName("banhub/banhub_admin_limited");

                return modelAndView;
            } else {
                if (gammaAdminsCkeys.contains(ckey.toLowerCase())) {
                    currentRequests++;

                    try {
                        List<Ban> bans = gammaDAO.getBansByAdminCkey(ckey);
                        if (jobban != null)
                            bans.addAll(gammaDAO.getJobBansByAdminCkey(ckey));

                        modelAndView.addObject("bans", bans);

                        logger.info(String.format("Данные об администраторе %s были успешно отправлены по адресу %s!", ckey, request.getRemoteAddr()));
                    } catch (DAOException e) {
                        currentRequests--;
                        logger.error("Ошибка извлечения данных администратора " + ckey + " для отправки по адресу " + request.getRemoteAddr(), e);
                        modelAndView.addObject("retrieving_error", "Oops, похоже, на сервере неполадки, просим прощения! Если вы увидели данное сообщение, сообщите данную информацию в Дискорде администрации!");

                        modelAndView.setViewName("banhub/banhub_admin_error");

                        return modelAndView;
                    }
                } else {
                    logger.info("Данные об администраторе " + ckey + " не обнаружены в кэше. Возможно, данного администратора нет, или он еще не выдавал баны/джоббаны.");
                }
            }
        }

        modelAndView.setViewName("bans");

        return modelAndView;
    }

    @Scheduled(fixedDelayString = "${cache_update_delay}", initialDelayString = "${cache_update_delay}")
    void getCash() {
        logger.debug("Обновление кэша...");
        try {
            gammaPlayersCkeys = gammaDAO.getAllUniqueCkeys();
            gammaAdminsCkeys = gammaDAO.getAllUniqueAdminCkeys();
            erisPlayersCkeys = erisDAO.getAllUniqueCkeys();
            erisAdminsCkeys = erisDAO.getAllUniqueAdminCkeys();

            logger.debug("Обновления кэша завершено!");
        } catch (DAOException e) {
            logger.error("Ошибка обновления кэша.", e);
        }
    }

    @Scheduled(fixedDelayString = "${banhub_requests_limit_reset_rate}", initialDelayString = "${banhub_requests_limit_reset_rate}")
    void resetLimit() {
        logger.debug("Сброс лимита запросов!");

        currentRequests = 0;
    }
}
