package com.kodebjorn.controllers;

import com.kodebjorn.models.dto.AddQuizEntryDto;
import com.kodebjorn.models.dto.QuizEntryAntity;
import com.kodebjorn.models.dto.UpdateQuizEntryDto;
import com.kodebjorn.models.mappers.QuizEntryMapper;
import com.kodebjorn.services.QuizEntryService;
import com.kodebjorn.services.QuizService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.kodebjorn.models.mappers.QuizEntryMapper.mapToApi;
import static com.kodebjorn.models.mappers.QuizEntryMapper.mapUpdatedFields;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/quizEntry")
@Introspected
public class QuizEntryController {

    private final QuizService quizService;
    private final QuizEntryService quizEntryService;

    public QuizEntryController(QuizService service,
                               QuizEntryService entryService) {
        quizService = service;
        quizEntryService = entryService;
    }

    @Get("/{id}")
    public HttpResponse<?> getEntryById(@PathVariable Integer id) {
        return HttpResponse.ok(mapToApi(quizEntryService.getById(id)));
    }

    @Transactional
    @Get("allEntriesByQuizTitle/{title}")
    public HttpResponse<?> getEntriesByQuizTitle(@PathVariable String title) {
        List<QuizEntryAntity> quizAntities = quizEntryService.getByQuizTitle(title)
            .stream()
            .map(QuizEntryMapper::mapToApi)
            .collect(Collectors.toList());

        System.out.println(quizAntities);
        return HttpResponse.ok(quizAntities);
    }

    @Transactional
    @Post
    public HttpResponse<?> addQuizEntry(@Valid @Body AddQuizEntryDto addQuizEntryDto) {
        var quizEntry = addQuizEntryDto.getQuizEntry();
        var quiz = quizService.findByTitle(addQuizEntryDto.getQuizTitle());
        quizEntry.setQuiz(quiz);
        return HttpResponse.ok(mapToApi(quizEntryService.save(quizEntry)));
    }

    @Transactional
    @Put("/{id}")
    public HttpResponse<?> updateQuizEntry(@PathVariable Integer id, @Body UpdateQuizEntryDto quizEntryDto) {
        var quizEntry = quizEntryService.getById(id);
        var mappedQuizEntry = mapUpdatedFields(quizEntry, quizEntryDto.getQuizEntry());
        return HttpResponse.ok(mapToApi(quizEntryService.save(mappedQuizEntry)));
    }

    @Delete("/{entryId}")
    public HttpResponse<?> deleteEntryById(@PathVariable Integer entryId) {
        quizEntryService.deleteById(entryId);
        return HttpResponse.ok("ok");
    }

}
