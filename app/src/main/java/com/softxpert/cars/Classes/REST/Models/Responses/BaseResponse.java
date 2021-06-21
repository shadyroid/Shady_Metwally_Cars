package com.softxpert.cars.Classes.REST.Models.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BaseResponse {

    /**
     * message : اتم حذف الكتاب من  قائمه تنزيلاتي  بنجاح .
     * authenticated : true
     * registered : false
     * success : true
     */
    private String message;
    private boolean success;
    private int status;

    public boolean isSuccess() {
        return status==1;
    }
}