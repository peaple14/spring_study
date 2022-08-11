package hello.upload.controller.domain;

import lombok.Data;

@Data

public class UploadFile {

    private String uploadfileName; //업로더가 설정한 이름
    private String storeFileName; //서버에 저장될 이름

    public UploadFile(String uploadfileName, String storeFileName) {
        this.uploadfileName = uploadfileName;
        this.storeFileName = storeFileName;
    }
}
