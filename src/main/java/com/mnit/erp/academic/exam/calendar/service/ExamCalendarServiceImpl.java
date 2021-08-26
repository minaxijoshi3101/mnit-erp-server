package com.mnit.erp.academic.exam.calendar.service;

import com.mnit.erp.academic.exam.calendar.model.ExamCalendar;
import com.mnit.erp.academic.exam.calendar.repository.ExamCalendarRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.room.model.Room;
import com.mnit.erp.room.model.RoomAllotment;
import com.mnit.erp.room.model.RoomStudentLink;
import com.mnit.erp.room.repository.RoomAllotmentRepository;
import com.mnit.erp.room.repository.RoomStudentLinkRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Contains Exam calendar Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 22 June, 2021
 */
@Service
public class ExamCalendarServiceImpl implements ExamCalendarService {

    @Autowired
    ExamCalendarRepository examCalendarRepository;

    @Autowired
    RoomAllotmentRepository roomAllotmentRepository;

    @Autowired
    RoomStudentLinkRepository roomStudentLinkRepository;

    @Override
    public ExamCalendar add(ExamCalendar examCalendar) {
        ExamCalendar examCalendar1;
        if (this.validate(examCalendar)) {
            examCalendar1 = this.examCalendarRepository.save(examCalendar);
            List<Long> studentIds = examCalendar1.getStudentIds();
            Room room = examCalendar1.getRoom();
            
            Date fromDate = setHoursMinutesToJavaUtilDate(examCalendar1.getDateOfExam(),
                    examCalendar1.getStartTime().getHours(), examCalendar1.getStartTime().getMinutes());
            Date toDate = setHoursMinutesToJavaUtilDate(examCalendar1.getDateOfExam(),
                    examCalendar1.getEndTime().getHours(), examCalendar1.getEndTime().getMinutes());

            if (studentIds != null && studentIds.size() > 0) {
                List<Long> allottedRoomList = new ArrayList<>();
                System.out.println("StudentIdList Size = " + studentIds.size());

                Long id = room.getId();
                Long capacity = room.getCapacity();
                System.out.println("Room Capacity = " + capacity);
                RoomAllotment roomAllotment = new RoomAllotment();
                roomAllotment.setRoom_id(id);
                roomAllotment.setAllotmentFromdate(fromDate);
                roomAllotment.setAllotmentTodate(toDate);

                Long roomAllotmentId = roomAllotmentRepository.save(roomAllotment).getId();
                allottedRoomList.add(roomAllotmentId);
                List<RoomStudentLink> roomStudentLinkList = new ArrayList<>();

                for (int i = 0; i < capacity; i++) {
                    if (i < studentIds.size() || (studentIds.size() == 1)) {
                        Long studentId = (studentIds.size() == 1) ? studentIds.get(0) : studentIds.get(i);
                        RoomStudentLink roomStudentLink = new RoomStudentLink();
                        roomStudentLink.setStudent_id(studentId);
                        roomStudentLink.setRoomallotment_id(roomAllotmentId);

                        roomStudentLinkList.add(roomStudentLink);
                        studentIds.remove(studentIds.size() == 1 ? 0 : i);
                    } else {
                        break;
                    }
                }

                roomStudentLinkRepository.saveAll(roomStudentLinkList);
            }

            return examCalendar1;
        }
        return null;
    }

    @Override
    public ExamCalendar update(ExamCalendar examCalendar) {
        ExamCalendar savedExamCalendar = this.examCalendarRepository.findById(examCalendar.getId()).orElse(null);
        if(Objects.isNull(savedExamCalendar)){
            throw new ServiceException("Exam calendar not found. Can't update!");
        }
        if(this.validate(examCalendar)) {
            CommonUtils.copyNonNullProperties(examCalendar, savedExamCalendar);
            return this.examCalendarRepository.save(examCalendar);
        }
        return null;
    }

    @Override
    public ExamCalendar find(Long id) {
        return this.examCalendarRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExamCalendar> findAll() {
        return this.examCalendarRepository.findAll();
    }

    private boolean validate(ExamCalendar examCalendar){
        return true;
    }

    public Date setHoursMinutesToJavaUtilDate(Date date, int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
