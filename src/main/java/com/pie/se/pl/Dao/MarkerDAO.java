package com.pie.se.pl.Dao;

import com.pie.se.pl.Model.Marker;
import com.pie.se.pl.Repository.MarkerReppository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkerDAO {

    @Autowired
    MarkerReppository markerReppository;


    public Marker save(Marker marker){
        return markerReppository.save(marker);
    }

    public List<Marker> findAll(){
        return markerReppository.findAll();
    }

    public Marker findOne(Long id){
        return markerReppository.findOne(id);
    }

    public void delete(Marker marker){
        markerReppository.delete(marker);
    }

    public void flush(){
        markerReppository.flush();
    }

}
