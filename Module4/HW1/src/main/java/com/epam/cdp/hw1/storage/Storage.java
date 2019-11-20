package com.epam.cdp.hw1.storage;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.IBaseModel;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.model.impl.EventImpl;
import com.epam.cdp.hw1.model.impl.TicketImpl;
import com.epam.cdp.hw1.model.impl.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Storage {

    private static Logger logger = LoggerFactory.getLogger(Storage.class);

    private String ticketFilePath;
    private String eventFilePath;
    private String userFilePath;

    private Map<String, Ticket> ticketStorage = new HashMap<>();
    private Map<String, Event> eventStorage = new HashMap<>();
    private Map<String, User> userStorage = new HashMap<>();

    private Function<String, Ticket> matToTicket = (line) -> {
        String[] split = trimRow(line);

        Ticket ticket = new TicketImpl();
        ticket.setId(Long.parseLong(split[0]));
        ticket.setUserId(Long.parseLong(split[1]));
        ticket.setEventId(Long.parseLong(split[2]));
        ticket.setCategory(Ticket.Category.valueOf(split[3]));
        ticket.setPlace(Integer.parseInt(split[4]));

        return ticket;
    };

    private String[] trimRow(String line) {
        return Arrays.stream(line.split(",")).map(String::trim).toArray(String[]::new);
    }

    private Function<String, Event> mapToEvent = (line) -> {
        String[] split = trimRow(line);

        Event event = new EventImpl();
        event.setId(Long.parseLong(split[0]));
        event.setTitle(split[1]);
        event.setDate(LocalDate.parse(split[2]));

        return event;
    };
    private Function<String, User> mapToUser = (line) -> {
        String[] split = trimRow(line);

        User user = new UserImpl();
        user.setId(Long.parseLong(split[0]));
        user.setName(split[1]);
        user.setEmail(split[2]);

        return user;
    };

    /**
     * init storage
     */
    @PostConstruct
    public void init() {
        logger.info("Storage initializing...");
        ticketStorage = (Map<String, Ticket>) readFile(ticketFilePath, matToTicket);
        eventStorage = (Map<String, Event>) readFile(eventFilePath, mapToEvent);
        userStorage = (Map<String, User>) readFile(userFilePath, mapToUser);
        logger.info("Storage initializing has been finished successfully.");
    }

    private Map<String, ? extends IBaseModel> readFile(String inputFilePath, Function<String, ? extends IBaseModel> mapObjects) {
        Map<String, ? extends IBaseModel> result = new HashMap<>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF.getAbsoluteFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            result = br.lines().skip(1)
                    .map(mapObjects)
                    .collect(Collectors.toMap(ticket -> String.valueOf(ticket.getId()), Function.identity()));
            br.close();
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        return result;
    }

    public Map<String, Ticket> getTicketStorage() {
        return ticketStorage;
    }

    public Map<String, Event> getEventStorage() {
        return eventStorage;
    }

    public Map<String, User> getUserStorage() {
        return userStorage;
    }

    public String getTicketFilePath() {
        return ticketFilePath;
    }

    public void setTicketFilePath(String ticketFilePath) {
        this.ticketFilePath = ticketFilePath;
    }

    public String getEventFilePath() {
        return eventFilePath;
    }

    public void setEventFilePath(String eventFilePath) {
        this.eventFilePath = eventFilePath;
    }

    public String getUserFilePath() {
        return userFilePath;
    }

    public void setUserFilePath(String userFilePath) {
        this.userFilePath = userFilePath;
    }
}
