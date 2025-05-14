package CUHA.homepage.service;

import CUHA.homepage.security.dto.fileDTO.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BoardFileService {
    FileResponse saveFile(MultipartFile file, BoardFileSaveRequest request) throws IOException;
    List<GeneralFileResponse> getFile(BoardFileSaveRequest request);
    InputStreamResource downloadFile(Long id) throws FileNotFoundException;
    FileResponse deleteFile(List<Long> ids);
    FileResponse findFilename(Long id);
}
