package org.izouir.meetup_api.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.izouir.meetup_api.comparator.MeetComparatorFactory;
import org.izouir.meetup_api.dto.MeetDto;
import org.izouir.meetup_api.exception.InvalidDateFormatException;
import org.izouir.meetup_api.mapper.MeetMapper;
import org.izouir.meetup_api.repository.MeetRepository;
import org.izouir.meetup_api.service.MeetService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MeetServiceImpl implements MeetService {
    private final MeetRepository meetRepository;
    private final MeetMapper meetMapper;
    private final MeetComparatorFactory meetComparatorFactory;

    public MeetServiceImpl(final MeetRepository meetRepository,
                           final MeetMapper meetMapper,
                           final MeetComparatorFactory meetComparatorFactory) {
        this.meetRepository = meetRepository;
        this.meetMapper = meetMapper;
        this.meetComparatorFactory = meetComparatorFactory;
    }

    @Override
    public List<MeetDto> findAll() {
        return meetMapper.mapToDtos(meetRepository.findAll());
    }

    @Override
    public MeetDto find(final Long meetId) {
        final var meet = meetRepository.find(meetId).orElseThrow(
                () -> new EntityNotFoundException("Meet with meetId = " + meetId + " was not found"));
        return meetMapper.mapToDto(meet);
    }

    @Override
    public void save(final MeetDto meetDto) {
        meetRepository.save(meetMapper.mapToEntity(meetDto));
    }

    @Override
    public void update(final MeetDto meetDto) {
        meetRepository.find(meetDto.meetId()).orElseThrow(
                () -> new EntityNotFoundException("Meet with meetId = " + meetDto.meetId() + " was not found"));
        meetRepository.update(meetMapper.mapToEntity(meetDto));
    }

    @Override
    public void delete(final Long meetId) {
        meetRepository.delete(meetId);
    }

    @Override
    public List<MeetDto> findAllFilteredAndSorted(final String title, final String keeper,
                                                  final String timestamp, final String comparator) {
        try {
            final var date = (timestamp.isBlank()) ? new Timestamp(0) : Timestamp.valueOf(timestamp);
            final var meets = meetRepository.findAllFiltered(title, keeper, date);
            meets.sort(meetComparatorFactory.get(comparator));
            return meetMapper.mapToDtos(meets);
        } catch (final IllegalArgumentException e) {
            throw new InvalidDateFormatException("Date format must be yyyy-mm-dd hh:mm:ss[.fffffffff]");
        }
    }
}
