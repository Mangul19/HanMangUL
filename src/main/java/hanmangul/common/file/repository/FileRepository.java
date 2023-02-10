package hanmangul.common.file.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hanmangul.common.file.entity.FileEntity;
import hanmangul.common.file.entity.RfrncTbl;

interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByRfrncSnAndRfrncSn(Long rfrncSn, RfrncTbl rfrncTbl);

    List<FileEntity> findByFileSnIn(List<Long> deleteSeqList);
}
