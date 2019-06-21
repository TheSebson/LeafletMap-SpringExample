package com.pie.se.pl.Repository;


import com.pie.se.pl.Model.Aggregate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AggregateRepository extends JpaRepository<Aggregate, Long> {


}