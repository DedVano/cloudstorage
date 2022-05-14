package fileserver.mapper;

import fileserver.dto.FileEntityDto;
import fileserver.model.FileEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface FileEntityMapper {

    FileEntityDto toDto(FileEntity entity);

    FileEntity toEntity(FileEntityDto dto);

    default List<FileEntityDto> toDtos(List<FileEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default Optional<FileEntityDto> toOptionalDto(Optional<FileEntity> entity) {
        return entity.map(this::toDto);
    }
}
