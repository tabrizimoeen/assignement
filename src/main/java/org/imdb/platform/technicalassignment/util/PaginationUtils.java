package org.imdb.platform.technicalassignment.util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public final class PaginationUtils {

    private PaginationUtils() {
    }

    public static <T> Page<T> paginate(
            List<T> list,
            Pageable pageable
    ) {

        if (list == null || list.isEmpty()) {
            return Page.empty(pageable);
        }

        int start = (int) pageable.getOffset();

        if (start >= list.size()) {
            return new PageImpl<>(
                    Collections.emptyList(),
                    pageable,
                    list.size()
            );
        }

        int end = Math.min(
                start + pageable.getPageSize(),
                list.size()
        );

        List<T> content = list.subList(start, end);

        return new PageImpl<>(
                content,
                pageable,
                list.size()
        );
    }
}