package CUHA.homepage.service;

import CUHA.homepage.security.dto.fileDTO.ExamFileResponse;
import CUHA.homepage.security.dto.fileDTO.ExamFileSaveRequest;
import CUHA.homepage.security.dto.fileDTO.FileResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ExamFileService {
    FileResponse saveFile(MultipartFile file, ExamFileSaveRequest request) throws IOException;
    List<ExamFileResponse> getFile(ExamFileSaveRequest request);
    InputStreamResource downloadFile(Long id) throws FileNotFoundException;
    FileResponse deleteFile(List<Long> ids);
    FileResponse findFilename(Long id);
}
