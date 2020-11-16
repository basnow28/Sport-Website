package server.sport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sport.exception.ResourceNotFoundException;
import server.sport.model.Responsibility;
import server.sport.model.Sport;
import server.sport.repository.ResponsibilityRepository;
import server.sport.repository.SportRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsibilities")
public class ResponsibilityController {
    @Autowired
    ResponsibilityRepository responsibilityRepository;

    @Autowired
    SportRepository sportRepository;

    @GetMapping("/sport/{sport_id}")
    public ResponseEntity<List<Responsibility>> getListOfResponsibilitiesBySport(@PathVariable("sport_id") Integer sport_id) {
        try {
            Optional<Sport> sport = sportRepository.findById(sport_id);

            if(sport.isEmpty()) {
                //Customize the message -> Sport not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<Responsibility> responsibilityList = responsibilityRepository.findAllBySport(sport.get());

            if (!responsibilityList.isEmpty()) {
                return new ResponseEntity<>(responsibilityList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{responsibility_id}")
    public ResponseEntity<Responsibility> updateResponsibility(
            @PathVariable("responsibility_id") Integer responsibility_id,
            @RequestBody Responsibility responsibility){

        Optional<Responsibility> optionalResponsibility = responsibilityRepository.findById(responsibility_id);

        if(optionalResponsibility.isPresent()){
            Responsibility _responsibility = optionalResponsibility.get();
            _responsibility.setResponsibilityName(responsibility.getResponsibilityName());
            responsibilityRepository.save(responsibility);
            return new ResponseEntity<>(_responsibility, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Responsibility> createResponsibility(
            @RequestBody Responsibility responsibility){
        try{
            String sport_name = responsibility.getSport().getSportName();
            Optional<Sport> sport = sportRepository.findBySportName(sport_name);

            if(sport.isEmpty()){
                Sport _sport = sportRepository.save(new Sport(responsibility.getSport().getSportName()));
                responsibility.setSport(_sport);
            }else{
                responsibility.setSport(sport.get());
            }

            Responsibility _responsibility = responsibilityRepository.save(responsibility);
            return new ResponseEntity<>(_responsibility, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{responsibility_id}")
    public ResponseEntity<HttpStatus> deleteResponsibility(@PathVariable ("responsibility_id") int responsibilityId){
        Responsibility responsibility = responsibilityRepository.findById(responsibilityId).orElseThrow(
                () -> new ResourceNotFoundException("Responsibility with id " + responsibilityId + " not found."));
        responsibilityRepository.delete(responsibility);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
