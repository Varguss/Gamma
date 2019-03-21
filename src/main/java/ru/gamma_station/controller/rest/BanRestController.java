package ru.gamma_station.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.gamma_station.dao.BanDAO;
import ru.gamma_station.dao.DAOException;
import ru.gamma_station.domain.Ban;
import ru.gamma_station.exception.ResourceNotFoundException;

import java.util.List;

@Slf4j
@RestController
public class BanRestController {
    private BanDAO gammaDAO, erisDAO;
    private List<String> gammaPlayersCkeys, gammaAdminsCkeys, erisPlayersCkeys, erisAdminsCkeys;

    @Autowired
    public BanRestController(@Qualifier("gammaBanDAO") BanDAO gammaDAO, @Qualifier("erisBanDAO") BanDAO erisDAO) {
        log.debug("Инициализация рест контроллера банхаба...");
        this.gammaDAO = gammaDAO;
        this.erisDAO = erisDAO;

        try {
            log.debug("Первоначальная загрузка кэша...");

            gammaPlayersCkeys = gammaDAO.getAllUniqueCkeys();
            gammaAdminsCkeys = gammaDAO.getAllUniqueAdminCkeys();
            erisPlayersCkeys = erisDAO.getAllUniqueCkeys();
            erisAdminsCkeys = erisDAO.getAllUniqueAdminCkeys();

            log.debug("Инициализация рест контроллера банхаба завершена!");
        } catch (DAOException e) {
            log.error("Ошибка рест инициализации банхаба :(", e);

            throw new BeanInitializationException("BanRestController initialization is failed.", e);
        }
    }

    @GetMapping("bans/gamma/player/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getGammaBansByPlayer(@PathVariable("ckey") String ckey) throws DAOException {
        if (isGammaPlayerCashed(ckey)) {
            return gammaDAO.getBansByCkey(ckey);
        }

        throw new ResourceNotFoundException("Gamma player " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("bans/gamma/admin/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getGammaBansByAdmin(@PathVariable("ckey") String ckey) throws DAOException {
        if (isGammaAdminCashed(ckey)) {
            return gammaDAO.getBansByAdminCkey(ckey);
        }

        throw new ResourceNotFoundException("Gamma admin " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("bans/eris/player/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getErisBansByPlayer(@PathVariable("ckey") String ckey) throws DAOException {
        if (isErisPlayerCashed(ckey)) {
            return erisDAO.getBansByCkey(ckey);
        }

        throw new ResourceNotFoundException("Eris player " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("bans/eris/admin/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getErisBansByAdmin(@PathVariable("ckey") String ckey) throws DAOException {
        if (isErisAdminCashed(ckey)) {
            return erisDAO.getBansByAdminCkey(ckey);
        }

        throw new ResourceNotFoundException("Eris admin " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("jobbans/gamma/player/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getGammaJobBansByPlayer(@PathVariable("ckey") String ckey) throws DAOException {
        if (isGammaPlayerCashed(ckey)) {
            return gammaDAO.getJobBansByCkey(ckey);
        }

        throw new ResourceNotFoundException("Gamma player " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("jobbans/gamma/admin/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getGammaJobBansByAdmin(@PathVariable("ckey") String ckey) throws DAOException {
        if (isGammaAdminCashed(ckey)) {
            return gammaDAO.getJobBansByAdminCkey(ckey);
        }

        throw new ResourceNotFoundException("Gamma admin " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("jobbans/eris/player/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getErisJobBansByPlayer(@PathVariable("ckey") String ckey) throws DAOException {
        if (isErisPlayerCashed(ckey)) {
            return erisDAO.getJobBansByCkey(ckey);
        }

        throw new ResourceNotFoundException("Eris player " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    @GetMapping("jobbans/eris/admin/{ckey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ban> getErisJobBansByAdmin(@PathVariable("ckey") String ckey) throws DAOException {
        if (isErisAdminCashed(ckey)) {
            return erisDAO.getJobBansByAdminCkey(ckey);
        }

        throw new ResourceNotFoundException("Eris admin " + ckey + " hasn't found in cash, try later after cash update or use another ckey");
    }

    private boolean isGammaPlayerCashed(String ckey) {
        return gammaPlayersCkeys.contains(ckey.toLowerCase());
    }

    private boolean isErisPlayerCashed(String ckey) {
        return erisPlayersCkeys.contains(ckey.toLowerCase());
    }

    private boolean isGammaAdminCashed(String ckey) {
        return gammaAdminsCkeys.contains(ckey.toLowerCase());
    }

    private boolean isErisAdminCashed(String ckey) {
        return erisAdminsCkeys.contains(ckey.toLowerCase());
    }

    @Scheduled(fixedDelayString = "${cache_update_delay}", initialDelayString = "${cache_update_delay}")
    void getCash() {
        log.debug("Обновление кэша...");
        try {
            gammaPlayersCkeys = gammaDAO.getAllUniqueCkeys();
            gammaAdminsCkeys = gammaDAO.getAllUniqueAdminCkeys();
            erisPlayersCkeys = erisDAO.getAllUniqueCkeys();
            erisAdminsCkeys = erisDAO.getAllUniqueAdminCkeys();

            log.debug("Обновления кэша завершено!");
        } catch (DAOException e) {
            log.error("Ошибка обновления кэша.", e);
        }
    }
}
