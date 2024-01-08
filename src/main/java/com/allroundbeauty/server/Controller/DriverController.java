package com.allroundbeauty.server.Controller;

import com.allroundbeauty.server.dto.CallDTO;
import com.allroundbeauty.server.dto.CallDetailDTO;
import com.allroundbeauty.server.dto.DriverAcceptRequestDTO;
import com.allroundbeauty.server.dto.DriverAcceptResponseDTO;
import com.allroundbeauty.server.exception.BadRequestException;
import com.allroundbeauty.server.service.CallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/api/call/{id}")
    public CallDetailDTO getCallDetail(@PathVariable("id") Long id) {
        return callService.getCallDetail(id);
    }

    @PostMapping("/api/call/pickup/{id}")
    private void pickUp(@PathVariable("id") Long id) {
        callService.pickUp(id);
    }

    @PostMapping("/api/call/accept")
    public DriverAcceptResponseDTO accept(@RequestBody DriverAcceptRequestDTO acceptRequestDTO) {
        return callService.accept(acceptRequestDTO);
    }

    @PostMapping("/api/call/complete/{id}")
    private void deliveryCompleted(@PathVariable("id") Long id, @RequestPart(value = "image", required = false) MultipartFile image) {
        if (image == null) {
            throw new BadRequestException("이미지 파일이 없습니다.");
        }
        callService.deliveryCompleted(id, image);
    }
}

