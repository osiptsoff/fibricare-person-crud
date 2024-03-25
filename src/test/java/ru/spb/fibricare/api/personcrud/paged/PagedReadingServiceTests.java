package ru.spb.fibricare.api.personcrud.paged;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;
import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.factory.TestEntityDtoFactory;
import ru.spb.fibricare.api.personcrud.service.PagedReadingService;

@Transactional
@RequiredArgsConstructor
public abstract class PagedReadingServiceTests<T> {
    protected final PagedReadingService<T> pagedReadingService;
    protected final CrudRepository<T, ?> crudRepository;
    protected final TestEntityDtoFactory<T, ?> factory;

    private final Integer itemsCount;

    @Test
    public final void findPageTest() {
        for(int i = 0; i < itemsCount * 2; i++) {
            var dto = factory.instantiate(Optional.empty());
            crudRepository.save(dto.from());
        }

        for(int i = 0; i < 2; i++) {
            PageDto<T> pageDto = pagedReadingService.findPage(createPageRequestDto(i));

            Assert.isTrue(pageDto != null, "Page DTO must not be null");
            Assert.isTrue(pageDto.getData() != null, "Content array must not be null");
            Assert.isTrue(pageDto.getData().size() > 0, "Page must not be empty");
            if(i == 0) {
                Assert.isTrue(pageDto.getPageNumber() + 1 < pageDto.getTotalPages(), "Page must not be last");
            }

            verifyPageDto(pageDto);
        }
        
    }

    protected PageRequestDto createPageRequestDto(Integer pageNumber) {
        return new PageRequestDto(pageNumber, itemsCount);
    }

    protected void verifyPageDto(PageDto<T> pageDto) {

    }
}
