package com.mnit.erp.activity.service;

import com.mnit.erp.activity.model.Activity;
import com.mnit.erp.activity.repository.ActivityRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.template.model.MessageTemplate;
import com.mnit.erp.template.service.MessageTemplateRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    MessageTemplateRepository messageTemplateRepository;

    public Activity add(Activity activity) {
        Activity activity1 = this.activityRepository.findByName(activity.getName());
        if(Objects.nonNull(activity1)){
            throw new ServiceException("Activity : " + activity1.getName() + " already exists. Use different name!");
        }
        if(this.validate(activity)){
            this.mapActivityWithMessageTemplate(activity);
            activity.setAddedBy(CurrentUser.getCurrentInMemoryUser().getId());
            activity.setAddedOn(new Date());
            return this.activityRepository.save(activity);
        }
        return null;
    }

    private Activity mapActivityWithMessageTemplate(Activity activity){
        if(Objects.nonNull(activity.getMessageTemplateId())){
            MessageTemplate messageTemplate = this.messageTemplateRepository.findById(activity.getMessageTemplateId()).orElse(null);
            if(Objects.isNull(messageTemplate)){
                throw new ServiceException("Message Template : " + activity.getMessageTemplateId() + " not found. Can't map with activity!");
            }
            activity.setMessageTemplate(messageTemplate);
        }
        return  activity;
    }

    private boolean validate(Activity activity) {
        return true;
    }

    public Activity update(Activity activity) {
        if(Objects.nonNull(activity.getName())){
            Activity activity1 = this.activityRepository.findByName(activity.getName());
            if(Objects.nonNull(activity1)){
                throw new ServiceException("Activity : " + activity1.getName() + " already exists. Use different name!");
            }
        }
        if(this.validate(activity)){
            this.mapActivityWithMessageTemplate(activity);
            Activity savedActivity = this.activityRepository.findById(activity.getId()).orElse(null);
            CommonUtils.copyNonNullProperties(activity, savedActivity);
            return this.activityRepository.save(savedActivity);
        }
        return null;
    }

    public Activity find(Long id) {
        return this.activityRepository.findById(id).orElse(null);
    }

    public Activity findByName(String name) {
        return this.activityRepository.findByName(name);
    }

    public Page<Activity> findAll(Pageable pageable) {
        return this.activityRepository.findAll(pageable);
    }

}
