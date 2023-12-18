package com.tripster.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhotoDTO {

    private Long id;

    private byte[] bytes;

}
