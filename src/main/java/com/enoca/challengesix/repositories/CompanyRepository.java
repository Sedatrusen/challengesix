package com.enoca.challengesix.repositories;

import com.enoca.challengesix.entities.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company,Long> {
}
