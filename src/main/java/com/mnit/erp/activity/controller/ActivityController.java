package com.mnit.erp.activity.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.activity.model.Activity;
import com.mnit.erp.activity.service.ActivityService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

    @Autowired
    ActivityService activityService;

    @PostMapping("add")
    public CustomResponseMessage add(@RequestBody Activity activity){
        Activity activity1 = this.activityService.add(activity);
        ResponseMessageType responseMessageType = Objects.nonNull(activity1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(activity1) ? "Activity record added successfully!" : "Unable to add activity!")
                .messageType(responseMessageType)
                .response(activity1).build();
    }

    @PutMapping("update")
    public CustomResponseMessage update(@RequestBody Activity activity){
        Activity activity1 = this.activityService.update(activity);
        ResponseMessageType responseMessageType = Objects.nonNull(activity1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(activity1) ? "Activity record updated successfully!" : "Unable to update activity!")
                .messageType(responseMessageType)
                .response(activity1).build();
    }

    @GetMapping("find/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Activity activity1 = this.activityService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(activity1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(activity1) ? "Activity found successfully!" : "Unable to find activity!")
                .messageType(responseMessageType)
                .response(activity1).build();
    }

    @GetMapping("findByName/{name}")
    public CustomResponseMessage findByName(@PathVariable String name){
        Activity byName = this.activityService.findByName(name);
        ResponseMessageType responseMessageType = Objects.nonNull(byName) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(byName) ? "Activity found successfully!" : "Unable to find activity!")
                .messageType(responseMessageType)
                .response(byName).build();
    }

    @GetMapping("findAll")
    public CustomResponseMessage findAll(Pageable pageable)
    {
        Page<Activity> activities = this.activityService.findAll(pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(activities) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(activities) && activities.getSize() > 0 ? "Activities found successfully!" : "Unable to find activity!")
                .messageType(responseMessageType)
                .response(activities).build();
    }

}
