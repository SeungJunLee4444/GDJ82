DROP TABLE SUMMERNOTE_IMAGE;
CREATE TABLE SUMMERNOTE_IMAGE
(
    GAL_NO NUMBER,
    FILESYSTEM VARCHAR2(40 BYTE)
);

-- 서머노트 이미지 외래키 : 블로그를 삭제하면 써머노트에서 사용한 이미지 파일도 삭제한다.
ALTER TABLE SUMMERNOTE_IMAGE
    ADD CONSTRAINT FK_SUMMERNOTE_GALLERY
        FOREIGN KEY(GAL_NO) REFERENCES GALLERY(GAL_NO)
            ON DELETE CASCADE;