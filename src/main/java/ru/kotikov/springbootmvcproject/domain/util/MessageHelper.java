package ru.kotikov.springbootmvcproject.domain.util;

import ru.kotikov.springbootmvcproject.domain.User;

public abstract class MessageHelper  {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
