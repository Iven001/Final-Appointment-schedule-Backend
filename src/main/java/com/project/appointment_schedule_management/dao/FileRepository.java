package com.project.appointment_schedule_management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.Interface.ScheduleAttachment;
import com.project.appointment_schedule_management.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

    @Query(value = "Select s.schedule_id as id,a.file_id as fileId,a.data as data,a.document_name as docName,a.document_type as docType,a.file_size as size "
            +
            "From appointment.schedule s,appointment.attachment a " +
            "WHERE s.schedule_id=a.schedule_file_id and s.schedule_id=:scheduleId", nativeQuery = true)
    List<ScheduleAttachment> getScheduleFiles(int scheduleId);

}
