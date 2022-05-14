package fileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileServerApplication.class, args);
		/*ConfigurableApplicationContext context = SpringApplication.run(FileServerApplication.class, args);
		FileEntityService fileEntityService = context.getBean(FileEntityService.class);
		FileEntityDto fileEntityDto = new FileEntityDto();
		fileEntityDto.setName("file.txt");
		fileEntityDto.setSizeKb(100L);
		FileEntityDto savedEntity = fileEntityService.save(fileEntityDto);
		System.out.println(savedEntity.toString());*/
	}

}
