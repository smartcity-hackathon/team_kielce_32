package com.fixit.server.database;


import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue, Integer> {

}
