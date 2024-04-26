package org.izouir.meetup_api.comparator;

import org.izouir.meetup_api.entity.Meet;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class MeetComparatorByKeeper implements Comparator<Meet> {
    @Override
    public int compare(final Meet o1, final Meet o2) {
        if (o1.getKeeper().equals(o2.getKeeper())) {
            return 0;
        } else if (o1.getKeeper().compareTo(o2.getKeeper()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
