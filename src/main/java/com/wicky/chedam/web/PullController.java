package com.wicky.chedam.web;

import com.wicky.chedam.service.PullService;
import com.wicky.chedam.web.vo.Egg;
import com.wicky.chedam.web.vo.Rope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PullController {

    @Autowired
    private PullService service;

    @PostMapping(value = "/pull", consumes = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public String pull(@RequestBody Egg firstEgg) {

        List<String> exportedInsertStatements = service.pullRope(firstEgg);

        System.out.println("exportedInsertStatements => ");
        exportedInsertStatements.forEach(System.out::println);

        return "success";
    }

}
