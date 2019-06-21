package com.pie.se.pl.Dao;


import com.pie.se.pl.Model.Aggregate;
import com.pie.se.pl.Repository.AggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregateDAO {
	
	@Autowired
	AggregateRepository aggregateRepository;

	
	public Aggregate save(Aggregate emp) {
		return aggregateRepository.save(emp);
	}
	

	
	public List<Aggregate> findAll(){
		return aggregateRepository.findAll();
	}
	

	public Aggregate findOne(Long empid) {
		return aggregateRepository.findOne(empid);
	}
	

	
	public void delete(Aggregate emp) {
		aggregateRepository.delete(emp);
	}





}
