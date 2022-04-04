package ru.kotikov.springbootmvcproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kotikov.springbootmvcproject.domain.Message;
import ru.kotikov.springbootmvcproject.domain.User;
import ru.kotikov.springbootmvcproject.domain.dto.MessageDto;

public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query(
            "select new ru.kotikov.springbootmvcproject.domain.dto.MessageDto(" +
                    "m, " +
                    "count(ml), " +
                    "sum (case when ml = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Message m left join m.likes ml " +
                    "group by m"
    )
    Page<MessageDto> findAll(Pageable pageable, @Param("user") User user);

    @Query(
            "select new ru.kotikov.springbootmvcproject.domain.dto.MessageDto(" +
                    "m, " +
                    "count(ml), " +
                    "sum (case when ml = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Message m left join m.likes ml " +
                    "where m.tag = :tag " +
                    "group by m"
    )
    Page<MessageDto> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query(
            "select new ru.kotikov.springbootmvcproject.domain.dto.MessageDto(" +
                    "m, " +
                    "count(ml), " +
                    "sum (case when ml = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Message m left join m.likes ml " +
                    "where m.author = :author " +
                    "group by m"
    )
    Page<MessageDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);
}
