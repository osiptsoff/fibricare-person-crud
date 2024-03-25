package ru.spb.fibricare.api.personcrud.service;

import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;

public interface PagedReadingService<T> {
    PageDto<T> findPage(PageRequestDto pageRequestDto);
}
