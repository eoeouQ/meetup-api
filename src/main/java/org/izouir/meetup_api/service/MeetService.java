package org.izouir.meetup_api.service;

import org.izouir.meetup_api.dto.MeetDto;

import java.util.List;

public interface MeetService {
    List<MeetDto> findAll();

    MeetDto find(Long meetId);

    void save(MeetDto meetDto);

    void update(MeetDto meetDto);

    void delete(Long meetId);

    List<MeetDto> findAllFilteredAndSorted(String title, String keeper, String timestamp, String comparator);
}
