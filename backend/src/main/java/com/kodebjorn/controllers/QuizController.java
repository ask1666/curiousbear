package com.kodebjorn.controllers;

import com.kodebjorn.models.dto.CreateQuizDto;
import com.kodebjorn.models.dto.QuizAntity;
import com.kodebjorn.models.dto.UpdateQuizDto;
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
import jakarta.transaction.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.kodebjorn.models.mappers.QuizMapper.mapUpdatedFields;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/quiz")
@Introspected
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    private final QuizMapper quizMapper = QuizMapper.getInstance();

    public QuizController(QuizService quizService,
                          UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get
    public HttpResponse<?> getAllQuiz() {
        List<QuizAntity> quizAntities = quizService.getAll()
            .stream()
            .map(quizMapper::mapToApi)
            .collect(Collectors.toList());

        return HttpResponse.ok(quizAntities);
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/{username}")
    public HttpResponse<List<QuizAntity>> getMyQuiz(@PathVariable String username) {
        return HttpResponse.ok(userService.getMyQuiz(username)
            .stream()
            .map(quizMapper::mapToApi)
            .toList());
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/{title}")
    public HttpResponse<?> getQuizByTitle(@PathVariable String title) {
        return HttpResponse.ok(quizMapper.mapToApi(quizService.findByTitle(title)));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post
    public HttpResponse<?> createQuiz(@Valid @Body CreateQuizDto quizDto) {
        var user = userService.findByUsername(quizDto.getUsername());
        var quiz = quizDto.getQuiz();
        quiz.setUser(user);
        return HttpResponse.ok(quizMapper.mapToApi(quizService.save(quiz)));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Transactional
    @Put("/{id}")
    public HttpResponse<?> updateQuiz(@PathVariable Integer id, @Body UpdateQuizDto quizDto) {
        var quiz = quizService.findById(id);
        var mappedQuiz = mapUpdatedFields(quiz, quizDto.getQuiz());
        return HttpResponse.ok(quizMapper.mapToApi(quizService.save(mappedQuiz)));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete("/{title}")
    public HttpResponse<?> deleteQuizByTitle(@PathVariable String title) {
        quizService.deleteByTitle(title);
        return HttpResponse.ok("ok");
    }

}
