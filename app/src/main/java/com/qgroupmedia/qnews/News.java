/*
 * MIT License
 *
 * Copyright (c) 2018 Soojeong Shin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.qgroupmedia.qnews;

import com.qgroupmedia.qnews.models.File;
import com.qgroupmedia.qnews.models.User;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link News} object contains information related to a single news.
 */

public class News {
    private String title;
    private String description;
    private String dateTime;
    private User user;
    private List<File> files;
    private Boolean show;
    private Integer type;
    private String source;
    private String analysis1;
    private String analysis2;
    private String analysis3;
    private Integer views;

    public News(String nTitle, String nDescription, String nDateTime, User nUser, Boolean nShow, Integer nType, String nSource, String nAnalysis1, String nAnalysis2, String nAnalysis3, Integer nViews, List<File> nfiles) {
        title = nTitle;
        description = nDescription;
        dateTime = nDateTime;
        user = nUser;
        show = nShow;
        type = nType;
        source = nSource;
        analysis1 = nAnalysis1;
        analysis2 = nAnalysis2;
        analysis3 = nAnalysis3;
        views = nViews;
        files = nfiles;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public Boolean getShow() {
        return show;
    }

    public Integer getType() {
        return type;
    }

    public String getAnalysis1() {
        return analysis1;
    }

    public String getAnalysis2() {
        return analysis2;
    }

    public String getAnalysis3() {
        return analysis3;
    }

    public Integer getViews() {
        return views;
    }

    public List<File> getFiles() {
        return files;
    }


}
