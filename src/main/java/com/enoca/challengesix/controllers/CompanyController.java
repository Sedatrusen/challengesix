package com.enoca.challengesix.controllers;

import com.enoca.challengesix.entities.Company;
import com.enoca.challengesix.exception.CompanyNotFoundException;
import com.enoca.challengesix.repositories.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyRepository repository;

    CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/company")
    List<Company> all() {
        return (List<Company>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/company")
    Company newCompany(@RequestBody Company newCompany) {
        return repository.save(newCompany);
    }

    // Single item

    @GetMapping("/company/{id}")
    Company one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @PutMapping("/company/{id}")
    Company replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) {

        return repository.findById(id)
                .map(company -> {
                   company.setName(newCompany.getName());
                    return repository.save(company);
                })
                .orElseGet(() -> {

                    return repository.save(newCompany);
                });
    }

    @DeleteMapping("/company/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
