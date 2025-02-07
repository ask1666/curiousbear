package com.kodebjorn.controllers;

import com.kodebjorn.models.dto.AddQuizEntryDto;
import com.kodebjorn.models.dto.QuizEntryAntity;
import com.kodebjorn.models.dto.UpdateQuizEntryDto;
import com.kodebjorn.models.mappers.QuizEntryMapper;
import com.kodebjorn.services.QuizEntryService;
import com.kodebjorn.services.QuizService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.transaction.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/quizEntry")
@Introspected
public class QuizEntryController {

    private final QuizService quizService;
    private final QuizEntryService quizEntryService;

    private static final QuizEntryMapper qeMapper = QuizEntryMapper.getInstance();

    public QuizEntryController(QuizService service,
                               QuizEntryService entryService) {
        quizService = service;
        quizEntryService = entryService;
    }

    @Get("/{id}")
    public HttpResponse<?> getEntryById(@PathVariable Integer id) {
        return HttpResponse.ok(qeMapper.mapToApi(quizEntryService.getById(id)));
    }

    @Transactional
    @Get("allEntriesByQuizTitle/{title}")
    public HttpResponse<?> getEntriesByQuizTitle(@PathVariable String title) {
        List<QuizEntryAntity> quizAntities = quizEntryService.getByQuizTitle(title)
            .stream()
            .map(qeMapper::mapToApi)
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
        return HttpResponse.ok(qeMapper.mapToApi(quizEntryService.save(quizEntry)));
    }

    @Transactional
    @Put("/{id}")
    public HttpResponse<?> updateQuizEntry(@PathVariable Integer id, @Body UpdateQuizEntryDto quizEntryDto) {
        var quizEntry = quizEntryService.getById(id);
        var mappedQuizEntry = qeMapper.mapUpdatedFields(quizEntry, quizEntryDto.getQuizEntry());
        return HttpResponse.ok(qeMapper.mapToApi(quizEntryService.save(mappedQuizEntry)));
    }

    @Delete("/{entryId}")
    public HttpResponse<?> deleteEntryById(@PathVariable Integer entryId) {
        quizEntryService.deleteById(entryId);
        return HttpResponse.ok("ok");
    }

}
