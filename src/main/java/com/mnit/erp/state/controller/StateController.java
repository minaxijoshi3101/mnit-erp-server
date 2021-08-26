package com.mnit.erp.state.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.state.model.State;
import com.mnit.erp.state.service.StateService;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/state")
public class StateController extends AbstractController {

    @Autowired
    StateService stateService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody State state){
        State state1 = this.stateService.add(state);
        ResponseMessageType responseMessageType = Objects.nonNull(state1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(state1) ? "State added successfully! !" : "Unable to add state!")
                .messageType(responseMessageType)
                .response(state1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody State state){
        State state1 = this.stateService.add(state);
        ResponseMessageType responseMessageType = Objects.nonNull(state1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(state1) ? "State updated successfully! !" : "Unable to update state!")
                .messageType(responseMessageType)
                .response(state1).build();
    }

    @GetMapping("/findAllInCountry/{countryId}")
    public CustomResponseMessage findAllInCountry(@PathVariable Long countryId){
        List<State> states = this.stateService.findAllInCountry(countryId);
        ResponseMessageType responseMessageType = Objects.nonNull(states) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(states) && states.size() > 0 ? "States found!" : "Unable to find states data!")
                .messageType(responseMessageType)
                .response(states).build();
    }
    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        State state1 = this.stateService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(state1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(state1) ? "State found successfully! !" : "Unable to find state!")
                .messageType(responseMessageType)
                .response(state1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<State> states = this.stateService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(states) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(states) && states.size() > 0 ? "States found!" : "Unable to find states data!")
                .messageType(responseMessageType)
                .response(states).build();

    }

}

