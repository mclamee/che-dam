package com.wicky.chedam.web;

import com.wicky.chedam.service.PullService;
import com.wicky.chedam.web.vo.Rope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public @ResponseBody String pull(@RequestBody Rope root){

        List<String> insertSqlList = service.extract(root);

        System.out.println("insertSqlList => ");
        insertSqlList.forEach(System.out::println);

        return "success";
    }

}
