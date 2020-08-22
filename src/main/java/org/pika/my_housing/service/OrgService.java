package org.pika.my_housing.service;

import org.pika.my_housing.repos.OrganizationRepo;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
    private final OrganizationRepo orgRepo;
    public OrgService(OrganizationRepo orgRepo) {
        this.orgRepo = orgRepo;
    }
}
