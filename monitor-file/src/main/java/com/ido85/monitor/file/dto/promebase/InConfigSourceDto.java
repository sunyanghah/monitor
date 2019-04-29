package com.ido85.monitor.file.dto.promebase;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by dell on 2019/4/24.
 * @author dell
 */
@Data
public class InConfigSourceDto {

    @NotBlank
    private String configContent;

}
