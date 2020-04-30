package edu.miu.waa.onlineshopping.uploadfiles.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FileResponse {
    private String name;
    private String uri;
    private String type;
    private long size;
}
