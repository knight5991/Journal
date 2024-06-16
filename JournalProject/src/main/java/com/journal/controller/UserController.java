package com.journal.controller;

import com.github.pagehelper.PageInfo;
import com.journal.pojo.Article;
import com.journal.pojo.Manager;
import com.journal.pojo.basicClass.User;
import com.journal.service.ManageService;
import com.journal.service.UserSeries;
import com.journal.utils.ApiResponse;
import com.journal.utils.PageRequest;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserSeries userSeries;

    @RequestMapping("/login")
    public ResponseEntity<ApiResponse<Void>> updatePassword(@RequestBody String Account) {
        return null;
    }

    @RequestMapping("/findArticle")
    public ApiResponse<PageInfo> findArticle(@RequestParam("key") String key,
                                             @RequestParam("searchWord") String searchWord,
                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<Article> pageInfo = null;
        PageRequest page=new PageRequest(pageNum,pageSize);
        switch (key) {
            case "title":
                pageInfo = userSeries.findByTitle(searchWord,page);
                break;
            case "author":
                pageInfo = userSeries.findByAuthor(searchWord,page);
                break;
            case "category":
                pageInfo = userSeries.findByCategory(searchWord,page);
                break;
            case "keywords":
                pageInfo = userSeries.findByKeyWord(searchWord,page);
                break;
            default:
                return new ApiResponse<>(false, "Invalid search key", null);
        }

        return new ApiResponse<>(true, "Success", pageInfo);
    }
}
