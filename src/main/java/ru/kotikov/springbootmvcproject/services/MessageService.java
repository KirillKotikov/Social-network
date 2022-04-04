package ru.kotikov.springbootmvcproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kotikov.springbootmvcproject.domain.Message;
import ru.kotikov.springbootmvcproject.domain.User;
import ru.kotikov.springbootmvcproject.domain.dto.MessageDto;
import ru.kotikov.springbootmvcproject.repository.MessageRepo;

import javax.persistence.EntityManager;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTag(filter, pageable, user);
        } else return messageRepo.findAll(pageable, user);
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
       return messageRepo.findByUser(pageable, author, currentUser);
    }
}
