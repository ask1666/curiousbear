package com.kodebjorn.controllers;

import com.kodebjorn.models.dto.CreateQuizDto;
import com.kodebjorn.models.dto.QuizAntity;
import com.kodebjorn.models.mappers.QuizMapper;
import com.kodebjorn.services.QuizService;
import com.kodebjorn.services.UserService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.kodebjorn.models.mappers.QuizMapper.mapToApi;
import static com.kodebjorn.models.mappers.QuizMapper.mapUpdatedFields;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/quiz")
@Introspected
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    public QuizController(QuizService quizService,
                          UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @Get
    public HttpResponse<?> getAllQuiz() {
        List<QuizAntity> quizAntities = quizService.getAll()
            .stream()
            .map(QuizMapper::mapToApi)
            .collect(Collectors.toList());

        return HttpResponse.ok(quizAntities);
    }

    @Get("/{title}")
    public HttpResponse<?> getQuizByTitle(@PathVariable String title) {
        return HttpResponse.ok(mapToApi(quizService.findByTitle(title)));
    }

    @Post
    public HttpResponse<?> createQuiz(@Valid @Body CreateQuizDto quizDto) {
        var user = userService.findByUsername(quizDto.getUsername());
        var quiz = quizDto.getQuiz();
        quiz.setUser(user);
        return HttpResponse.ok(mapToApi(quizService.save(quiz)));
    }

    @Put("/{id}")
    public HttpResponse<?> updateQuiz(@PathVariable Integer id, @Body CreateQuizDto quizDto) {
        var quiz = quizService.findById(id);
        var mappedQuiz = mapUpdatedFields(quiz, quizDto.getQuiz());
        return HttpResponse.ok(quizService.save(mappedQuiz));
    }

    @Delete("/{title}")
    public HttpResponse<?> deleteQuizByTitle(@PathVariable String title) {
        quizService.deleteByTitle(title);
        return HttpResponse.ok("ok");
    }

}
