package org.izouir.meetup_api.mapper;

import org.izouir.meetup_api.dto.MeetDto;
import org.izouir.meetup_api.entity.Meet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetMapper {
    public MeetDto mapToDto(final Meet meet) {
        return new MeetDto(meet.getMeetId(), meet.getTitle(), meet.getDescription(), meet.getKeeper(), meet.getDate(), meet.getPlace());
    }

    public Meet mapToEntity(final MeetDto meetDto) {
        return new Meet(meetDto.meetId(), meetDto.title(), meetDto.description(), meetDto.keeper(), meetDto.date(), meetDto.place());
    }

    public List<MeetDto> mapToDtos(final List<Meet> meets) {
        return meets.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
