package ua.hillel.bookstore.service;

import com.querydsl.jpa.impl.JPAQuery;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericQueryDSL<T> {
    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQuery<T> query;

    @PostConstruct
    private void init() {
        query = new JPAQuery<T>(entityManager);
    }
}