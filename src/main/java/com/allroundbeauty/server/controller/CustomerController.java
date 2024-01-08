package com.allroundbeauty.server.Controller;

import com.allroundbeauty.server.dto.CallImageDto;
import com.allroundbeauty.server.dto.CallPlaceDto;
import com.allroundbeauty.server.dto.DestinationDto;
import com.allroundbeauty.server.dto.driverInfo.DriverDetailDto;
import com.allroundbeauty.server.dto.driverInfo.DriverInfoDto;
import com.allroundbeauty.server.dto.driverInfo.DriverPositionDto;
import com.allroundbeauty.server.service.CallService;
import com.allroundbeauty.server.service.CustomerService;
import com.allroundbeauty.server.service.DriverService;
import com.allroundbeauty.server.vo.CallCreateVo;
import com.allroundbeauty.server.vo.CallIdVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CustomerController {
    private final DriverService driverService;
    private final CallService callService;
    private final CustomerService customerService;

    @GetMapping("/call/driver-position/{call_id}")
    public DriverInfoDto driverInfo(@PathVariable("call_id") Long id) {
        Long driver_id = callService.findDriverIdByCallId(id);
        log.info("driver_id : " + driver_id);
        DriverDetailDto driverDetailDto = driverService.getDriverInfoById(driver_id);
        log.info("driver : " + driverDetailDto.getName());
        DriverPositionDto driverPositionDto = callService.findDriverPosition(id);
        log.info("position : " + driverPositionDto.getX());
        return new DriverInfoDto(driverDetailDto, driverPositionDto);
    }

    @GetMapping("/recent/{customerId}")
    public DestinationDto recentDestination(@PathVariable("customerId") Long customerId) {
        return customerService.getRecents(customerId);
    }

    @GetMapping("/call/complete/{callId}")
    public CallImageDto getCallImage(@PathVariable("callId") Long callId) {
        return callService.getCallImage(callId);
    }

    @PostMapping("/call/finish")
    public void callFinish(@RequestBody CallIdVo callIdVo) {
        callService.updateCallState(callIdVo.getId());
    }

    @PostMapping("/create/call")
    public CallPlaceDto createCall(@RequestBody @Validated CallCreateVo callCreateVo) {
        return callService.createCall(callCreateVo);
    }
}
