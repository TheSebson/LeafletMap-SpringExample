package com.pie.se.pl.Controller;


import com.pie.se.pl.Dao.AggregateDAO;
import com.pie.se.pl.Dao.MarkerDAO;
import com.pie.se.pl.Model.Aggregate;
import com.pie.se.pl.Model.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private AggregateDAO aggregateDAO;

    @Autowired
    private MarkerDAO markerDAO;

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView welcomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/getMarkers")
    public ResponseEntity<Object> getAllBooks() {
        List<Aggregate> emp = aggregateDAO.findAll();
        ServiceResponse<List<Aggregate>> response = new ServiceResponse<>("success", emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/saveMarker")
    public ResponseEntity<Object> addBook(@RequestBody Marker book) {
        System.out.println(book);
        Aggregate agg = aggregateDAO.findOne(1L);
        agg.addToList(book);
        markerDAO.save(book);
        ServiceResponse<Marker> response = new ServiceResponse<Marker>("success", book);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteMarker")
    public ResponseEntity<Object> delete(@RequestBody Long id) {
        System.out.println(markerDAO.findOne(id));
        markerDAO.delete(markerDAO.findOne(id));
//        markerDAO.flush();
        ServiceResponse<Long> response = new ServiceResponse<>("success", id);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }




}
