package com.tripster.project.controller;

import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.mapper.UserReportDTOMapper;
import com.tripster.project.model.User;
import com.tripster.project.model.UserReport;
import com.tripster.project.service.UserReportServiceImpl;
import com.tripster.project.service.UserServiceImpl;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users/reports")
public class UserReportController {

    @Autowired
    private UserReportServiceImpl userReportService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserReportDTO>> getAll() {

        List<UserReportDTO> reports = userReportService.findAll().stream()
                .map(UserReportDTOMapper::fromUserReportToDTO)
                .toList();

        return new ResponseEntity<>(reports, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserReportDTO> getOne(@PathVariable Long id) {

        UserReport report = userReportService.findOne(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserReportDTOMapper.fromUserReportToDTO(report), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserReportDTO> reportUser(@RequestBody UserReportDTO dto) {

        UserReport report = UserReportDTOMapper.fromDTOToUserReport(dto);
        User reporter = userService.findOne(dto.getReporterId());
        User reportee = userService.findOne(dto.getReporteeId());

        if (reporter == null || reportee == null) {
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }

        report.setReporter(reporter);
        report.setReportee(reportee);
        userReportService.save(report);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        UserReport report = userReportService.findOne(id);

        if (report != null) {
            userReportService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
