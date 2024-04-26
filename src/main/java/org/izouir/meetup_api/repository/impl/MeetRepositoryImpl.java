package org.izouir.meetup_api.repository.impl;

import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.izouir.meetup_api.entity.Meet;
import org.izouir.meetup_api.repository.MeetRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MeetRepositoryImpl implements MeetRepository {
    private final SessionFactory sessionFactory;

    public MeetRepositoryImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Meet> findAll() {
        final var session = sessionFactory.getCurrentSession();
        return session.createQuery("from Meet", Meet.class).getResultList();
    }

    @Override
    public Optional<Meet> find(final Long meetId) {
        final var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Meet.class, meetId));
    }

    @Override
    public void save(final Meet meet) {
        final var session = sessionFactory.getCurrentSession();
        session.persist(meet);
    }

    @Override
    @Transactional
    public void update(final Meet meet) {
        final var session = sessionFactory.getCurrentSession();
        session.clear();
        session.merge(meet);
    }

    @Override
    @Transactional
    public void delete(final Long meetId) {
        final var session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("delete from Meet where meetId = :meetId", Meet.class);
        query.setParameter("meetId", meetId);
        query.executeUpdate();
    }

    @Override
    public List<Meet> findAllFiltered(String title, String keeper, final Timestamp date) {
        title = "%" + title.toLowerCase() + "%";
        keeper = "%" + keeper.toLowerCase() + "%";
        final var session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("from Meet where lower(title) like :title and lower(keeper) like :keeper and date >= :date", Meet.class);
        query.setParameter("title", title);
        query.setParameter("keeper", keeper);
        query.setParameter("date", date);

        final List<Meet> resultList = new ArrayList<>();
        for (final var o : query.getResultList()) {
            resultList.add((Meet) o);
        }

        return resultList;
    }
}
