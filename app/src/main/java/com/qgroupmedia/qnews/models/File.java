package com.qgroupmedia.qnews.models;

public class File {
    private String _id;
    private String fileNewName;
    private String mimeType;
    private String originalName;
    private String filePath;
    private Integer fileType;


    public File(String fid, String ffileNewName, String fmimeType, String foriginalName, String ffilePath, Integer ffileType) {
        _id = fid;
        fileNewName = ffileNewName;
        mimeType = fmimeType;
        originalName = foriginalName;
        filePath = ffilePath;
        fileType = ffileType;
    }


    public String getFilePath() {
        return filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

}
