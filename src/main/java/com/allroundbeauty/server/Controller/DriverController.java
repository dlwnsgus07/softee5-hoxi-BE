package com.allroundbeauty.server.Controller;

import com.allroundbeauty.server.dto.CallDTO;
import com.allroundbeauty.server.service.CallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DriverController {

    private final CallService callService;

    @GetMapping("/api/calls")
    public List<CallDTO> getAllCallList() {
        return callService.getCallList();
    }
}
