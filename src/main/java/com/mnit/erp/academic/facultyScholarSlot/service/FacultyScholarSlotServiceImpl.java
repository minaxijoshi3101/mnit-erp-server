package com.mnit.erp.academic.facultyScholarSlot.service;

import com.mnit.erp.academic.facultyScholarSlot.model.FacultyScholarSlot;
import com.mnit.erp.academic.facultyScholarSlot.model.SlotApproveStatus;
import com.mnit.erp.academic.facultyScholarSlot.repository.FacultyScholarSlotRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Contains faculty scholar slot Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 27 July, 2021
 */
@Service
public class FacultyScholarSlotServiceImpl implements FacultyScholarSlotService {

    @Autowired
    FacultyScholarSlotRepository facultyScholarSlotRepository;

    @Override
    public FacultyScholarSlot add(FacultyScholarSlot facultyScholarSlot) {

        FacultyScholarSlot byPendingStatus = this.facultyScholarSlotRepository.findBySlotApproveStatus(SlotApproveStatus.PENDING);
        if(Objects.nonNull(byPendingStatus)){
            throw new ServiceException("faculty scholar slot for this student already exists. Can't add again!");
        }
        facultyScholarSlot.setSlotApproveStatus(SlotApproveStatus.PENDING);
        if(this.validate(facultyScholarSlot))
            return this.facultyScholarSlotRepository.save(facultyScholarSlot);
        return null;
    }

    @Override
    public FacultyScholarSlot update(FacultyScholarSlot facultyScholarSlot) {
        FacultyScholarSlot byPendingStatusAndIdNot = this.facultyScholarSlotRepository.findBySlotApproveStatusAndIdNot( SlotApproveStatus.PENDING, facultyScholarSlot.getId());
        if(Objects.nonNull(byPendingStatusAndIdNot)){
            throw new ServiceException("faculty scholar slot for this student already exists. Can't update!");
        }

        FacultyScholarSlot savedFacultyScholarSlot = this.facultyScholarSlotRepository.findById(facultyScholarSlot.getId()).orElse(null);
        if(Objects.isNull(savedFacultyScholarSlot)){
            throw new ServiceException("faculty scholar slot not found. Can't update!");
        }
        facultyScholarSlot.setSlotApproveStatus(SlotApproveStatus.PENDING);
        if(this.validate(facultyScholarSlot)) {
            CommonUtils.copyNonNullProperties(facultyScholarSlot, savedFacultyScholarSlot);
            return this.facultyScholarSlotRepository.save(facultyScholarSlot);
        }
        return null;
    }

    @Override
    @Transactional
    public FacultyScholarSlot approveOrReject(FacultyScholarSlot facultyScholarSlot) {
        FacultyScholarSlot savedFacultyScholarSlot = this.facultyScholarSlotRepository.findByIdAndSlotApproveStatus(facultyScholarSlot.getId(), SlotApproveStatus.PENDING);
        if(Objects.isNull(savedFacultyScholarSlot)){
            throw new ServiceException("faculty scholar slot not found. Can't approve or reject!");
        }
        if(this.validate(facultyScholarSlot)) {

            if (facultyScholarSlot.getSlotApproveStatus() == SlotApproveStatus.APPROVED){
                savedFacultyScholarSlot.setStatus(true);
                FacultyScholarSlot facultyScholarSlot1 = facultyScholarSlotRepository.findByEmployee_IdAndStatus(facultyScholarSlot.getEmployeeId(), true);
                if (Objects.nonNull(facultyScholarSlot1)) {
                    facultyScholarSlot1.setStatus(false);
                    this.facultyScholarSlotRepository.save(facultyScholarSlot1);
                }
            }

            savedFacultyScholarSlot.setSlotApproveStatus(facultyScholarSlot.getSlotApproveStatus());
            savedFacultyScholarSlot.setApprovedById(CurrentUser.getCurrentInMemoryUser().getId());


            return this.facultyScholarSlotRepository.save(savedFacultyScholarSlot);
        }
        return null;
    }

    @Override
    public FacultyScholarSlot find(Long id) {
        return this.facultyScholarSlotRepository.findById(id).orElse(null);
    }

    @Override
    public List<FacultyScholarSlot> findAll() {
        return this.facultyScholarSlotRepository.findAll();
    }

    private boolean validate(FacultyScholarSlot facultyScholarSlot){
        return true;
    }

}
