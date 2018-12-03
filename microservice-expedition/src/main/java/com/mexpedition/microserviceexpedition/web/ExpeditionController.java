package com.mexpedition.microserviceexpedition.web;

import com.mexpedition.microserviceexpedition.dao.ExpeditionDao;
import com.mexpedition.microserviceexpedition.model.Expedition;
import com.mexpedition.microserviceexpedition.web.exceptions.ExpeditionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    private ExpeditionDao dao;

    @PostMapping("expedition")
    @ResponseStatus(HttpStatus.CREATED)
    public void addExpedition(@RequestBody @NotNull Expedition exp) {
        dao.save(exp);
    }

    @GetMapping("expedition/{id}")
    public Expedition getExpedition(@PathVariable Integer id) {

        Optional<Expedition> expedition = dao.findById(id);

        if (expedition.isPresent()) {
            return expedition.get();
        }

        throw new ExpeditionNotFoundException(id);
    }

    @PatchMapping("expedition")
    public ResponseEntity<Expedition> updateExpedition(@RequestBody @NotNull Expedition exp) {
        Optional<Expedition> expeditionFromBDD = dao.findById(exp.getId());

        if (expeditionFromBDD.isPresent()) {
            Expedition newExp = dao.save(exp);
            return new ResponseEntity<>(newExp, HttpStatus.OK);
        }
        return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
    }
}
