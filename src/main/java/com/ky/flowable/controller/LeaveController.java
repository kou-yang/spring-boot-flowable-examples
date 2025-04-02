package com.ky.flowable.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-01 17:05
 */
@Api(tags = "请假")
@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final TaskService taskService;
}
