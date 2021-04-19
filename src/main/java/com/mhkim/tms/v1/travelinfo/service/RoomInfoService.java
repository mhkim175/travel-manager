package com.mhkim.tms.v1.travelinfo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mhkim.tms.v1.travelinfo.entity.RoomInfo;
import com.mhkim.tms.v1.travelinfo.repository.RoomInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;

    public Optional<RoomInfo> addRoomInfo(RoomInfo roomInfo) {
        return Optional.of(roomInfoRepository.save(roomInfo));
    }

}
