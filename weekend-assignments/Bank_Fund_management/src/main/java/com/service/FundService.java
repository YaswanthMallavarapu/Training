package com.service;

import com.exception.ManagerNotFoundException;
import com.model.Fund;
import com.model.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FundService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addManager(Manager manager) {
        em.persist(manager);
    }

    public Manager check(Long managerId) throws ManagerNotFoundException {
        Manager manager=em.find(Manager.class,managerId);
        if(manager==null){
            throw new ManagerNotFoundException("manager with id not found");
        }
        return manager;
    }

    @Transactional
    public void addFund(Fund fund, Manager manager) {
        fund.setManager(manager);
        em.persist(fund);

    }

    public List<Fund> getFundsByManagerName(String name) {
        String jpql="select f from Fund f where f.manager.name =:managerName";
        Query query=em.createQuery(jpql,Fund.class);
        query.setParameter("managerName",name);
        return query.getResultList();
    }
}
